package com.example.demopost.service.home;

import com.example.demopost.convert.UserConvert;
import com.example.demopost.data.enity.PostTopic;
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

  private UserConvert userConvert;


  @Autowired
  public IHomeServiceImpl(IPostRepository iPostRepository,
      IQuestionRepository iQuestionRepository, UserConvert userConvert) {
    this.iPostRepository = iPostRepository;
    this.iQuestionRepository = iQuestionRepository;
    this.userConvert = userConvert;
  }

  @Override
  public List<PostResponse> getChoBan() {
    return iPostRepository.findAll()
        .stream()
        .map(userConvert ::convertEntityToDo)
        .collect(Collectors.toList());
  }

  @Override
  public List<PostTopic> setOutstanding() {
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
        .map(userConvert::convertEntityTODo1)
        .collect(Collectors.toList());
  }
}
