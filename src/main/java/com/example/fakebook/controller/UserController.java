package com.example.fakebook.controller;

import com.example.fakebook.request.ReqId;
import com.example.fakebook.service.UserInfoService;
import com.example.fakebook.utils.Contains;
import com.example.fakebook.model.entity.User;
import com.example.fakebook.request.ReqRegister;
import com.example.fakebook.request.ReqUpdatePass;
import com.example.fakebook.respone.Resp;
import com.example.fakebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("fakebook")
public class UserController {
   @Autowired
   private UserService userService;

   @PostMapping("/register")
   public ResponseEntity<Resp> register(@RequestBody  ReqRegister reqRegister) {
      Resp resp = new Resp();
      try {
         userService.addNewUser(reqRegister);
         resp.setData(new User(reqRegister.getUsername() , reqRegister.getPassword()));
         resp.setStatusCode(Contains.RESP_SUCC);
         resp.setMsg("Thanh cong!");
      } catch (Exception e) {
         resp.setStatusCode(Contains.RESP_FAIL);
         resp.setMsg(e.getMessage());
      }
      return new ResponseEntity<>(resp, HttpStatus.OK);
   }

   @PostMapping("/login")
   public ResponseEntity<Resp> login(@RequestBody User user) {
      Resp resp = new Resp();
      try {
         resp.setAll(userService.checkLogin(user), Contains.RESP_SUCC, "Dang nhap thanh cong!");
      } catch (Exception e) {
         resp.setAll(null, Contains.RESP_SUCC, e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @PutMapping("update")
   public ResponseEntity<Resp> update(@RequestBody ReqUpdatePass reqUpdatePass) {
      Resp resp = new Resp();
      try {
         resp.setData(userService.updatePasswordUser(reqUpdatePass));
         resp.setStatusCode(Contains.RESP_SUCC);
         resp.setMsg("Chinh sua thanh cong");
      } catch (Exception e) {
         resp.setStatusCode(Contains.RESP_FAIL);
         resp.setMsg(e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }

   @DeleteMapping("delete")
   public ResponseEntity<Resp> delete(@RequestBody ReqId reqId) {
      Resp resp = new Resp();
      try {
         userService.deleteUser(reqId.getId());
         resp.setStatusCode(Contains.RESP_SUCC);
         resp.setMsg("OK");
      } catch (Exception e) {
         resp.setStatusCode(Contains.RESP_FAIL);
         resp.setMsg(e.getMessage());
      }
      return ResponseEntity.ok(resp);
   }
}
