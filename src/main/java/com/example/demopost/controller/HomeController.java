package com.example.demopost.controller;

import com.example.demopost.service.Question.IQuestionService;
import com.example.demopost.service.home.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

  private IHomeService iHomeService;
  private IQuestionService iQuestionService;
  @Autowired
  public HomeController(IHomeService iHomeService, IQuestionService iQuestionService) {
    this.iHomeService = iHomeService;
    this.iQuestionService = iQuestionService;
  }

  @GetMapping("/NoiBat")
  public ResponseEntity<?> get_NoiBat() {
    return ResponseEntity.ok(iHomeService.get_NoiBat());
  }
  @GetMapping("News")
  public ResponseEntity<?> get_News(){
    return ResponseEntity.ok(iHomeService.get_News());
  }
  @GetMapping("/CauHoi")
  public ResponseEntity<?> get_CauHoi(){
    return ResponseEntity.ok(iQuestionService.finAll_Question());
  }
  @GetMapping("/ChoBan")
  public ResponseEntity<?> get_ChoBan() {
    return ResponseEntity.ok(iHomeService.get_ChoBan());
  }
}
