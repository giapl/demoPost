package com.example.demopost.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentTopicResponse {

  private Long id;
  private String content;
  private String imageUrl;
  @JsonFormat(pattern = "yyyy:MM:dd HH:mm:ss")
  private String dateTime;

}
