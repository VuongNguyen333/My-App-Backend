package com.example.fakebook.request;

import com.example.fakebook.model.dto.AvatarForm;
import com.example.fakebook.model.dto.BackGroundForm;
import lombok.Data;

@Data
public class ReqSetAvtOrBgr {
   private AvatarForm avatarForm;

   private BackGroundForm backGroundForm;

   private Long uerInfoId;
}
