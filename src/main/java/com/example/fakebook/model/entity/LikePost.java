package com.example.fakebook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LikePost {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private boolean status;

   @ManyToOne
   private PostUser postUser;

   @ManyToOne
   private UserInfo userInfo;

   public LikePost(boolean status, PostUser postUser, UserInfo userInfo) {
      this.status = status;
      this.postUser = postUser;
      this.userInfo = userInfo;
   }
}
