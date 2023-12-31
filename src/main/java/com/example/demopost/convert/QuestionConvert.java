package com.example.demopost.convert;

import com.example.demopost.data.enity.Question;
import com.example.demopost.data.response.QuestionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionConvert {

  private final ModelMapper modelMapper;

  @Autowired
  public QuestionConvert(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public QuestionResponse ConvertEntityQuestion(Question question) {
    QuestionResponse questionResponse = modelMapper.map(question, QuestionResponse.class);
    if (!question.getLikeQuestions().isEmpty()) {
      questionResponse.setLike(question.getLikeQuestions().get(0).getLike());
    } else {
      questionResponse.setLike(0);
    }

    return questionResponse;
  }
}
