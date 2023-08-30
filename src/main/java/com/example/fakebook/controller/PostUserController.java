package com.example.fakebook.controller;

import com.example.fakebook.model.dto.PostUserForm;
import com.example.fakebook.request.ReqId;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.PostUserService;
import com.example.fakebook.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class PostUserController {
   @Autowired
   private PostUserService postUserService;

   @PostMapping("/showAll")
   public ResponseEntity<Resp> showAll() {
      Resp resp = new Resp();
      try {
         resp.setAll(
                postUserService.showAll(),
                Contains.RESP_SUCC,
                "OK"
         );
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @PostMapping("/Home")
   public ResponseEntity<Resp> showAllPost(@RequestBody ReqId reqId) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                postUserService.showAllPostUser(reqId),
                Contains.RESP_SUCC,
                "Ok"
         );
      } catch (Exception e) {
         e.printStackTrace();
         resp.setAll(
                null,
                Contains.RESP_FAIL,
                e.getMessage()
         );
      }
      return ResponseEntity.ok(resp);
   }

   @GetMapping("view-post/{id}")
   public ResponseEntity<Resp> getPostById(@PathVariable Long id) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                postUserService.getPostById(id),
                Contains.RESP_SUCC,
                "OK"
         );
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @PostMapping("/newPost")
   public ResponseEntity<Resp> newPost(@ModelAttribute PostUserForm postUserForm) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                postUserService.createNewPost(postUserForm),
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

   @PostMapping("/deletePost")
   public ResponseEntity<Resp> deletePost(@RequestBody ReqId reqId) {
      Resp resp = new Resp();
      try {
         postUserService.deletePost(reqId);
         resp.setAll(null, Contains.RESP_SUCC, "OK");
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_FAIL, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }
}
