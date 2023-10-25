package com.example.demopost.repository;

import com.example.demopost.data.enity.LikeQuestion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeQuestionRepository extends JpaRepository<LikeQuestion , Long > {

  @Query(value = "select * from like_question where question_id = :question_id",nativeQuery = true)
   Optional<LikeQuestion> findById(@Param("question_id") Long id);
}
