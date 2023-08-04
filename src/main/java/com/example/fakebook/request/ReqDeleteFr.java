package com.example.fakebook.request;

import lombok.Data;

@Data
public class ReqDeleteFr {
   private Long fromUserInfoId;

   private Long toUserInfoId;
}
