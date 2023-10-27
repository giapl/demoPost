package com.example.demopost.data.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentQuestionResponse {

  private long id;
  private String content;
  private String imageUrl;
  private LocalDateTime dateTime;
}
