package com.example.demopost.repository;

import com.example.demopost.data.enity.CommentQuestion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentQuestionRepository extends JpaRepository<CommentQuestion, Long> {
  @Query(value = "select * from comment_question where question_id = :question_id",nativeQuery = true)
  Optional<CommentQuestion> findById(@Param("question_id") Long id);
}
