package com.example.fakebook.request;

import lombok.Data;

@Data
public class ReqAddFriend {
   private Long fromUserInfoId;

   private Long toUserInfoId;
}
