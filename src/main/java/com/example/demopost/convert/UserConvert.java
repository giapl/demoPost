package com.example.demopost.convert;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.response.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {

  private ModelMapper modelMapper;

  @Autowired
  public UserConvert(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public PostResponse convertEntityToDo(PostTopic topic) {
    PostResponse response = modelMapper.map(topic, PostResponse.class);
    response.setImgUrl(topic.getImageUrl());
    if (!topic.getLikes().isEmpty()) {
      response.setLike(topic.getLikes().get(0).getLike());
    } else {
      response.setLike(0);
    }
    return response;
  }
}
