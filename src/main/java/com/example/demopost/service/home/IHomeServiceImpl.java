package com.example.demopost.service.home;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.repository.IPostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IHomeServiceImpl implements IHomeService {

  private IPostRepository iPostRepository;

  @Autowired
  public IHomeServiceImpl(IPostRepository iPostRepository) {
    this.iPostRepository = iPostRepository;
  }

  @Override
  public List<PostTopic> get_ChoBan() {
    return null;
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
  public List<PostTopic> get_CauHoi() {
    return null;
  }
}
