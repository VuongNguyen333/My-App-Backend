package com.example.fakebook.controller;

import com.example.fakebook.model.entity.NotificationUser;
import com.example.fakebook.request.ReqId;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.NotificationService;
import com.example.fakebook.utils.Contains;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
public class NotificationController {
   private final NotificationService notificationService;

   public NotificationController(NotificationService notificationService) {
      this.notificationService = notificationService;
   }

   @PostMapping()
   public ResponseEntity<Resp> showAll(@RequestBody ReqId reqId) {
      Resp resp = new Resp();
      try {
         List<NotificationUser> notificationUserList = notificationService.showAllByUserId(reqId.getId());
         resp.setAll(notificationUserList, Contains.RESP_SUCC, "Thong bao");
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @PostMapping("/delete/{id}")
   public ResponseEntity<Resp> deleteNotification(@PathVariable Long id) {
      Resp resp = new Resp();
      try {
         notificationService.deleteById(id);
         resp.setAll(null, Contains.RESP_SUCC, "Xoa thanh cong");
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @PostMapping("deleteAll")
   public ResponseEntity<Resp> deleteAll(@RequestBody ReqId reqId) {
      Resp resp = new Resp();
      try {
         notificationService.deleteAllById(reqId);
         resp.setAll(null, Contains.RESP_SUCC, "OK");
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }
}
