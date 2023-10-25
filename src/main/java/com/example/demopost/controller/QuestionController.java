package com.example.demopost.controller;


import com.example.demopost.data.enity.LikeQuestion;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.service.Question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/home/CauHoi")
public class QuestionController {

  private final IQuestionService iQuestionService;

  @Autowired
  public QuestionController(IQuestionService iQuestionService) {
    this.iQuestionService = iQuestionService;
  }

  @PostMapping("/question")
  public ResponseEntity<?> createQuestion(@RequestBody QuestionRequest question) {
    return ResponseEntity.ok(iQuestionService.createQuestion(question));
  }

  @GetMapping("/all")
  public ResponseEntity<?> finAllQuestion() {
    return ResponseEntity.ok(iQuestionService.finAllQuestion());
  }

  @GetMapping("/id")
  public ResponseEntity<?> searchId(@RequestParam long id) {
    return ResponseEntity.ok(iQuestionService.searchId(id));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteById(@RequestParam Long id) {
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

  @GetMapping("/News")
  public ResponseEntity<?> setNewsQuestion() {
    return ResponseEntity.ok(iQuestionService.setNewsQuestion());
  }
  @PutMapping("/comment/{question_id}")
  public ResponseEntity<?> createComment(@PathVariable("question_id") Long id , Question question , @RequestBody CommentQuestionRequest commentQuestionRequest){
    iQuestionService.createComment(id , question , commentQuestionRequest);
    return ResponseEntity.ok("comment successful");
  }
}
