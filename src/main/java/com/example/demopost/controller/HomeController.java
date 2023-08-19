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
  public ResponseEntity<?> get_NoiBat() {
    return ResponseEntity.ok(iHomeService.get_NoiBat());
  }
  @GetMapping("News")
  public ResponseEntity<?> get_News(){
    return ResponseEntity.ok(iHomeService.get_News());
  }
  @GetMapping("/CauHoi")
  public ResponseEntity<?> get_CauHoi(){
    return ResponseEntity.ok(iHomeService.get_CauHoi());
  }
  @GetMapping("/ChoBan")
  public ResponseEntity<?> get_ChoBan() {
    return ResponseEntity.ok(iHomeService.get_ChoBan());
  }
}
