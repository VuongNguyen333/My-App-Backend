package com.example.fakebook.controller;

import com.example.fakebook.request.CreateCommentReq;
import com.example.fakebook.request.DeleteCommentReq;
import com.example.fakebook.request.PostIdReq;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.CommentService;
import com.example.fakebook.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {
   @Autowired
   private CommentService commentService;

   @GetMapping("/{id}")
   public ResponseEntity<Resp> allComment(@PathVariable PostIdReq id) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                commentService.showAllComment(id),
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

   @PostMapping("/new_comment")
   public ResponseEntity<Resp> createComment(@RequestBody CreateCommentReq createCommentReq) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                commentService.newComment(createCommentReq),
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

   @PostMapping("/delete_comment")
   public ResponseEntity<Resp> deleteCnt(@RequestBody DeleteCommentReq deleteCommentReq) {
      Resp resp = new Resp();
      try {
         commentService.deleteComment(deleteCommentReq);
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
