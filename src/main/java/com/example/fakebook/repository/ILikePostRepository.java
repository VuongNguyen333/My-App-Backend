package com.example.fakebook.repository;

import com.example.fakebook.model.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {

   @Query(value = "select  * from like_post where post_user_id = ?1 and status = true", nativeQuery = true)
   List<LikePost> totalLikeByPost(Long postUserId);

   Optional<LikePost> findByPostUserIdAndUserInfoId(Long postId, Long userInfoId);
}
