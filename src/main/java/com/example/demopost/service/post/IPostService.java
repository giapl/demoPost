package com.example.demopost.service.post;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.data.response.PostResponse;
import java.util.List;
import java.util.Optional;


public interface IPostService {

  PostResponse createTopic(PostRequest post); // method them post

  List<PostTopic> findAllTopic(); // method hien thi all bai post

  List<PostTopic> searchByTitle(String title); // method search theo title bai post

  Optional<PostTopic> searchById(Long id); // method search theo id bai post

  void deleteById(Long id); // method delete postTopic theo id
}
