package com.example.demopost.service.post;

import com.example.demopost.convert.PosTopicConvert;
import com.example.demopost.data.enity.CommentTopic;
import com.example.demopost.data.enity.LikePostTopic;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.data.response.CommentTopicResponse;
import com.example.demopost.data.response.PostResponse;
import com.example.demopost.exception.InternalServerException;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.mapper.ICommentTopic;
import com.example.demopost.repository.ICommentTopicRepository;
import com.example.demopost.repository.ILikeRepository;
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
public class PostServiceImpl implements IPostService {

  private final IPostRepository iPostRepository;

  private final ILikeRepository iLikeRepository;

  private final PosTopicConvert userConvert;

  private final ICommentTopicRepository commentTopicRepository;

  private final ICommentTopic iCommentTopic;

  @Autowired
  public PostServiceImpl(IPostRepository iPostRepository, ILikeRepository iLikeRepository,
      PosTopicConvert userConvert, ICommentTopicRepository commentTopicRepository,
      ICommentTopic iCommentTopic) {
    this.iPostRepository = iPostRepository;
    this.iLikeRepository = iLikeRepository;
    this.userConvert = userConvert;
    this.commentTopicRepository = commentTopicRepository;
    this.iCommentTopic = iCommentTopic;
  }

  @Override
  public PostTopic createTopic(PostRequest post) {
    PostTopic topic = new PostTopic();
    topic.setDateTime(LocalDateTime.now());
    topic.setTitle(post.getTitle());
    topic.setContent(post.getContent());
    topic.setImageUrl(post.getImageUrl());
    topic.setUpdateTime(LocalDateTime.now());
    // like tuong ung
    LikePostTopic like1 = new LikePostTopic();
    like1.setLike(0);
    like1.setPostTopic(topic);
    List<LikePostTopic> like = new ArrayList<>();
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
        .stream().map(userConvert::convertEntityToDo)
        .collect(Collectors.toList());
  }

  @Override
  public List<PostResponse> searchByTitle(String title) {
    try {
      List<PostTopic> postTopicList = iPostRepository.findByTitleContainingIgnoreCase(title);
      return postTopicList.stream().map(userConvert::convertEntityToDo)
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new NotFoundException("no title database");
    }
  }

  @Override
  public Optional<PostResponse> searchById(Long id) {
    Optional<PostTopic> postTopic = iPostRepository.findById(id);
    List<CommentTopic> commentTopic = commentTopicRepository.finCommentTopicByPostId(id);
    List<CommentTopicResponse> commentTopicResponses1 = iCommentTopic.convertEntityCommentTopic(
        commentTopic);
    if (postTopic.isPresent()) {
      PostResponse postResponse = userConvert.convertEntityToDo(postTopic.get());
      // add commentTopic vs numberComment
      postResponse.setCommentTopicResponses(commentTopicResponses1);
      postResponse.setNumberComment(commentTopic.size());

      return Optional.of(postResponse);

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

  @Override
  public void updateById(Long id, PostRequest postRequest) {
    Optional<PostTopic> topicOptional = iPostRepository.findById(id);
    if (topicOptional.isPresent()) {
      PostTopic postTopic = topicOptional.get();
      if (postRequest.getTitle() != null) {
        postTopic.setTitle(postRequest.getTitle());
      }
      if (postRequest.getContent() != null) {
        postTopic.setContent(postRequest.getContent());
      }
      if (postRequest.getImageUrl() != null) {
        postTopic.setImageUrl(postRequest.getImageUrl());
      }
      postTopic.setUpdateTime(LocalDateTime.now());
      iPostRepository.save(postTopic);
    } else {
      throw new NotFoundException("no id");
    }
  }

  @Override
  public void increaseLikes(Long id, LikePostTopic like) {
    Optional<LikePostTopic> optionalLike = iLikeRepository.findById(id);
    if (optionalLike.isPresent()) {
      LikePostTopic like1 = optionalLike.get();
      like1.setLike(like1.getLike() + 1); // tang like len 1 like khi tim dc pót_id
      like1.setDateLike(LocalDateTime.now());
      iLikeRepository.save(like1);
    } else {
      throw new NotFoundException("no post_id");
    }
  }

}



