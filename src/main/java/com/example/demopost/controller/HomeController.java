package com.example.demopost.controller;


import com.example.demopost.service.home.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/homes")
public class HomeController {

  private final IHomeService iHomeService;

  @Autowired
  public HomeController(IHomeService iHomeService) {
    this.iHomeService = iHomeService;
  }

  @GetMapping("/outstanding")
  public ResponseEntity<?> setOutstanding() {
    return ResponseEntity.ok(iHomeService.setOutstanding());
  }

  @GetMapping("/news")
  public ResponseEntity<?> setNews() {
    return ResponseEntity.ok(iHomeService.setNews());
  }

  @GetMapping("/question")
  public ResponseEntity<?> setQuestions() {
    return ResponseEntity.ok(iHomeService.setQuestions());
  }

  @GetMapping("/choBan")
  public ResponseEntity<?> getChoBan() {
    return ResponseEntity.ok(iHomeService.getChoBan());
  }
}
