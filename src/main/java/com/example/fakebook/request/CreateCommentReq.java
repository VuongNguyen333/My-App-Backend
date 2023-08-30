package com.example.fakebook.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCommentReq {
   private String content;

   private Long userInfoId;

   private Long postId;
}
