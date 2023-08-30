package com.example.fakebook.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCommentReq {
   private Long commentId;
   private Long userInfoId;
}
