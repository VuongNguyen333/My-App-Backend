package com.example.fakebook.service;

import com.example.fakebook.model.entity.User;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.repository.IUserInfoRepository;
import com.example.fakebook.repository.IUserRepository;
import com.example.fakebook.request.ReqSetAvt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {

   @Value(value = "${file-upload}")
   private String uploadPath;

   @Autowired
   private IUserInfoRepository userInfoRepository;

   @Autowired
   private IUserRepository userRepository;


   public UserInfo findById(Long id) {
      return userInfoRepository.findById(id).get();
   }


   public Page<UserInfo> findAll(Pageable pageable) {
      return userInfoRepository.findAll(pageable);
   }

   @Transactional
   public UserInfo save(UserInfo userInfo) {
      return userInfoRepository.save(userInfo);
   }

   @Transactional
   public UserInfo updateAvatar(ReqSetAvt reqSetAvt, Long userInfoId) {
      MultipartFile multipartFile = reqSetAvt.getAvatar();
      String avatar = multipartFile.getOriginalFilename();
      try {
         FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + avatar));
      } catch (Exception e) {
         e.printStackTrace();
      }
      UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId).get();
      userInfo.setAvatarUrl("http://localhost:8080/image/" + avatar);
      return userInfo;
   }

   @Transactional
   public UserInfo updateBackground(ReqSetAvt reqSetAvt, Long userInfoId) {
      MultipartFile multipartFile = reqSetAvt.getAvatar();
      String background = multipartFile.getOriginalFilename();
      try {
         FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + background));
      } catch (Exception e) {
         e.printStackTrace();
      }
      UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId).get();
      userInfo.setBackgroundUrl("http://localhost:8080/image/" + background);
      return userInfo;
   }

   @Transactional
   // chuẩn vẫn phải là userInfoReq (create id = null, update bắt buoc. có ID)
   public UserInfo update(UserInfo userInfoReq) throws Exception {
      Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userInfoReq.getId());
      if (userInfoOptional.isEmpty()) {
         throw new Exception("Không tìm thấy thông tin");
      }
      UserInfo userInfo = userInfoOptional.get();
      BeanUtils.copyProperties(userInfoReq, userInfo, "id");
      return userInfoRepository.save(userInfo);
   }

   public UserInfo getUserByName(String userName) throws Exception {
      Optional<User> userOptional = userRepository.findByUsername(userName);
      if (userOptional.isEmpty()) {
         throw new Exception("Khong tim thay nguoi dung");
      } else {
         Optional<UserInfo> userInfoOptional = userInfoRepository.findByUserId(userOptional.get().getId());
         if (userInfoOptional.isEmpty()) {
            throw new Exception("Khong tim thay nguoi dung");
         } else {
            return userInfoOptional.get();
         }
      }
   }

   public Optional<List<UserInfo>> findByName(String name) {
      Optional<List<UserInfo>> userInfoListOptional = userInfoRepository.findByName(name);
      if (userInfoListOptional.isEmpty()) {
         return null;
      } else {
         return userInfoListOptional;
      }
   }
}
