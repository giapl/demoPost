package com.example.demopost.controller;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.example.demopost.data.request.QuestionRequest;
import com.example.demopost.data.response.QuestionResponse;
import com.example.demopost.service.Question.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home/CauHoi")
public class QuestionContronller {

  private IQuestionService iQuestionService;

  @Autowired
  public QuestionContronller(IQuestionService iQuestionService) {
    this.iQuestionService = iQuestionService;
  }
  @PostMapping("/question")
  public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequest question) {
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
}
