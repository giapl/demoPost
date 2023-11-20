package com.example.demopost.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentQuestionResponse {

  private long id;
  private String content;
  private String imageUrl;
  @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
  private LocalDateTime dateTime;
}
