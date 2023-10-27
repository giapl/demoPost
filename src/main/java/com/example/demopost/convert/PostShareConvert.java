package com.example.demopost.convert;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.response.PostShareResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostShareConvert {

  private final ModelMapper modelMapper;

  @Autowired
  public PostShareConvert(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
  public PostShareResponse shareResponse(PostTopic topic) {
    return modelMapper.map(topic , PostShareResponse.class);
  }
}
