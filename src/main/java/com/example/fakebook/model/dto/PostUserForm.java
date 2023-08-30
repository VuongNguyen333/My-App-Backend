package com.example.fakebook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserForm {

   private Long userInfoId;
   private String content;
   private MultipartFile[] image;

   public PostUserForm(Long userInfoId, String content) {
      this.userInfoId = userInfoId;
      this.content = content;
   }
}
