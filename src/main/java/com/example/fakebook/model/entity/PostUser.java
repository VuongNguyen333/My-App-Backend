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
public class PostUser {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   public String content;

   private Date dateCreated;

   @ManyToOne
   private UserInfo userInfo;

   public PostUser(String content, Date dateCreated, UserInfo userInfo) {
      this.content = content;
      this.dateCreated = dateCreated;
      this.userInfo = userInfo;
   }
}
