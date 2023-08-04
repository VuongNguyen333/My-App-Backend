package com.example.fakebook.repository;

import com.example.fakebook.model.entity.Comment;
import com.example.fakebook.model.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<PostUser, Long> {

   @Query(value = "select * from comment where post_user_id = ?1 order by date_created", nativeQuery = true)
   List<Comment> showAllByPost(Long id);

}
