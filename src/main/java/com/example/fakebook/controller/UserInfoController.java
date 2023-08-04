package com.example.fakebook.controller;

import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.request.ReqSetAvtOrBgr;
import com.example.fakebook.request.ReqUpdateInfo;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.UserInfoService;
import com.example.fakebook.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@CrossOrigin("*")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
   @Autowired
   private UserInfoService userInfoService;


   @Value(value = "${file-upload}")
   private String uploadPath;



   @GetMapping
   public ResponseEntity<Page<UserInfo>> showAll(@PageableDefault(value = 1) Pageable pageable) {
      Page<UserInfo> userInfos = userInfoService.findAll(pageable);
      return new ResponseEntity<>(userInfos, HttpStatus.OK);
   }

   @PostMapping("/setAvatar")
   public ResponseEntity<Resp> updateAvt(@RequestBody ReqSetAvtOrBgr reqSetAvtOrBgr){
      Resp resp = new Resp();
      MultipartFile multipartFile = reqSetAvtOrBgr.getAvatarForm().getAvatar();
      String avatar = multipartFile.getOriginalFilename();
      try {
         FileCopyUtils.copy(multipartFile.getBytes() , new File(uploadPath + avatar));
         UserInfo userInfo = userInfoService.findById(reqSetAvtOrBgr.getUerInfoId());
         userInfo.setAvatarUrl(avatar);
         resp.setAll(
                userInfoService.save(userInfo),
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

   @PostMapping("/setBackground")
   public ResponseEntity<Resp> updateBgr(@RequestBody ReqSetAvtOrBgr reqSetAvtOrBgr){
      Resp resp = new Resp();
      MultipartFile multipartFile = reqSetAvtOrBgr.getBackGroundForm().getBackground();
      String background = multipartFile.getOriginalFilename();
      try {
         FileCopyUtils.copy(multipartFile.getBytes() , new File(uploadPath + background));
         UserInfo userInfo = userInfoService.findById(reqSetAvtOrBgr.getUerInfoId());
         userInfo.setBackgroundUrl(background);
         userInfoService.save(userInfo);
         resp.setAll(
                userInfoService.save(userInfo),
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

   @PostMapping("/updateInfo")
   public ResponseEntity<Resp> updateInfo(@RequestBody ReqUpdateInfo reqUpdateInfo) {
      Resp resp = new Resp();
      try{
         resp.setAll(
                userInfoService.update(reqUpdateInfo.getUserInfo() , reqUpdateInfo.getUserId()),
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

}
