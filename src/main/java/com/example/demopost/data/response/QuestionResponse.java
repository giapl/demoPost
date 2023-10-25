package com.example.demopost.data.response;

import com.example.demopost.data.enity.CommentQuestion;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class QuestionResponse {

  private long id;
  private LocalDateTime date;
  private String content;
  private String imageUrl;
  private long like;
  private long comment;
  private List<CommentQuestion> commentQuestions;
}
