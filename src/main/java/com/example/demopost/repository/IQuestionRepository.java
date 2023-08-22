package com.example.demopost.repository;

import com.example.demopost.data.enity.Question;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IQuestionRepository extends JpaRepository<Question , Long> {
  @Query(value = "select * from question ",nativeQuery = true)
   List<Question> findAll();
  @Query(value = "select * from question where id = :id",nativeQuery = true)
  Optional<Question> searchById(@Param("id") long id);

}
