package com.example.fakebook.respone;

import com.example.fakebook.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikePostRes {
   private UserInfo userInfoId;
}
