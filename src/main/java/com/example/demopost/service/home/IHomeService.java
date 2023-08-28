package com.example.demopost.service.home;

import com.example.demopost.data.enity.Like;
import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.enity.Question;
import java.util.List;

public interface IHomeService {

  List<PostTopic> getChoBan(); // method hien thi toan bo bai post

  List<PostTopic> setOutstanding(); // method lay cac bai post noi bat theo so like

  List<PostTopic> setNews(); // method lay cac bai viet moi dang len dau tien

  List<Question> setQuestions(); // method hien thi toan bo cau hoi

}
