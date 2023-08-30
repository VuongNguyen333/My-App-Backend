package com.example.fakebook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationUser {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private Date dateCreated;

   private String content;

   @ManyToOne
   private UserInfo fromUserInfo;

   @ManyToOne
   private UserInfo toUserInfo;

   private Long postId;

   public NotificationUser(Date dateCreated, String content, UserInfo fromUserInfo, UserInfo toUserInfo) {
      this.dateCreated = dateCreated;
      this.content = content;
      this.fromUserInfo = fromUserInfo;
      this.toUserInfo = toUserInfo;
   }

   public NotificationUser(Date dateCreated, String content, UserInfo fromUserInfo, UserInfo toUserInfo, Long postId) {
      this.dateCreated = dateCreated;
      this.content = content;
      this.fromUserInfo = fromUserInfo;
      this.toUserInfo = toUserInfo;
      this.postId = postId;
   }
}
