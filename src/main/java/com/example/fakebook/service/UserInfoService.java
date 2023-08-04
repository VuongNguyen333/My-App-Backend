package com.example.fakebook.service;

import com.example.fakebook.model.entity.User;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.repository.IUserInfoRepository;
import com.example.fakebook.repository.IUserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class UserInfoService {

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
   public UserInfo update(UserInfo userInfo, Long userId) throws Exception {
      Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userInfo.getId());
      if (userInfoOptional.isPresent()) {
         User user = userRepository.findById(userId).get();
         UserInfo userInfo1 = userInfoOptional.get();
         String[] get = {userInfo.getEmail(), userInfo.getName(), userInfo.getAge(), userInfo.getSex(), userInfo.getAddress(), userInfo.getAvatarUrl(), userInfo.getBackgroundUrl(), userInfo.getAbout()};
         String[] get1 = {userInfo1.getEmail(), userInfo1.getName(), userInfo1.getAge(), userInfo1.getSex(), userInfo1.getAddress(), userInfo1.getAvatarUrl(), userInfo1.getBackgroundUrl(), userInfo1.getAbout()};
         for (int i = 0; i < 8; i++) {
            if (get[i] == null && get1[i] != null) {
               get[i] = get1[i];
            }
         }
         return new UserInfo(get[0], get[1], get[2], get[3], get[4], get[5], get[6], user, get[7]);

      }
      throw new Exception("Da xay ra loi");
   }
}
