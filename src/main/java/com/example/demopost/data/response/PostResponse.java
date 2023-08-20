package com.example.demopost.data.response;



import java.time.LocalDate;
import lombok.Data;

@Data
public class PostResponse {

  private String title;
  private String content;
  private LocalDate dateTime;
  private int like;
  private String comment;
  private int share;
}
