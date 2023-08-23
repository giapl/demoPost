package com.example.demopost.service.Question;

import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.exception.InternalServerException;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.repository.IQuestionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IQuestionServiceImpl implements IQuestionService {

  private IQuestionRepository iQuestionRepository;

  @Autowired
  public IQuestionServiceImpl(IQuestionRepository iQuestionRepository) {
    this.iQuestionRepository = iQuestionRepository;
  }

  @Override
  public Question createQuestion(QuestionRequest question) {
      Question question1 = new Question();
      question1.setDate(LocalDateTime.now());
      question1.setContent(question.getContent());
      question1.setImageUrl(question.getImgUrl());
      question1.setComment(question1.getComment());
      try {
        return iQuestionRepository.save(question1);
      } catch (Exception e) {
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
}
