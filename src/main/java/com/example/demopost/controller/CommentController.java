package com.example.demopost.controller;

import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.service.comment.ICommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by DucDV on 27-10-2023 HN, VN.
 */

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

  private final ICommentService commentService;

  public CommentController(ICommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("/create")
  public ResponseEntity<?> createComment(
      @RequestBody CommentQuestionRequest commentQuestionRequest) {
    commentService.createComment(commentQuestionRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("successful created new a comment");
  }
}
