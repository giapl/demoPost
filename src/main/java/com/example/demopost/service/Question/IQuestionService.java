package com.example.demopost.service.Question;


import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.QuestionRequest;
import java.util.List;
import java.util.Optional;

public interface IQuestionService {

  Question createQuestion(QuestionRequest question);
  List<Question> finAllQuestion();

  Optional<Question> searchId(long id);

}
