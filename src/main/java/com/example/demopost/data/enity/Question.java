package com.example.demopost.data.enity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "content")
  private String content;


  @Column(name = "image_url")
  private String imageUrl;
  @Column(name = "update_at")
  private LocalDateTime updateTime;
  @Column(name = "create_at")
  private LocalDateTime date;

  @JsonManagedReference
  @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LikeQuestion> likeQuestions = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL,orphanRemoval = true)
  private List<CommentQuestion> commentQuestions;
}
