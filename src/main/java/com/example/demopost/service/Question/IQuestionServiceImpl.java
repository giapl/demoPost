package com.example.demopost.service.Question;

import com.example.demopost.convert.QuestionConvert;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.data.response.QuestionResponse;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.repository.IQuestionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IQuestionServiceImpl implements IQuestionService {

  private IQuestionRepository iQuestionRepository;

  private QuestionConvert questionConvert;

  @Autowired
  public IQuestionServiceImpl(IQuestionRepository iQuestionRepository, QuestionConvert questionConvert) {
    this.iQuestionRepository = iQuestionRepository;
    this.questionConvert = questionConvert;
  }


  @Override
  public Question createQuestion(QuestionRequest question) {
    Question question1 = new Question();
    question1.setContent(question.getContent());
    question1.setImageUrl(question.getImageUrl());
    question1.setDate(LocalDateTime.now());
    question1.setUpdateTime(LocalDateTime.now());
    return iQuestionRepository.save(question1);
  }

  @Override
  public List<QuestionResponse> finAllQuestion() {
    return iQuestionRepository.findAll()
        .stream()
        .map(questionConvert::ConvertEntityQuestion)
        .collect(Collectors.toList());
  }


  @Override
  public Optional<QuestionResponse> searchId(long id) {
    Optional<Question> questionOptional = iQuestionRepository.searchById(id);
    if (questionOptional.isPresent()) {
      return Optional.of(questionConvert.ConvertEntityQuestion(questionOptional.get()));
    } else {
      throw new NotFoundException("no id database");
    }
  }

  @Override
  public void deleteById(Long id) {
    if (iQuestionRepository.existsById(id)) {
      iQuestionRepository.deleteById(id);
    } else {
      throw new NotFoundException("no id or id question on delete successful");
    }
  }

  @Override
  public void updateById(Long id, QuestionRequest questionRequest) {
    Optional<Question> questionOptional = iQuestionRepository.findById(id);
    if (questionOptional.isPresent()) {
      Question question = questionOptional.get();
      question.setContent(questionRequest.getContent());
      question.setImageUrl(questionRequest.getImageUrl());
      question.setUpdateTime(LocalDateTime.now());
      iQuestionRepository.save(question);
    } else {
      throw new NotFoundException("no id");
    }
  }
}
