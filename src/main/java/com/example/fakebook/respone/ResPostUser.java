package com.example.fakebook.respone;

import com.example.fakebook.model.entity.Comment;
import com.example.fakebook.model.entity.Image;
import com.example.fakebook.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResPostUser {
   private Long id;

   private String content;

   private Date dateCreated;

   private Image[] image;

   private int totalLike;

   private List<Comment> commentPostUser;

   private int totalComment;

   private UserInfo userInfo;

   public ResPostUser(Long id, String content, Date dateCreated, Image[] image, int totalLike, List<Comment> commentPostUser, int totalComment) {
      this.id = id;
      this.content = content;
      this.dateCreated = dateCreated;
      this.image = image;
      this.totalLike = totalLike;
      this.commentPostUser = commentPostUser;
      this.totalComment = totalComment;
   }
}



