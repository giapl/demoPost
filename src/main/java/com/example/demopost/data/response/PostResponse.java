package com.example.demopost.data.response;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostResponse {

  private LocalDateTime dateTime;
  private String title;
  private String content;
  private String imgUrl;
  private long like;
}
