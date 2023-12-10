package com.example.demopost.repository;

import com.example.demopost.data.enity.CommentQuestionLeverTwo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentQuestionLeverTwoRepository extends JpaRepository<CommentQuestionLeverTwo, Long> {

  @Query(value = "select * from comment_lever_two where comment_id = :comment_id and question_id = :question_id", nativeQuery = true)
  List<CommentQuestionLeverTwo> findAllByCommentQuestionIdAndQuestionId(
      @Param("comment_id") Long commentId, @Param("question_id") Long questionId);

  @Query(value = "select * from comment_lever_two where question_id = :question_id",nativeQuery = true)
  List<CommentQuestionLeverTwo> findByQuestionId(@Param("question_id") Long id);
}
