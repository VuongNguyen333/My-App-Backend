package com.example.fakebook.model.dto;

import com.example.fakebook.model.entity.Comment;
import com.example.fakebook.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserFrontEnd {
   private Long id;

   private String content;

   private Date dateCreated;

   private MultipartFile[] image;

   private UserInfo userInfo;

   private Integer totalLike;

   private List<Comment> commentPostUser;


}
