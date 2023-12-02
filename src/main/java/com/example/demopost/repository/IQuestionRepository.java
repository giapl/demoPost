package com.example.demopost.repository;

import com.example.demopost.data.enity.Question;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {

  // jpa hien thi toan bo question
  @Query(value = "select question.id ,question.content,question.image_url,question.create_at, comment_question.id , comment_question.content , comment_question.image , comment_question.create_at"
      + " from question"
      + " left join comment_question"
      + " on question.id=comment_question.question_id", nativeQuery = true)
  List<Question> findAll();

  // jpa search question theo id
  @Query(value = "select * from question where id = :id", nativeQuery = true)
  Optional<Question> searchById(@Param("id") long id);

  //jpa lay question theo id
  @Query(value = "select * from question order by id desc ", nativeQuery = true)
  List<Question> findAllByIdOrderBy();
}
