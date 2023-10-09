package com.example.demopost.data.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostShareResponse {

  private Long id;
  private LocalDateTime dateTime;
  private String title;
  private String content;
  private PostResponse topicShare;
  private Long like;
  private Long share;

}
