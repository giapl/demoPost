package com.example.demopost.service.Question;

import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.data.response.QuestionResponse;
import com.example.demopost.exception.InternalServerException;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.repository.IQuestionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class IQuestionServiceImpl implements IQuestionService {

  private IQuestionRepository iQuestionRepository;

  @Autowired
  public IQuestionServiceImpl(IQuestionRepository iQuestionRepository) {
    this.iQuestionRepository = iQuestionRepository;
  }


  @Override
  public QuestionResponse createQuestion(QuestionRequest question) {
    Question question1 = new Question();
    question1.setContent(question.getContent());
    question1.setImageUrl(question.getImgUrl());
    question1.setDate(LocalDateTime.now());
    try {
      question1 = iQuestionRepository.save(question1);
      // response
      QuestionResponse response = new QuestionResponse();
      response.setId(question1.getId());
      response.setContent(question1.getContent());
      response.setImgUrl(question1.getImageUrl());
      response.setDate(LocalDateTime.now());
      response.setComment(question1.getComment());
      return response;
    } catch (DataAccessException e) {
      throw new InternalServerException("sorry save database");
    }
  }

  @Override
  public List<Question> finAllQuestion() {
    return iQuestionRepository.findAll();
  }


  @Override
  public Optional<Question> searchId(long id) {
    Optional<Question> questionOptional = iQuestionRepository.searchById(id);
    if (questionOptional.isPresent()) {
      return Optional.of(questionOptional.get());
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
}
