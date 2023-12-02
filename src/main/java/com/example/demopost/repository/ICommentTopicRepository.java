package com.example.demopost.repository;

import com.example.demopost.data.enity.CommentTopic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentTopicRepository extends JpaRepository<CommentTopic, Long> {
  @Query(value = "select * from comment_topic where post_id = :post_id",nativeQuery = true)
  List<CommentTopic> finCommentTopicByPostId(@Param("post_id") Long id);

}
