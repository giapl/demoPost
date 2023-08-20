package com.example.demopost.service.Question;


import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import java.util.List;

public interface IQuestionService {

  Question create_Question(QuestionRequest question);
  List<Question> finAll_Question();

}
