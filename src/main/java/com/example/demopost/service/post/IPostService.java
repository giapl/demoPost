package com.example.demopost.service.post;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import java.util.List;



public interface IPostService {
  PostTopic createTopic(PostRequest post);
  List<PostTopic> findAllTopic();
  List<PostTopic> searchByTitle(String title);

}
