package com.example.fakebook.service;

import com.example.fakebook.model.dto.PostUserForm;
import com.example.fakebook.model.entity.*;
import com.example.fakebook.repository.*;
import com.example.fakebook.request.ReqId;
import com.example.fakebook.respone.ResPostUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostUserService {

   @Value("$(file-upload)")
   private String uploadPath;

   @Autowired
   private IPostUserRepository postUserRepository;

   @Autowired
   private IUserInfoRepository userInfoRepository;

   @Autowired
   private IImageRepository imageRepository;

   @Autowired
   private ICommentRepository commentRepository;

   @Autowired
   private IUserRepository userRepository;

   @Autowired
   private ILikePostRepository likePostRepository;

   @Transactional
   public List<ResPostUser> showAll() {
      List<PostUser> postUserList = postUserRepository.findAllPost();
      List<ResPostUser> resPostUserList = new ArrayList<>();
      for (PostUser postUser : postUserList) {
         resPostUserList.add(new ResPostUser(
                postUser.getId(),
                postUser.getContent(),
                postUser.getDateCreated(),
                imageRepository.findAllByPostUserId(postUser.getId()),
                likePostRepository.totalLikeByPost(postUser.getId()).size(),
                commentRepository.showAllByPost(postUser.getId()),
                commentRepository.showAllByPost(postUser.getId()).size(),
                postUser.getUserInfo()
         ));
      }
      return resPostUserList;
   }

   @Transactional
   public List<ResPostUser> showAllPostUser(ReqId reqId) throws Exception {
      Optional<UserInfo> userInfoOptional = userInfoRepository.findByUserInfoId(reqId.getId());
      List<ResPostUser> resPostUserList = new ArrayList<>();
      if (userInfoOptional.isPresent()) {
         UserInfo userInfo = userInfoOptional.get();
         List<PostUser> postUserList = postUserRepository.findAllByUserInfoId(reqId.getId());
         for (PostUser postUser : postUserList) {
            resPostUserList.add(new ResPostUser(
                   postUser.getId(),
                   postUser.getContent(),
                   postUser.getDateCreated(),
                   imageRepository.findAllByPostUserId(postUser.getId()),
                   likePostRepository.totalLikeByPost(postUser.getId()).size(),
                   commentRepository.showAllByPost(postUser.getId()),
                   commentRepository.showAllByPost(postUser.getId()).size()
            ));
         }
         return resPostUserList;
      } else {
         throw new Exception("That bai");
      }
   }

   @Transactional
   public PostUser createNewPost(PostUserForm postUserForm) {
      MultipartFile[] multipartFiles = postUserForm.getImage();
      Optional<UserInfo> userInfoOptional = userInfoRepository.findByUserInfoId(postUserForm.getUserInfoId());
      UserInfo userInfo = userInfoOptional.get();
      List<String> images = new ArrayList<>();
      PostUser postUser = new PostUser(
             postUserForm.getContent(),
             new Date(),
             userInfo
      );
      postUserRepository.save(postUser);
      if (multipartFiles != null) {
         for (MultipartFile multipartFile : multipartFiles) {
            images.add(multipartFile.getOriginalFilename());
         }
         for (int i = 0; i < images.size(); i++) {
            imageRepository.save(new Image(images.get(i), postUser.getId()));
            try {
               FileCopyUtils.copy(multipartFiles[i].getBytes(), new File(uploadPath + images.get(i)));
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
      return postUser;
   }

   public ResPostUser getPostById(Long id) {
      PostUser postUser = postUserRepository.findById(id).get();
      return new ResPostUser(
             postUser.getId(),
             postUser.getContent(),
             postUser.getDateCreated(),
             imageRepository.findAllByPostUserId(postUser.getId()),
             likePostRepository.totalLikeByPost(postUser.getId()).size(),
             commentRepository.showAllByPost(postUser.getId()),
             commentRepository.showAllByPost(postUser.getId()).size(),
             postUser.getUserInfo()
      );
   }

   public void deletePost(ReqId reqId) {
//      PostUser postUser = postUserRepository.findById(reqId.getId()).get();
      List<LikePost> likePostList = likePostRepository.totalLikeByPost(reqId.getId());
      for (LikePost likePost : likePostList) {
         likePostRepository.deleteById(likePost.getId());
      }
      Image[] images = imageRepository.findAllByPostUserId(reqId.getId());
      for (Image image : images) {
         imageRepository.deleteById(image.getId());
      }
      commentRepository.deleteAllByPostUserId(reqId.getId());
      postUserRepository.deleteById(reqId.getId());
   }
}























