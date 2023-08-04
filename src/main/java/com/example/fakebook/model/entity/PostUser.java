package com.example.fakebook.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

   private long[] likeArrays;

   @ManyToOne
   private UserInfo userInfo;


}
