package com.example.demopost.repository;

import com.example.demopost.data.enity.LikePostTopic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepository extends JpaRepository<LikePostTopic, Long> {

  // jpa search post_id
  @Query(value = "select * from like_post where post_id = :post_id", nativeQuery = true)
  Optional<LikePostTopic> findById(@Param("post_id") Long id);

}
