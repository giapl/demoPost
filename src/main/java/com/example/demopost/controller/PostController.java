package com.example.demopost.controller;

import com.example.demopost.data.request.PostRequest;
import com.example.demopost.data.response.PostResponse;
import com.example.demopost.service.post.IPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity<?> createTopic(@RequestBody PostRequest post) {
    return ResponseEntity.ok(iPostService.createTopic(post));
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAllTopic() {
    return ResponseEntity.ok(iPostService.findAllTopic());
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchByTitle(@RequestParam("title") String title) {
    return ResponseEntity.ok(iPostService.searchByTitle(title));
  }

  @GetMapping("/search/id")
  public ResponseEntity<?> searchById(@RequestParam long id) {
    return ResponseEntity.ok(iPostService.searchById(id));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteById(@RequestParam Long id) {
    iPostService.deleteById(id);
    return ResponseEntity.ok("delete successful postTopic id : " + id);
  }
  @GetMapping("/all-PostResponse")
  public List<PostResponse> getAllPostResponse() {
    return iPostService.getAllPostResponse();
  }
}
