package com.example.fakebook.controller;

import com.example.fakebook.model.entity.NotificationUser;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.request.ReqAddFriend;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.NotificationService;
import com.example.fakebook.service.UserInfoService;
import com.example.fakebook.utils.Contains;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
public class NotificationController {
   private final NotificationService notificationService;

   private final UserInfoService userInfoService;

   public NotificationController(NotificationService notificationService, UserInfoService userInfoService) {
      this.notificationService = notificationService;
      this.userInfoService = userInfoService;
   }


   @GetMapping("/{id}")
   public ResponseEntity<Resp> showAll(@PathVariable Long id) {
      Resp resp = new Resp();
      try{
         List<NotificationUser> notificationUserList = notificationService.showAllByUserId(id);
         resp.setAll(notificationUserList, Contains.RESP_SUCC, "Thong bao");
      } catch(Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Resp> deleteNotification(@PathVariable Long id) {
      Resp resp = new Resp();
      try{
         notificationService.deleteById(id);
         resp.setAll(null, Contains.RESP_SUCC, "Xoa thanh cong");
      } catch(Exception e){
         resp.setAll(null , Contains.RESP_FAIL , e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @PostMapping("/addFriend")
   public ResponseEntity<Resp> create(@RequestBody ReqAddFriend reqAddFriend) {
      Resp resp = new Resp();
      try {
         UserInfo userInfo1 = userInfoService.findById(reqAddFriend.getFromUserInfoId());
         UserInfo userInfo2 = userInfoService.findById(reqAddFriend.getToUserInfoId());
         NotificationUser notificationUser = new NotificationUser(
                new Date(),
                userInfo1.getName() + " đã gửi lời mời kết bạn ",
                userInfo1,
                userInfo2
         );
         resp.setAll(notificationUser, Contains.RESP_SUCC , "OK!");
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL , e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }
}
