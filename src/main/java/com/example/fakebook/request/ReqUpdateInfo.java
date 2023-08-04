package com.example.fakebook.request;

import com.example.fakebook.model.entity.UserInfo;
import lombok.Data;

@Data
public class ReqUpdateInfo {
   private UserInfo userInfo;

   private Long userId;
}
