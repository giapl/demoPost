package com.example.demopost.service.home;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.response.PostResponse;
import com.example.demopost.data.response.QuestionResponse;
import java.util.List;

public interface IHomeService {

  List<PostResponse> getChoBan(); // method hien thi toan bo bai post

  List<PostResponse> setOutstanding(); // method lay cac bai post noi bat theo so like

  List<PostResponse> setNews(); // method lay cac bai viet moi dang len dau tien

  List<QuestionResponse> setQuestions(); // method hien thi toan bo cau hoi

}
