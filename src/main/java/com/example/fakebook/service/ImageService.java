package com.example.fakebook.service;

import com.example.fakebook.model.entity.Image;
import com.example.fakebook.repository.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
   @Autowired
   private IImageRepository imageRepository;

   public List<Image> showAllByPostId(Long postUserId) {
      return imageRepository.findAllByPostUserId(postUserId);
   }

   public void saveImage(Image image) {
      imageRepository.save(image);
   }
   
   public void deleteById(Long imageId) {
      imageRepository.deleteById(imageId);
   }

   public void deleteAllByPostId(Long postId) {
      imageRepository.deleteAllByPostUserId(postId);
   }
}
