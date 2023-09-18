package com.example.demopost.convert;

import com.example.demopost.data.enity.Question;
import com.example.demopost.data.response.QuestionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionConvert {

  private final ModelMapper modelMapper;

  public QuestionConvert(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public QuestionResponse ConvertEntityQuestion(Question question){
    return modelMapper.map(question, QuestionResponse.class);
  }

}
