package com.example.fakebook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String email;

   private String name;

   private String age;

   private String sex;

   private String address;

   private String avatarUrl;

   private String backgroundUrl;

   @OneToOne
   private User user;

   private String about;

   public UserInfo(Long id, String email, String name, String age, String sex, String address, String about) {
      this.id = id;
      this.email = email;
      this.name = name;
      this.age = age;
      this.sex = sex;
      this.address = address;
      this.about = about;
   }

   public UserInfo(String email, String name, String age, String sex, String address, String avatarUrl, String backgroundUrl, User user, String about) {
      this.email = email;
      this.name = name;
      this.age = age;
      this.sex = sex;
      this.address = address;
      this.avatarUrl = avatarUrl;
      this.backgroundUrl = backgroundUrl;
      this.user = user;
      this.about = about;
   }

   public UserInfo(String name, User user) {
      this.name = name;
      this.user = user;
   }

   public UserInfo(String name, String avatarUrl, String backgroundUrl, User user) {
      this.name = name;
      this.avatarUrl = avatarUrl;
      this.backgroundUrl = backgroundUrl;
      this.user = user;
   }
}
