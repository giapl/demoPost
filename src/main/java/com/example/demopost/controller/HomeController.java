package com.example.demopost.controller;


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

  @Autowired
  public HomeController(IHomeService iHomeService) {
    this.iHomeService = iHomeService;
  }

  @GetMapping("/NoiBat")
  public ResponseEntity<?> setOutstanding() {
    return ResponseEntity.ok(iHomeService.setOutstanding());
  }

  @GetMapping("News")
  public ResponseEntity<?> setNews() {
    return ResponseEntity.ok(iHomeService.setNews());
  }

  @GetMapping("/CauHoi")
  public ResponseEntity<?> setQuestions() {
    return ResponseEntity.ok(iHomeService.setQuestions());
  }

  @GetMapping("/ChoBan")
  public ResponseEntity<?> get_ChoBan() {
    return ResponseEntity.ok(iHomeService.get_ChoBan());
  }
}
