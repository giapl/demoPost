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
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topic")
public class PostTopic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @Column(name = "title")
  private String title;
  @Column(name = "content")
  private String content;
  @Column(name = "image")
  private String imageUrl;
  @Column(name = "create_at")
  private LocalDateTime dateTime;
  @Column(name = "update_at")
  @CreationTimestamp
  private LocalDateTime updateTime;

  @JsonManagedReference
  @OneToMany(mappedBy = "postTopic", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LikePostTopic> likes = new ArrayList<>();


}
