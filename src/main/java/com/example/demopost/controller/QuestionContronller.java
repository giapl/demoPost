package com.example.demopost.controller;

import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.service.Question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class QuestionContronller {

  private IQuestionService iQuestionService;

  @Autowired
  public QuestionContronller(IQuestionService iQuestionService) {
    this.iQuestionService = iQuestionService;
  }
  @PostMapping("/question")
  public ResponseEntity<?> create_Question(@RequestBody QuestionRequest question) {
    return ResponseEntity.ok(iQuestionService.create_Question(question));
  }
  @GetMapping("/all")
  public ResponseEntity<?> finAll_Question() {
    return ResponseEntity.ok(iQuestionService.finAll_Question());
  }
}
