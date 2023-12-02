package com.example.demopost.service.comment;

import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.enity.CommentTopic;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.request.CommentTopicRequest;
import com.example.demopost.data.response.CommentQuestionResponse;
import com.example.demopost.data.response.CommentTopicResponse;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.mapper.IComment;
import com.example.demopost.mapper.ICommentTopic;
import com.example.demopost.repository.ICommentQuestionRepository;
import com.example.demopost.repository.ICommentTopicRepository;
import com.example.demopost.repository.IPostRepository;
import com.example.demopost.repository.IQuestionRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by DucDV on 27-10-2023 HN, VN.
 */
@Service
public class CommentServiceImpl implements ICommentService {

  private final IQuestionRepository questionRepository;
  private final ICommentQuestionRepository commentQuestionRepository;
  private final IComment comment;

  private final ICommentTopic commentTopic;

  private final ICommentTopicRepository commentTopicRepository;

  private final IPostRepository postRepository;

  @Autowired
  public CommentServiceImpl(IQuestionRepository questionRepository,
      ICommentQuestionRepository commentQuestionRepository,
      IComment comment, ICommentTopic commentTopic,
      ICommentTopicRepository commentTopicRepository, IPostRepository postRepository) {
    this.questionRepository = questionRepository;
    this.commentQuestionRepository = commentQuestionRepository;
    this.comment = comment;
    this.commentTopic = commentTopic;
    this.commentTopicRepository = commentTopicRepository;
    this.postRepository = postRepository;
  }

  @Override
  @Transactional
  public CommentQuestionResponse createComment(CommentQuestionRequest commentQuestionRequest) {
    // kiem tra xem question co ton tai hay k
    Optional<Question> questionById = questionRepository.findById(
        commentQuestionRequest.getQuestionId());
    if (!questionById.isPresent()) {
      throw new RuntimeException("question not have in system");
    }
    // b1: tạo ra 1 mapper : map commentQuestionRequest to comment entity
    CommentQuestion commentQuestion = comment.convertEntityComment(commentQuestionRequest);
    commentQuestion.setQuestionId(questionById.get().getId());
    commentQuestion.setContent(commentQuestionRequest.getContent());
    commentQuestion.setImageUrl(commentQuestionRequest.getImageUrl());
    commentQuestion.setDateTime(LocalDateTime.now());
    commentQuestion.setUpdateTime(LocalDateTime.now());
    // b2: save  CommentQuestion commentSaved = commentQuestionRepository.save();
    CommentQuestion commentQuestion1 = commentQuestionRepository.save(commentQuestion);
    // create commentResponse
    CommentQuestionResponse response = new CommentQuestionResponse();
    response.setId(commentQuestion1.getId());
    response.setContent(commentQuestion1.getContent());
    response.setImageUrl(commentQuestion1.getImageUrl());
    response.setDateTime(LocalDateTime.now());
    return response;
  }

  @Override
  public CommentTopicResponse createCommentTopic(CommentTopicRequest commentTopicRequest) {
    Optional<PostTopic> optionalPostTopic = postRepository.findById(commentTopicRequest.getPostId());
    if(!optionalPostTopic.isPresent()) {
      throw new NotFoundException("postTopic not have in system");
    }
    CommentTopic commentTopic1 = commentTopic.convertEntity(commentTopicRequest);

    commentTopic1.setPostId(optionalPostTopic.get().getId());
    commentTopic1.setContent(commentTopicRequest.getContent());
    commentTopic1.setImageUrl(commentTopicRequest.getImageUrl());
    commentTopic1.setDateTime(LocalDateTime.now());
    commentTopic1.setUpdateTime(LocalDateTime.now());

    CommentTopic commentTopic2 = commentTopicRepository.save(commentTopic1);
    // luu ve response
    CommentTopicResponse commentTopicResponse = commentTopic.convertTopicResponse(commentTopic2);
    return commentTopicResponse;
  }

}
