package com.example.demopost.repository;

import com.example.demopost.data.enity.PostTopic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<PostTopic, Long> {

  @Query(value = "select *from topic ",nativeQuery = true)
  List<PostTopic> findAll();
  @Query(value = "SELECT * from topic pt WHERE LOWER(pt.title) LIKE LOWER(concat('%', :title, '%'))", nativeQuery = true)
  List<PostTopic> findByTitleContainingIgnoreCase(@Param("title") String title);
  @Query(value = "select * from topic order by  id desc ",nativeQuery = true)
  List<PostTopic> findAllBYIdOrderBy();
  @Query(value = "select *from topic order by like_count,share desc",nativeQuery = true)
  List<PostTopic> findAllByLikeOrderBy();
}
