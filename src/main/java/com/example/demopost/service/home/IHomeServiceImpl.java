package com.example.demopost.service.home;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.enity.Question;
import com.example.demopost.repository.IPostRepository;
import com.example.demopost.repository.IQuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IHomeServiceImpl implements IHomeService {

  private IPostRepository iPostRepository;
  private IQuestionRepository iQuestionRepository;

  @Autowired
  public IHomeServiceImpl(IPostRepository iPostRepository, IQuestionRepository iQuestionRepository) {
    this.iPostRepository = iPostRepository;
    this.iQuestionRepository = iQuestionRepository;
  }

  @Override
  public List<PostTopic> get_ChoBan() {
    return iPostRepository.findAll();
  }

  @Override
  public List<PostTopic> get_NoiBat() {
    return iPostRepository.findAllByLikeOrderBy();
  }

  @Override
  public List<PostTopic> get_News() {
    return iPostRepository.findAllBYIdOrderBy();
  }

  @Override
  public List<Question> get_CauHoi() {
    return iQuestionRepository.findAll();
  }
}
