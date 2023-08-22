package com.example.demopost.service.home;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.enity.Question;
import java.util.List;

public interface IHomeService {

  List<PostTopic> getChoBan();

  List<PostTopic> setOutstanding();

  List<PostTopic> setNews();

  List<Question> setQuestions();

}
