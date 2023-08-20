package com.example.demopost.data.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class QuestionResponse {

  private LocalDateTime date;
  private String content;
  private String imgUrl;
  private String comment;

}
