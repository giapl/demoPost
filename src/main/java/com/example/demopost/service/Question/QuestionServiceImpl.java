package com.example.demopost.service.Question;

import com.example.demopost.convert.QuestionConvert;
import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.enity.LikeQuestion;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.data.response.CommentQuestionResponse;
import com.example.demopost.data.response.QuestionResponse;
import com.example.demopost.exception.NotFoundException;
import com.example.demopost.mapper.IComment;
import com.example.demopost.repository.ICommentQuestionRepository;
import com.example.demopost.repository.ILikeQuestionRepository;
import com.example.demopost.repository.IQuestionRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements IQuestionService {

  private final IQuestionRepository iQuestionRepository;

  private final QuestionConvert questionConvert;

  private final ILikeQuestionRepository iLikeQuestionRepository;

  private final ICommentQuestionRepository iCommentQuestionRepository;

  private final IComment iComment;




  @Autowired
  public QuestionServiceImpl(IQuestionRepository iQuestionRepository,
      QuestionConvert questionConvert, ILikeQuestionRepository iLikeQuestionRepository,
      ICommentQuestionRepository iCommentQuestionRepository, IComment iComment) {
    this.iQuestionRepository = iQuestionRepository;
    this.questionConvert = questionConvert;
    this.iLikeQuestionRepository = iLikeQuestionRepository;
    this.iCommentQuestionRepository = iCommentQuestionRepository;
    this.iComment = iComment;
  }


  @Override
  public Question createQuestion(QuestionRequest question) {
    Question question1 = new Question();
    question1.setContent(question.getContent());
    question1.setImageUrl(question.getImageUrl());
    question1.setDate(LocalDateTime.now());
    question1.setUpdateTime(LocalDateTime.now());

    // like tuong ung
    LikeQuestion likeQuestion = new LikeQuestion();
    likeQuestion.setLike(0);
    likeQuestion.setDateTime(LocalDateTime.now());
    likeQuestion.setQuestions(question1);

    List<LikeQuestion> like = new ArrayList<>();
    like.add(likeQuestion);
    question1.setLikeQuestions(like);
    return iQuestionRepository.save(question1);
  }


  @Override
  public List<QuestionResponse> finAllQuestion() {
    return iQuestionRepository.findAll().stream().map(questionConvert::ConvertEntityQuestion)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<QuestionResponse> searchId(long id) {
    Optional<Question> questionOptional = iQuestionRepository.searchById(id);
    // List<CommentQuestion> commentsInQuestion = iCommentQuestionRepository.findCommentQuestionsByQuestionId(id);
    List<CommentQuestion> commentQuestions = iCommentQuestionRepository.findCommentQuestionsByQuestionId(
        id);
    // mapper CommentQuestion -> CommentQuestionResponse
    List<CommentQuestionResponse> commentQuestionResponses = iComment.convertEntityCommentMapper(
        commentQuestions);

    if (questionOptional.isPresent()) {
      QuestionResponse questionResponse = questionConvert.ConvertEntityQuestion(
          questionOptional.get());
      // questionResponse.setCommentQuestions(commentsInQuestion);
      questionResponse.setCommentQuestions(commentQuestionResponses);
      //questionResponse.setNumComment(commentsInQuestion.size());
      questionResponse.setNumComment(commentQuestions.size());
      return Optional.of(questionResponse);
    } else {
      throw new NotFoundException("no id database");
    }
  }

  @Override
  public void deleteById(Long id) {
    if (iQuestionRepository.existsById(id)) {
      iQuestionRepository.deleteById(id);
    } else {
      throw new NotFoundException("no id or id question on delete successful");
    }
  }

  @Override
  public void updateById(Long id, QuestionRequest questionRequest) {
    Optional<Question> questionOptional = iQuestionRepository.findById(id);
    if (questionOptional.isPresent()) {
      Question question = questionOptional.get();
      question.setContent(questionRequest.getContent());
      question.setImageUrl(questionRequest.getImageUrl());
      question.setUpdateTime(LocalDateTime.now());
      iQuestionRepository.save(question);
    } else {
      throw new NotFoundException("no id");
    }
  }

  @Override
  public void increaseLike(Long id, LikeQuestion likeQuestion) {
    Optional<LikeQuestion> optionalLikeQuestion = iLikeQuestionRepository.findById(id);
    if (optionalLikeQuestion.isPresent()) {

      LikeQuestion likeQuestion1 = optionalLikeQuestion.get();
      likeQuestion1.setLike(likeQuestion1.getLike() + 1);
      iLikeQuestionRepository.save(likeQuestion1);
    } else {
      throw new NotFoundException("no question_id");
    }
  }

  @Override
  public List<QuestionResponse> setNewsQuestion() {
    return iQuestionRepository.findAllByIdOrderBy().stream()
        .map(questionConvert::ConvertEntityQuestion).collect(Collectors.toList());
  }


}
