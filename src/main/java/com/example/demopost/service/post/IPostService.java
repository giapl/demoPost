package com.example.demopost.service.post;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import java.util.List;



public interface IPostService {

  PostTopic create_Topic(PostRequest post);
  List<PostTopic> findAll_Topic();
  List<PostTopic> searchByTitle(String title);

}
