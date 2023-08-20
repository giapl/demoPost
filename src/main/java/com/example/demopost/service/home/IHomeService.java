package com.example.demopost.service.home;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.enity.Question;
import java.util.List;

public interface IHomeService {

  List<PostTopic> get_ChoBan();
  List<PostTopic> get_NoiBat();
  List<PostTopic> get_News();
  List<Question> get_CauHoi();

}
