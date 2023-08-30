package com.example.fakebook.controller;

import com.example.fakebook.request.PostIdReq;
import com.example.fakebook.request.ReqPostIdAndUserInfoId;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.LikePostService;
import com.example.fakebook.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/like_post")
@CrossOrigin("*")
public class LikePostController {

   @Autowired
   private LikePostService likePostService;

   @PostMapping("/totalLike")
   public ResponseEntity<Resp> totalLikePost(@RequestBody PostIdReq postIdReq) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                likePostService.totalLike(postIdReq),
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

   @PostMapping("create_like")
   public ResponseEntity<Resp> createNewLike(@RequestBody ReqPostIdAndUserInfoId reqPostIdAndUserInfoId) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                likePostService.newLike(reqPostIdAndUserInfoId),
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

   @PostMapping("/getStatus")
   public ResponseEntity<Resp> getStatusLike(@RequestBody ReqPostIdAndUserInfoId reqPostIdAndUserInfoId) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                likePostService.getStatusLike(reqPostIdAndUserInfoId),
                Contains.RESP_SUCC,
                "OK"
         );
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }
}
