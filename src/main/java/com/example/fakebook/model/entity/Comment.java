package com.example.fakebook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String content;

   private Date dateCreated;

   @ManyToOne
   private UserInfo userInfo;

   @ManyToOne
   private PostUser postUser;

   public Comment(String content, Date dateCreated, UserInfo userInfo, PostUser postUser) {
      this.content = content;
      this.dateCreated = dateCreated;
      this.userInfo = userInfo;
      this.postUser = postUser;
   }
}
