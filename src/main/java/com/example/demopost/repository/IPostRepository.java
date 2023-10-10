package com.example.demopost.repository;

import com.example.demopost.data.enity.PostTopic;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<PostTopic, Long> {

  // jpa hien thi toan bo post
  @Query(value = "select *from topic ", nativeQuery = true)
  List<PostTopic> findAll();

  //jpa search title cho bai post
  @Query(value = "SELECT * from topic pt WHERE LOWER(pt.title) LIKE LOWER(concat('%', :title, '%'))", nativeQuery = true)
  List<PostTopic> findByTitleContainingIgnoreCase(@Param("title") String title);

  // jpa hien thi bai moi post len
  @Query(value = "select * from topic order by  id desc ", nativeQuery = true)
  List<PostTopic> findAllBYIdOrderBy();

  // jpa search theo idl
  @Query(value = "select * from topic where id = :id", nativeQuery = true)
  Optional<PostTopic> findById(@Param("id") Long id);

  // jpa lay bai viet theo so like
  @Query(value =
      "select topic.id , topic.create_at , topic.title , topic.content , topic.image , topic.update_at, like_post.like_count , like_post.share "
          + "from topic "
          + "left join like_post "
          + "on topic.id = like_post.post_id "
          + "order by like_post.like_count desc", nativeQuery = true)
  List<PostTopic> findAllByOrderByLikesDesc();
}
