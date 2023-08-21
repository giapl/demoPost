package com.example.demopost.controller;

import com.example.demopost.data.enity.PostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home/ChoBan")
public class PostController {

  private IPostService iPostService;

  @Autowired
  public PostController(IPostService iPostService) {
    this.iPostService = iPostService;
  }

  @PostMapping("/topic")
  public ResponseEntity<PostTopic> create_Topic(@RequestBody PostRequest post) {
    return ResponseEntity.ok(iPostService.create_Topic(post));
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAll_Topic() {
    return ResponseEntity.ok(iPostService.findAll_Topic());
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchByTitle(@RequestParam("title") String title) {
    return ResponseEntity.ok(iPostService.searchByTitle(title));
  }
}
