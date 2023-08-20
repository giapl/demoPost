package com.example.demopost.service.Question;

import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.repository.IQuestionRepository;
import java.time.LocalDateTime;
import java.util.List;
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
  public Question create_Question(QuestionRequest question) {
    Question question1 = new Question();
    question1.setDate(LocalDateTime.now());
    question1.setContent(question.getContent());
    question1.setImageUrl(question.getImgUrl());
    question1.setComment(question1.getComment());
    return iQuestionRepository.save(question1);
  }

  @Override
  public List<Question> finAll_Question() {
    return iQuestionRepository.findAll();
  }
}
