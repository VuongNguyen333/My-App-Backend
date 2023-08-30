package com.example.fakebook.controller;

import com.example.fakebook.request.ReqId;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.ImageService;
import com.example.fakebook.utils.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@CrossOrigin("*")
public class ImageController {
   @Autowired
   private ImageService imageService;

   @PostMapping("/getImage")
   public ResponseEntity<Resp> getAllImageByPostId(@RequestBody ReqId reqId) {
      Resp resp = new Resp();
      try {
         resp.setAll(
                imageService.showAllByPostId(reqId.getId()),
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
