package com.example.fakebook.repository;

import com.example.fakebook.model.entity.NotificationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository<NotificationUser, Long> {

   @Query(value = "select * from notification_user where to_user_info_id = ?1 order by date_created desc ", nativeQuery = true)
   List<NotificationUser> showAll(Long to_user_id);

   void deleteAllByToUserInfoId(Long to_userInfo_id);
}
