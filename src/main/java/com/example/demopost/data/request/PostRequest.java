package com.example.demopost.data.request;

import lombok.Data;

@Data
public class PostRequest {

  private String title;
  private String content;
  private String imageUrl;
}
