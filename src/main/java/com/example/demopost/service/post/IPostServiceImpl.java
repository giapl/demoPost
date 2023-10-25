package com.example.demopost.service.post;

import com.example.demopost.convert.PosTopicConvert;
import com.example.demopost.convert.PostShareConvert;
import com.example.demopost.data.enity.LikePostTopic;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.data.response.PostResponse;
import com.example.demopost.data.response.PostShareResponse;
import com.example.demopost.exception.InternalServerException;
import com.example.demopost.exception.NotFoundException;
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
public class IPostServiceImpl implements IPostService {

  private final IPostRepository iPostRepository;

  private final ILikeRepository iLikeRepository;

  private final PosTopicConvert userConvert;

  private final PostShareConvert postShareConvert;

  @Autowired
  public IPostServiceImpl(IPostRepository iPostRepository, ILikeRepository iLikeRepository,
      PosTopicConvert userConvert, PostShareConvert postShareConvert) {
    this.iPostRepository = iPostRepository;
    this.iLikeRepository = iLikeRepository;
    this.userConvert = userConvert;
    this.postShareConvert = postShareConvert;
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
    // share tuong ung
    LikePostTopic share1 = new LikePostTopic();
    share1.setShare(0);
    share1.setPostTopic(topic);
    List<LikePostTopic> share = new ArrayList<>();
    share.add(share1);
    topic.setLikes(share);
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

  @Override
  public void updateById(Long id, PostRequest postRequest) {
    Optional<PostTopic> topicOptional = iPostRepository.findById(id);
    if (topicOptional.isPresent()) {
      PostTopic postTopic = topicOptional.get();
      postTopic.setTitle(postRequest.getTitle());
      postTopic.setContent(postRequest.getContent());
      postTopic.setImageUrl(postRequest.getImageUrl());
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

  @Override
  public PostShareResponse increaseShare(Long id, LikePostTopic like, PostRequest postRequest ) {

    Optional<LikePostTopic> optionalShare = iLikeRepository.findById(id); // tim id bai post_id trong like
    if (optionalShare.isPresent()) {
      LikePostTopic share = optionalShare.get(); // lay share
      // tim bai viet goc
      Optional<PostTopic> optionalPostTopicShare = iPostRepository.findById(id);
      PostTopic sharePostTopic = optionalPostTopicShare.get();

      // lay bai viet goc ra tu postResponse
      PostResponse response = new PostResponse();
      response.setId(sharePostTopic.getId());
      response.setDateTime(sharePostTopic.getDateTime());
      response.setTitle(sharePostTopic.getTitle());
      response.setContent(sharePostTopic.getContent());
      response.setImageUrl(sharePostTopic.getImageUrl());
      response.setLike(sharePostTopic.getLikes().get(0).getLike());
      response.setShare(sharePostTopic.getLikes().get(0).getShare());

      // long vao object goc vao object chia se
      PostTopic topic = new PostTopic();
      topic.setTitle(postRequest.getTitle());
      topic.setContent(String.valueOf(response));
      topic.setImageUrl(postRequest.getImageUrl());
      topic.setDateTime(LocalDateTime.now());
      // tang share bai viet goc
      share.setDateShare(LocalDateTime.now());
      share.setShare(share.getShare()+1);

      LikePostTopic like1 = new LikePostTopic();
      like1.setLike(0);
      like1.setPostTopic(topic);
      LikePostTopic share1 = new LikePostTopic();
      share1.setShare(0);
      share1.setPostTopic(topic);

      List<LikePostTopic> likeList = new ArrayList<>();
      likeList.add(like1);
      likeList.add(share1);
      topic.setLikes(likeList);
      //
      iPostRepository.save(topic);

      return postShareConvert.shareResponse(topic);
    } else {
      throw new NotFoundException("no id topic");
    }
  }

}



