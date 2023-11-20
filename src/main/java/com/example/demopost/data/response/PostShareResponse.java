package com.example.demopost.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostShareResponse {

  private Long id;
  @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
  private LocalDateTime dateTime;
  private String title;
  private String content;
  private PostResponse topicShare;
  private Long like;
  private Long share;

}
