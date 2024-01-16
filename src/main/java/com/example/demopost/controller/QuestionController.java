package com.example.demopost.controller;

import com.example.demopost.data.enity.LikeQuestion;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.service.Question.IQuestionService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

  private final IQuestionService iQuestionService;


  @Autowired
  public QuestionController(IQuestionService iQuestionService) {
    this.iQuestionService = iQuestionService;
  }
/* comment by ducdv
 * ở trên có questions rồi thì chỗ này nên bỏ /question hoặc thay bằng /save or /create
 */
  @PostMapping("/question")
  public ResponseEntity<?> createQuestion(@RequestBody QuestionRequest question) {
    iQuestionService.createQuestion(question);
    return ResponseEntity.status(HttpStatus.CREATED).body("successful created new a question");
  }

  @GetMapping("/all")
  public ResponseEntity<?> finAllQuestion() {
    return ResponseEntity.ok(iQuestionService.finAllQuestion());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> searchId(@PathVariable long id) {
    return ResponseEntity.ok(iQuestionService.searchId(id));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    iQuestionService.deleteById(id);
    return ResponseEntity.ok("delete successful question id : " + id);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateById(@PathVariable Long id,
      @RequestBody QuestionRequest questionRequest) {
    iQuestionService.updateById(id, questionRequest);
    return ResponseEntity.ok("update successful");
  }

  @PutMapping("/like/{question_id}")
  public ResponseEntity<?> increaseLike(@PathVariable("question_id") Long id,
      LikeQuestion likeQuestion) {
    iQuestionService.increaseLike(id, likeQuestion);
    return ResponseEntity.ok("like successful");
  }

  @GetMapping("/news")
  public ResponseEntity<?> setNewsQuestion() {
    return ResponseEntity.ok(iQuestionService.setNewsQuestion());
  }

}
