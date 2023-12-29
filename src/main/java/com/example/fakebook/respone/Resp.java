package com.example.fakebook.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resp {
   private Object data;

   private String statusCode;// 0: succ <>1: fail

   private String msg;

   public void setAll(Object data, String statusCode, String msg) {
      this.data = data;
      this.statusCode = statusCode;
      this.msg = msg;
   }
}
