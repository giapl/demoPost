package com.example.demopost.controller;

import com.example.demopost.data.enity.LikePostTopic;
import com.example.demopost.data.request.PostRequest;
import com.example.demopost.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/postTopics")
public class PostTopicController {

  private IPostService iPostService;

  @Autowired
  public PostTopicController(IPostService iPostService) {
    this.iPostService = iPostService;
  }
/* comment by ducdv
 * tương tự bên question
 */
  @PostMapping("/topic")
  public ResponseEntity<?> createTopic(@RequestBody PostRequest post) {
    iPostService.createTopic(post);
    return ResponseEntity.status(HttpStatus.CREATED).body("successful created new a topic");
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAllTopic() {
    return ResponseEntity.ok(iPostService.findAllTopic());
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchByTitle(@RequestParam("title") String title) {
    return ResponseEntity.ok(iPostService.searchByTitle(title));
  }

  @GetMapping("/search/{id}")
  public ResponseEntity<?> searchById(@PathVariable long id) {
    return ResponseEntity.ok(iPostService.searchById(id));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    iPostService.deleteById(id);
    return ResponseEntity.ok("delete successful postTopic id : " + id);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody PostRequest postRequest) {
    iPostService.updateById(id, postRequest);
    return ResponseEntity.ok("update successful");
  }

  @PutMapping("/like/{post_id}")
  public ResponseEntity<?> increaseLike(@PathVariable("post_id") Long id, LikePostTopic like) {
    iPostService.increaseLikes(id, like);
    return ResponseEntity.ok("like postTopic successful");
  }

}
