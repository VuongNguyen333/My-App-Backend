package com.example.fakebook.service;

import com.example.fakebook.model.entity.FriendShip;
import com.example.fakebook.model.entity.NotificationUser;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.repository.IFriendShipRepository;
import com.example.fakebook.repository.INotificationRepository;
import com.example.fakebook.repository.IUserInfoRepository;
import com.example.fakebook.request.ReqAddFriend;
import com.example.fakebook.request.ReqDeleteFr;
import com.example.fakebook.request.ReqId;
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
      if (userInfoOptional.isPresent()) {
         List<Long> listFriend = friendShipRepository.showListFriend(userInfoId);
         for (Long userInfoId1 : listFriend) {
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
      friendShipRepository.deleteFriend(userInfo1.getId(), userInfo2.getId());
      friendShipRepository.deleteFriend(userInfo2.getId(), userInfo1.getId());
      NotificationUser notificationUser = new NotificationUser(
             new Date(),
             userInfo1.getName() + " đã chấp nhận lời mời kết bạn của bạn",
             userInfo1,
             userInfo2
      );
      notificationRepository.save(notificationUser);
      friendShipRepository.save(new FriendShip(
             userInfo1,
             userInfo2,
             2
      ));
      friendShipRepository.save(new FriendShip(
             userInfo2,
             userInfo1,
             2
      ));
   }

   @Transactional
   public void deleteFriendShip(ReqDeleteFr reqDeleteFr) {
      UserInfo userInfo1 = userInfoRepository.findByUserInfoId(reqDeleteFr.getFromUserInfoId()).get();
      UserInfo userInfo2 = userInfoRepository.findByUserInfoId(reqDeleteFr.getToUserInfoId()).get();
      friendShipRepository.deleteFriend(userInfo1.getId(), userInfo2.getId());
      friendShipRepository.deleteFriend(userInfo2.getId(), userInfo1.getId());
   }

   @Transactional
   public void addFriendReq(ReqAddFriend reqAddFriend) {
      UserInfo userInfo1 = userInfoRepository.findByUserInfoId(reqAddFriend.getFromUserInfoId()).get();
      UserInfo userInfo2 = userInfoRepository.findByUserInfoId(reqAddFriend.getToUserInfoId()).get();
      friendShipRepository.deleteFriend(userInfo1.getId(), userInfo2.getId());
      friendShipRepository.save(new FriendShip(
             userInfo1,
             userInfo2,
             1
      ));
   }

   @Transactional
   public FriendShip getStatusFrShip(ReqAddFriend reqAddFriend) throws Exception {
      Long userInfoId1 = reqAddFriend.getFromUserInfoId();
      Long userInfoId2 = reqAddFriend.getToUserInfoId();
      if (userInfoId1 == userInfoId2) return null;
      Optional<FriendShip> friendShipOptional = friendShipRepository.getFriendShipByFromUserInfoIdAndToUserInfoId(
             userInfoId1,
             userInfoId2
      );
      if (friendShipOptional.isEmpty()) {
         return friendShipRepository.save(new FriendShip(
                userInfoRepository.findByUserInfoId(userInfoId1).get(),
                userInfoRepository.findByUserInfoId(userInfoId2).get(),
                0
         ));
      } else {
         return friendShipOptional.get();
      }
   }

   @Transactional
   public List<UserInfo> getAllRequestFriend(ReqId reqId) throws Exception {
      Long userInfoId = reqId.getId();
      Optional<UserInfo> userInfoOptional = userInfoRepository.findById(reqId.getId());
      List<UserInfo> listUserInfo = new ArrayList<>();
      if (userInfoOptional.isEmpty()) {
         throw new Exception("Khong tim thay nguoi dung!");
      } else {
         List<Long> listReq = friendShipRepository.listRequest(userInfoId);
         for (Long req : listReq) {
            UserInfo userInfo = userInfoRepository.findById(req).get();
            listUserInfo.add(userInfo);
         }
      }
      return listUserInfo;
   }

   @Transactional
   public List<UserInfo> showNotFriend(ReqId reqId) {
      UserInfo userLogin = userInfoRepository.findByUserInfoId(reqId.getId()).get();
      List<UserInfo> userInfoList = userInfoRepository.findAll();
      List<UserInfo> userListResult = new ArrayList<>();
      int max = 0;
      for (UserInfo userInfo : userInfoList) {
         if (userInfo.getId() == userLogin.getId()) continue;
         Optional<FriendShip> friendShipOptional1 = friendShipRepository.getFriendShipByFromUserInfoIdAndToUserInfoId(userLogin.getId(), userInfo.getId());
         Optional<FriendShip> friendShipOptional2 = friendShipRepository.getFriendShipByFromUserInfoIdAndToUserInfoId(userInfo.getId(), userLogin.getId());

         if (friendShipOptional1.isPresent() && friendShipOptional2.isPresent()) {
            if (friendShipOptional1.get().getStatus() == 0 && friendShipOptional2.get().getStatus() == 0) {
               userListResult.add(userInfo);
               max++;
               if (max == 5) break;
            }
         } else {
            userListResult.add(userInfo);
            max++;
            if (max == 5) break;
         }
      }
      return userListResult;
   }
}
