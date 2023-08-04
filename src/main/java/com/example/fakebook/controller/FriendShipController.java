package com.example.fakebook.controller;

import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.request.ReqAddFriend;
import com.example.fakebook.request.ReqDeleteFr;
import com.example.fakebook.request.ReqId;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.FriendShipService;
import com.example.fakebook.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/friendship")
public class FriendShipController {

   @Autowired
   private FriendShipService friendShipService;

   @GetMapping("/listFriend")
   public ResponseEntity<Resp> showAll(@RequestBody ReqId reqId) {
      Resp resp = new Resp();
      try{
         List<UserInfo> infoList = friendShipService.showAllFriend(reqId.getId());
         resp.setAll(
                infoList,
                Contains.RESP_SUCC,
                "OK"
         );
      } catch(Exception e ){
         resp.setAll(
                null,
                Contains.RESP_FAIL,
                e.getMessage()
         );
      }
      return ResponseEntity.ok(resp);
   }

   @PostMapping("/newFriend")
   public ResponseEntity<Resp> createFriendShip(@RequestBody ReqAddFriend reqAddFriend) {
      Resp resp = new Resp();
      try{
         friendShipService.createFriendShip(reqAddFriend);
         resp.setAll(
                null,
                Contains.RESP_SUCC,
                "OK"
         );
      } catch (Exception e) {
         resp.setAll(
                null,
                Contains.RESP_FAIL,
                e.getMessage()
         );
      }
      return ResponseEntity.ok(resp);
   }

   @PostMapping("/deleteFriend")
   public ResponseEntity<Resp> deleteFriendShip(@RequestBody ReqDeleteFr reqDeleteFr) {
      Resp resp = new Resp();
      try{
         friendShipService.deleteFriendShip(reqDeleteFr);
         resp.setAll(
                null,
                Contains.RESP_SUCC,
                "OK"
         );
      } catch (Exception e) {
         resp.setAll(
                null,
                Contains.RESP_FAIL,
                e.getMessage()
         );
      }
      return ResponseEntity.ok(resp);
   }

}
