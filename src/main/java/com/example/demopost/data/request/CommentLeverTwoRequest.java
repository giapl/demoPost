package com.example.demopost.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentLeverTwoRequest {

  private Long questionId;
  private Long CommentId;
  private String Content;
  private String imageUrl;

}
