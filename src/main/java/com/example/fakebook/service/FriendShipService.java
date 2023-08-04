package com.example.fakebook.service;

import com.example.fakebook.model.entity.FriendShip;
import com.example.fakebook.model.entity.NotificationUser;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.repository.IFriendShipRepository;
import com.example.fakebook.repository.INotificationRepository;
import com.example.fakebook.repository.IUserInfoRepository;
import com.example.fakebook.request.ReqAddFriend;
import com.example.fakebook.request.ReqDeleteFr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FriendShipService {

   @Autowired
   private IFriendShipRepository friendShipRepository;

   @Autowired
   private IUserInfoRepository userInfoRepository;

   @Autowired
   private INotificationRepository notificationRepository;

   public List<UserInfo> showAllFriend(Long userInfoId) throws Exception {
      Optional<UserInfo> userInfoOptional = userInfoRepository.findByUserInfoId(userInfoId);
      List<UserInfo> userInfos = new ArrayList<>();
      if(userInfoOptional.isPresent()) {
         List<Long> listFriend = friendShipRepository.showListFriend(userInfoId, 1L);
         for(Long userInfoId1 : listFriend) {
            userInfos.add(userInfoRepository.findByUserInfoId(userInfoId1).get());
         }
      } else {
         throw new Exception("Da xay ra loi!");
      }
      return userInfos;
   }

   @Transactional
   public void createFriendShip(ReqAddFriend reqAddFriend) throws Exception {
      UserInfo userInfo1 = userInfoRepository.findByUserInfoId(reqAddFriend.getFromUserInfoId()).get();
      UserInfo userInfo2 = userInfoRepository.findByUserInfoId(reqAddFriend.getToUserInfoId()).get();
      NotificationUser notificationUser = new NotificationUser(
             new Date(),
             userInfo1.getName() + " đã chấp nhận lời mời kết bạn của bạn",
             userInfo1,
             userInfo2
      );
      notificationRepository.save(notificationUser);
   }

   @Transactional
   public void deleteFriendShip(ReqDeleteFr reqDeleteFr) throws Exception {
      UserInfo userInfo1 = userInfoRepository.findByUserInfoId(reqDeleteFr.getFromUserInfoId()).get();
      UserInfo userInfo2 = userInfoRepository.findByUserInfoId(reqDeleteFr.getToUserInfoId()).get();

      friendShipRepository.deleteFriend(userInfo1.getId(), userInfo2.getId());
      friendShipRepository.deleteFriend(userInfo2.getId(), userInfo1.getId());
   }
}
