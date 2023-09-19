package com.example.demopost.convert;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.response.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {

  private final ModelMapper modelMapper;

  @Autowired
  public UserConvert(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public PostResponse convertEntityToDo(PostTopic topic) {
    PostResponse response = modelMapper.map(topic, PostResponse.class);
    if (!topic.getLikes().isEmpty()) {
      response.setLike(topic.getLikes().get(0).getLike());
      response.setShare(topic.getLikes().get(0).getShare());
    } else {
      response.setLike(0);
      response.setShare(0);
    }
    return response;
  }
}
