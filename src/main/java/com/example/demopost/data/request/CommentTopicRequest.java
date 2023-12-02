package com.example.demopost.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentTopicRequest {

  private Long postId;

  private String content;

  private String imageUrl;

}
