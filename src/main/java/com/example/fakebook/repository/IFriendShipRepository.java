package com.example.fakebook.repository;

import com.example.fakebook.model.entity.FriendShip;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface IFriendShipRepository extends JpaRepository<FriendShip, Long> {

   @Modifying
   @Query(value = "select to_user_info_id from friend_ship where from_user_info_id = ?1 and status=?2", nativeQuery = true)
   List<Long> showListFriend(Long userId, Long statusId);

   @Modifying
   @Query(value = "delete from friend_ship where to_user_info_id = ?1 and from_user_info_id = ?2", nativeQuery = true)
   void deleteFriend(Long userInfoId1, Long userInfoId2);

}

