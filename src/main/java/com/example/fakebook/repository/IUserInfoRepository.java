package com.example.fakebook.repository;

import com.example.fakebook.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {
   @Query(value = "select * from user_info where email=?1", nativeQuery = true)
   Optional<UserInfo> findByEmail(String email);

   @Query(value = "select * from user_info where name=?1", nativeQuery = true)
   Optional<List<UserInfo>> findByName(String name);

   @Query(value = "select * from user_info where id=?1", nativeQuery = true)
   Optional<UserInfo> findByUserInfoId (Long userInfoId);

   @Query(value = "select * from user_info where user_id=?1", nativeQuery = true)
   Optional<UserInfo> findByUserId(Long userId);
}
