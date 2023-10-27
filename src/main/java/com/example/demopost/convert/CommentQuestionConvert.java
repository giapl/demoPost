package com.example.demopost.convert;

import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.response.CommentQuestionResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentQuestionConvert {

  private final ModelMapper modelMapper;

  @Autowired
  public CommentQuestionConvert(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
  public CommentQuestionResponse commentQuestionResponse(CommentQuestion commentQuestion) {
    return modelMapper.map(commentQuestion , CommentQuestionResponse.class);
  }
}
