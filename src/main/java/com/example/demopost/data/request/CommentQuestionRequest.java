package com.example.demopost.data.request;

import lombok.Data;

@Data
public class CommentQuestionRequest {

  private Long questionId;
  private String content;
  private String imageUrl;

}
