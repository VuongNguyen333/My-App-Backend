package com.example.fakebook.controller;

import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.request.ReqSetAvt;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.ImageService;
import com.example.fakebook.service.UserInfoService;
import com.example.fakebook.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "userInfo")
public class UserInfoController {
   @Autowired
   private UserInfoService userInfoService;

   @Autowired
   private ImageService imageService;

   @Value(value = "${file-upload}")
   private String uploadPath;

   @GetMapping
   public ResponseEntity<Page<UserInfo>> showAll(@PageableDefault(value = 1) Pageable pageable) {
      Page<UserInfo> userInfos = userInfoService.findAll(pageable);
      return new ResponseEntity<>(userInfos, HttpStatus.OK);
   }

   @PostMapping("/setAvatar/{userInfoId}")
   public ResponseEntity<Resp> updateAvt(@ModelAttribute ReqSetAvt reqSetAvt, @PathVariable Long userInfoId) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                userInfoService.updateAvatar(reqSetAvt, userInfoId),
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

   @PostMapping("/setBackground/{userInfoId}")
   public ResponseEntity<Resp> updateBackground(@ModelAttribute ReqSetAvt reqSetAvt, @PathVariable Long userInfoId) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                userInfoService.updateBackground(reqSetAvt, userInfoId),
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

   @PostMapping("/updateInfo")
   public ResponseEntity<Resp> updateInfo(@RequestBody UserInfo userInfo) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                userInfoService.update(userInfo),
                Contains.RESP_SUCC,
                "Ok"
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

   @GetMapping("user/{userName}")
   public ResponseEntity<Resp> getUserByName(@PathVariable String userName) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                userInfoService.getUserByName(userName),
                Contains.RESP_SUCC,
                "OK"
         );
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @GetMapping("find/{name}")
   public ResponseEntity<Resp> findByName(@PathVariable String name) {
      Resp resp = new Resp();
      try {
         resp.setAll(userInfoService.findByName(name).get(),
                Contains.RESP_SUCC,
                "OK");
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

}
