package com.example.demopost.service.home;

import com.example.demopost.convert.QuestionConvert;
import com.example.demopost.convert.PosTopicConvert;
import com.example.demopost.data.response.PostResponse;
import com.example.demopost.data.response.QuestionResponse;
import com.example.demopost.repository.IPostRepository;
import com.example.demopost.repository.IQuestionRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IHomeServiceImpl implements IHomeService {

  private IPostRepository iPostRepository;
  private IQuestionRepository iQuestionRepository;

  private PosTopicConvert userConvert;

  private QuestionConvert questionConvert;

  @Autowired
  public IHomeServiceImpl(IPostRepository iPostRepository,
      IQuestionRepository iQuestionRepository, PosTopicConvert userConvert,
      QuestionConvert questionConvert) {
    this.iPostRepository = iPostRepository;
    this.iQuestionRepository = iQuestionRepository;
    this.userConvert = userConvert;
    this.questionConvert=questionConvert;
  }

  @Override
  public List<PostResponse> getChoBan() {
    return iPostRepository.findAll()
        .stream()
        .map(userConvert ::convertEntityToDo)
        .collect(Collectors.toList());
  }

  @Override
  public List<PostResponse> setOutstanding() {
    return null;
  }

  @Override
  public List<PostResponse> setNews() {
    return iPostRepository.findAllBYIdOrderBy()
        .stream()
        .map(userConvert::convertEntityToDo)
        .collect(Collectors.toList());
  }

  @Override
  public List<QuestionResponse> setQuestions() {
    return iQuestionRepository.findAll()
        .stream()
        .map(questionConvert::ConvertEntityQuestion)
        .collect(Collectors.toList());
  }
}
