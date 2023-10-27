package com.example.demopost.data.enity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment_question")
public class CommentQuestion {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private long id;

  @Column(name = "question_id")
  private long questionId;

  @Column(name = "content")
  private String content;




  @Column(name = "comment")
  private long comment;

  @Column(name = "image")
  private String imageUrl;
  @Column(name = "create_at")
  private LocalDateTime dateTime;
  @Column(name = "update_at")
  private LocalDateTime updateTime;


//  @JsonBackReference
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "question_id")
//  private Question question;

}
