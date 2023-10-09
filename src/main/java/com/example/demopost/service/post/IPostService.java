package com.example.demopost.service.post;

import com.example.demopost.data.enity.Like;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.data.response.PostResponse;
import com.example.demopost.data.response.PostShareResponse;
import java.util.List;
import java.util.Optional;


public interface IPostService {

  PostTopic createTopic(PostRequest post); // method them post

  List<PostResponse> findAllTopic(); // method hien thi all bai post

  List<PostResponse> searchByTitle(String title); // method search theo title bai post

  Optional<PostResponse> searchById(Long id); // method search theo id bai post

  void deleteById(Long id); // method delete postTopic theo id

  void updateById(Long id, PostRequest postRequest); // method update postTopic theo id

  void increaseLikes(Long id , Like like); // method tang like bai post

  PostShareResponse increaseShare(Long id , Like like, PostRequest postRequest); // method tang share cho bai post
}
