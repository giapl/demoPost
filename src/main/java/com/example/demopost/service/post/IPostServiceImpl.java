package com.example.demopost.service.post;

import com.example.demopost.convert.UserConvert;
import com.example.demopost.data.enity.Like;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.data.response.PostResponse;
import com.example.demopost.exception.InternalServerException;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.repository.IPostRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class IPostServiceImpl implements IPostService {

  private IPostRepository iPostRepository;


  private UserConvert userConvert;


  @Autowired
  public IPostServiceImpl(IPostRepository iPostRepository, UserConvert userConvert) {
    this.iPostRepository = iPostRepository;
    this.userConvert = userConvert;
  }

  @Override
  public PostTopic createTopic(PostRequest post) {
    PostTopic topic = new PostTopic();
    topic.setDateTime(LocalDateTime.now());
    topic.setTitle(post.getTitle());
    topic.setContent(post.getContent());
    topic.setImageUrl(post.getImageUrl());
    // like tuong ung
    Like like1 = new Like();
    like1.setLike(0);
    like1.setPostTopic(topic);
    List<Like> like = new ArrayList<>();
    like.add(like1);
    topic.setLikes(like);
    try {
      return iPostRepository.save(topic);
    } catch (DataAccessException e) {
      throw new InternalServerException("sorry save database");
    }
  }

  @Override
  public List<PostResponse> findAllTopic() {
    return iPostRepository.findAll()
        .stream()
        .map(userConvert::convertEntityToDo)
        .collect(Collectors.toList());
  }

  @Override
  public List<PostResponse> searchByTitle(String title) {
    try {
      List<PostTopic> postTopicList = iPostRepository.findByTitleContainingIgnoreCase(title);
      return postTopicList
          .stream()
          .map(userConvert::convertEntityToDo)
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new NotFoundException("no title database");
    }
  }

  @Override
  public Optional<PostResponse> searchById(Long id) {
    Optional<PostTopic> postTopic = iPostRepository.findById(id);
    if (postTopic.isPresent()) {
      return Optional.of(userConvert.convertEntityToDo(postTopic.get()));
    } else {
      throw new NotFoundException("no search id");
    }
  }

  @Override
  public void deleteById(Long id) {
    if (iPostRepository.existsById(id)) {
      iPostRepository.deleteById(id);
    } else {
      throw new NotFoundException("no id or id question on delete successful");
    }
  }

}



