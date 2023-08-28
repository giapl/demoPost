package com.example.demopost.service.post;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class IPostServiceImpl implements IPostService {

  private IPostRepository iPostRepository;

  @Autowired
  public IPostServiceImpl(IPostRepository iPostRepository) {
    this.iPostRepository = iPostRepository;
  }

  @Override
  public PostResponse createTopic(PostRequest post) {
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
      topic = iPostRepository.save(topic);
      // response post
      PostResponse response = new PostResponse();
      response.setId(topic.getId());
      response.setDateTime(LocalDateTime.now());
      response.setTitle(topic.getTitle());
      response.setContent(topic.getContent());
      response.setImgUrl(topic.getImageUrl());
      response.setLike(topic.getLikes().get(0).getLike());
      return response;
    } catch (DataAccessException e) {
      throw new InternalServerException("sorry save database");
    }
  }

  @Override
  public List<PostTopic> findAllTopic() {
    return iPostRepository.findAll();
  }

  @Override
  public List<PostTopic> searchByTitle(String title) {
    return iPostRepository.findByTitleContainingIgnoreCase(title);
  }

  @Override
  public Optional<PostTopic> searchById(Long id) {
    Optional<PostTopic> postTopic = iPostRepository.findById(id);
    if (postTopic.isPresent()) {
      return Optional.of(postTopic.get());
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
