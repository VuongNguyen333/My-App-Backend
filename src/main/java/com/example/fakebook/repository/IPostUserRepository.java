package com.example.fakebook.repository;

import com.example.fakebook.model.entity.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostUserRepository extends JpaRepository<PostUser, Long> {

   @Query(value = "select * from post_user where user_info_id=?1 order by id desc ", nativeQuery = true)
   List<PostUser> showAllByUserInfo(Long userInfoId);


}
