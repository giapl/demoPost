package com.example.demopost.service.Question;

import com.example.demopost.convert.QuestionConvert;
import com.example.demopost.data.enity.LikeQuestion;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.data.response.QuestionResponse;
import com.example.demopost.exception.NotFoundException;
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
public class IQuestionServiceImpl implements IQuestionService {

  private IQuestionRepository iQuestionRepository;

  private QuestionConvert questionConvert;

  private ILikeQuestionRepository iLikeQuestionRepository;

  @Autowired
  public IQuestionServiceImpl(IQuestionRepository iQuestionRepository,
      QuestionConvert questionConvert, ILikeQuestionRepository iLikeQuestionRepository) {
    this.iQuestionRepository = iQuestionRepository;
    this.questionConvert = questionConvert;
    this.iLikeQuestionRepository = iLikeQuestionRepository;
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
    return iQuestionRepository.findAll()
        .stream()
        .map(questionConvert::ConvertEntityQuestion)
        .collect(Collectors.toList());
  }


  @Override
  public Optional<QuestionResponse> searchId(long id) {
    Optional<Question> questionOptional = iQuestionRepository.searchById(id);
    if (questionOptional.isPresent()) {
      return Optional.of(questionConvert.ConvertEntityQuestion(questionOptional.get()));
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
    if(optionalLikeQuestion.isPresent()){

      LikeQuestion likeQuestion1 = optionalLikeQuestion.get();
      likeQuestion1.setLike(likeQuestion1.getLike()+1);
      iLikeQuestionRepository.save(likeQuestion1);
    } else {
      throw  new NotFoundException("no question_id");
    }
  }
}
