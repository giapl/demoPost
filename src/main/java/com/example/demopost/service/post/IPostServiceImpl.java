package com.example.demopost.service.post;

import com.example.demopost.data.enity.Like;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.exception.InternalServerException;
import com.example.demopost.repository.IPostRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
  public PostTopic createTopic(PostRequest post) {
    PostTopic topic = new PostTopic();
    topic.setTitle(post.getTitle());
    topic.setContent(post.getContent());
    topic.setImageUrl(post.getImageUrl());
    topic.setDateTime(LocalDateTime.now());
    // tao like tuong ung
    Like like1 = new Like();
    like1.setLike(0); // set like = 0
    like1.setPostTopic(topic); // lien ket voi postTopic
    List<Like> likes = new ArrayList<>();
    likes.add(like1);
    topic.setLikes(likes);
    try {
      return iPostRepository.save(topic);
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
}
