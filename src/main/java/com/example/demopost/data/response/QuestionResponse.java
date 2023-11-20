package com.example.demopost.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class QuestionResponse {

  private long id;
  @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
  private LocalDateTime date;
  private String content;
  private String imageUrl;
  private long like;
  private long numComment;
  private List<CommentQuestionResponse> commentQuestions;
}
