package com.example.demopost.service.Question;


import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.enity.LikeQuestion;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.data.response.QuestionResponse;
import java.util.List;
import java.util.Optional;

public interface IQuestionService {

  Question createQuestion(QuestionRequest question); // method them cau hoi

  List<QuestionResponse> finAllQuestion(); // method hien thi all cau hoi

  Optional<QuestionResponse> searchId(long id); // method search cau hoi theo id

  void deleteById(Long id); // method delete question theo id

  void updateById(Long id, QuestionRequest questionRequest); // method update question theo id

  void increaseLike(Long id, LikeQuestion likeQuestion);    // method like question theo question_id

  List<QuestionResponse> setNewsQuestion(); // method lay question moi theo id

  CommentQuestion createComment(Long id, Question question,
      CommentQuestionRequest commentQuestionRequest); // method tao tra loi cap 1


}
