package com.example.fakebook.repository;

import com.example.fakebook.model.entity.FriendShip;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IFriendShipRepository extends JpaRepository<FriendShip, Long> {

   @Modifying
   @Query(value = "select from_user_info_id from friend_ship where to_user_info_id = ?1 and status=2", nativeQuery = true)
   List<Long> showListFriend(Long userId);

   @Modifying
   @Query(value = "delete from friend_ship where from_user_info_id = ?1 and to_user_info_id = ?2", nativeQuery = true)
   void deleteFriend(Long userInfoId1, Long userInfoId2);

   @Modifying
   @Query(value = "select from_user_info_id from friend_ship where to_user_info_id = ?1 and status=1", nativeQuery = true)
   List<Long> listRequest(Long userId);

   Optional<FriendShip> getFriendShipByFromUserInfoIdAndToUserInfoId(Long userInfoId1, Long userInfoId2);
}

