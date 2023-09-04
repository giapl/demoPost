package com.example.demopost.service.Question;


import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.data.response.QuestionResponse;
import java.util.List;
import java.util.Optional;

public interface IQuestionService {

  QuestionResponse createQuestion(QuestionRequest question); // method them cau hoi

  List<QuestionResponse> finAllQuestion(); // method hien thi all cau hoi

  Optional<QuestionResponse> searchId(long id); // method search cau hoi theo id

  void deleteById(Long id); // method delete question theo id

}
