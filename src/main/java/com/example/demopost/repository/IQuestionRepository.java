package com.example.demopost.repository;

import com.example.demopost.data.enity.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IQuestionRepository extends JpaRepository<Question , Long> {
  @Query(value = "select * from question ",nativeQuery = true)
   List<Question> findAll();

}
