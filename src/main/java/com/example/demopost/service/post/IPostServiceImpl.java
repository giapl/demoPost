package com.example.demopost.service.post;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.repository.IPostRepository;
import com.example.demopost.service.post.IPostService;
import java.time.LocalDateTime;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPostServiceImpl implements IPostService {

  private IPostRepository iPostRepository;

  @Autowired
  public IPostServiceImpl(IPostRepository iPostRepository) {
    this.iPostRepository = iPostRepository;
  }

  @Override
  public PostTopic create_Topic(@NotNull PostRequest post) {
    PostTopic topic =new PostTopic();
    topic.setTitle(post.getTitle());
    topic.setContent(post.getContent());
    topic.setImageUrl(post.getImageUrl());
    topic.setDateTime(LocalDateTime.now());
    topic.setLike(0);
    topic.setShare(0);
    return iPostRepository.save(topic);
  }

  @Override
  public List<PostTopic> findAll_Topic() {
    return iPostRepository.findAll();
  }

  @Override
  public List<PostTopic> searchByTitle(String title) {
    return iPostRepository.findByTitleContainingIgnoreCase(title);
  }

}
