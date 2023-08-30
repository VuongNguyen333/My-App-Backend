package com.example.fakebook.service;

import com.example.fakebook.model.entity.LikePost;
import com.example.fakebook.model.entity.NotificationUser;
import com.example.fakebook.model.entity.PostUser;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.repository.ILikePostRepository;
import com.example.fakebook.repository.INotificationRepository;
import com.example.fakebook.repository.IPostUserRepository;
import com.example.fakebook.repository.IUserInfoRepository;
import com.example.fakebook.request.PostIdReq;
import com.example.fakebook.request.ReqPostIdAndUserInfoId;
import com.example.fakebook.respone.LikePostRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LikePostService {
   @Autowired
   private ILikePostRepository likePostRepository;

   @Autowired
   private IPostUserRepository postUserRepository;

   @Autowired
   private IUserInfoRepository userInfoRepository;

   @Autowired
   private INotificationRepository notificationRepository;

   @Transactional
   public List<LikePostRes> totalLike(PostIdReq postIdReq) {
      List<LikePostRes> likePostResList = new ArrayList<>();
      if (likePostRepository.totalLikeByPost(postIdReq.getPostId()) != null) {
         List<LikePost> likePostList = likePostRepository.totalLikeByPost(postIdReq.getPostId());
         for (LikePost likePost : likePostList) {
            likePostResList.add(new LikePostRes(
                   likePost.getUserInfo()
            ));
         }
      }
      return likePostResList;
   }

   @Transactional
   public LikePost newLike(ReqPostIdAndUserInfoId reqPostIdAndUserInfoId) {
      PostUser postUser = postUserRepository.findById(reqPostIdAndUserInfoId.getPostId()).get();
      UserInfo userInfo = userInfoRepository.findById(reqPostIdAndUserInfoId.getUserInfoId()).get();
      Optional<LikePost> likePost = likePostRepository.findByPostUserIdAndUserInfoId(reqPostIdAndUserInfoId.getPostId(), reqPostIdAndUserInfoId.getUserInfoId());
      if (likePost.isEmpty()) {
         LikePost newLike = likePostRepository.save(new LikePost(
                true,
                postUser,
                userInfo
         ));
         if (postUser.getUserInfo().getId() != userInfo.getId()) {
            NotificationUser notificationUser = new NotificationUser(
                   new Date(),
                   userInfo.getName() + " đã thích bài viết của bạn ",
                   userInfo,
                   postUser.getUserInfo(),
                   postUser.getId()
            );
            notificationRepository.save(notificationUser);
         }
         return newLike;
      } else {
         likePostRepository.deleteById(likePost.get().getId());
         return null;
      }
   }

   @Transactional
   public LikePost getStatusLike(ReqPostIdAndUserInfoId reqPostIdAndUserInfoId) {
      LikePost likePost = new LikePost();
      Optional<LikePost> likePostOptional = likePostRepository.findByPostUserIdAndUserInfoId(reqPostIdAndUserInfoId.getPostId(), reqPostIdAndUserInfoId.getUserInfoId());
      if (likePostOptional.isPresent()) {
         likePost = likePostOptional.get();
      } else likePost = new LikePost(
             false,
             postUserRepository.findById(reqPostIdAndUserInfoId.getPostId()).get(),
             userInfoRepository.findByUserInfoId(reqPostIdAndUserInfoId.getUserInfoId()).get()
      );
      return likePost;
   }
}
