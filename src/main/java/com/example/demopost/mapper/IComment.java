package com.example.demopost.mapper;

import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.enity.CommentQuestionLeverTwo;
import com.example.demopost.data.request.CommentLeverTwoRequest;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.response.CommentLeverTwoResponse;
import com.example.demopost.data.response.CommentQuestionResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IComment {

  CommentQuestion convertEntityComment(CommentQuestionRequest commentQuestionRequest);

  List<CommentQuestionResponse> convertEntityCommentMapper(List<CommentQuestion> commentQuestion);

  CommentQuestionLeverTwo convertEntityCommentLeverTwo(CommentLeverTwoRequest commentLeverTwoRequest);

  CommentLeverTwoResponse convertEntityCommentQuestion(CommentQuestionLeverTwo commentQuestionLeverTwo);

  List<CommentLeverTwoResponse> convertEntityCommentQuestionLeverTwo(List<CommentQuestionLeverTwo> commentQuestionLeverTwos);

}
