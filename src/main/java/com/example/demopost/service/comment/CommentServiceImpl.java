package com.example.demopost.service.comment;

import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.enity.CommentQuestionLeverTwo;
import com.example.demopost.data.enity.CommentTopic;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.CommentLeverTwoRequest;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.request.CommentTopicRequest;
import com.example.demopost.data.response.CommentLeverTwoResponse;
import com.example.demopost.data.response.CommentQuestionResponse;
import com.example.demopost.data.response.CommentTopicResponse;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.mapper.IComment;
import com.example.demopost.mapper.ICommentTopic;
import com.example.demopost.repository.ICommentQuestionLeverTwoRepository;
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

  private final ICommentQuestionLeverTwoRepository leverTwoRepository;

  @Autowired
  public CommentServiceImpl(IQuestionRepository questionRepository,
      ICommentQuestionRepository commentQuestionRepository,
      IComment comment, ICommentTopic commentTopic,
      ICommentTopicRepository commentTopicRepository, IPostRepository postRepository,
      ICommentQuestionLeverTwoRepository leverTwoRepository) {
    this.questionRepository = questionRepository;
    this.commentQuestionRepository = commentQuestionRepository;
    this.comment = comment;
    this.commentTopic = commentTopic;
    this.commentTopicRepository = commentTopicRepository;
    this.postRepository = postRepository;
    this.leverTwoRepository = leverTwoRepository;
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
    /* comment by ducdv
 * Sao ở trên dùng mapper rồi mà vấn cần set ở dưới
 * nên xử lý hết trong mapper
 */
    commentQuestion.setQuestionId(questionById.get().getId());
    commentQuestion.setContent(commentQuestionRequest.getContent());
    commentQuestion.setImageUrl(commentQuestionRequest.getImageUrl());
    commentQuestion.setDateTime(LocalDateTime.now());
    commentQuestion.setUpdateTime(LocalDateTime.now());
    // b2: save  CommentQuestion commentSaved = commentQuestionRepository.save();

    /* comment by ducdv
 * xem lại cách đặt tên biến, ko nên dùng số sau mỗi biến. nên đặt tên biến có ý nghĩa
 */
    CommentQuestion commentQuestion1 = commentQuestionRepository.save(commentQuestion);
    // create commentResponse
    CommentQuestionResponse response = comment.convertComment(commentQuestion1);
    return response;
  }

  @Override
  @Transactional
  public CommentTopicResponse createCommentTopic(CommentTopicRequest commentTopicRequest) {
    Optional<PostTopic> optionalPostTopic = postRepository.findById(
        commentTopicRequest.getPostId());
    if (!optionalPostTopic.isPresent()) {
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

  @Override
  @Transactional
  public CommentLeverTwoResponse createCommentLeverTwo(
      CommentLeverTwoRequest commentLeverTwoRequest) {
    Optional<CommentQuestion> optionalCommentQuestion = commentQuestionRepository.findByIdAndQuestionId(
        commentLeverTwoRequest.getQuestionId(), commentLeverTwoRequest.getCommentId()
    ); // search id_comment and question id trong commentQuestion
    if (!optionalCommentQuestion.isPresent()) {
      throw new NotFoundException("Comment not have in system");
    }
    CommentQuestionLeverTwo commentQuestion = comment.convertEntityCommentLeverTwo(
        commentLeverTwoRequest); // map commentRequest sang commentQuestionLeverTwo

    // create comment cho commentQuestion
    commentQuestion.setQuestionId(optionalCommentQuestion.get().getQuestionId());
    commentQuestion.setCommentQuestionId(optionalCommentQuestion.get().getId());
    commentQuestion.setContent(commentLeverTwoRequest.getContent());
    commentQuestion.setImageUrl(commentLeverTwoRequest.getImageUrl());
    commentQuestion.setDateTime(LocalDateTime.now());
    commentQuestion.setUpdateTime(LocalDateTime.now());
    CommentQuestionLeverTwo commentQuestion1 = leverTwoRepository.save(
        commentQuestion);  // luu vao database
    CommentLeverTwoResponse commentLeverTwoResponse = comment.convertEntityCommentQuestion(
        commentQuestion1); // convert sang response
    return commentLeverTwoResponse;
  }

}
