package com.example.fakebook.request;

import lombok.Data;

@Data
public class ReqRegister {
   private String username;

   private String password;

   private String confirmPassword;
}
