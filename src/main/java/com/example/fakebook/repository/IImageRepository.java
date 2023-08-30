package com.example.fakebook.repository;

import com.example.fakebook.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
   Image[] findAllByPostUserId(Long postUserId);

   void deleteAllByPostUserId(Long postUserId);
}
