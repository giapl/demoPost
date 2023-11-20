package com.example.demopost.mapper;

import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.response.CommentQuestionResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IComment {

  CommentQuestion convertEntityComment(CommentQuestionRequest commentQuestionRequest);

  List<CommentQuestionResponse> convertEntityCommentMapper(List<CommentQuestion> commentQuestion);

}
