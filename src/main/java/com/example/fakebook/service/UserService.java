package com.example.fakebook.service;

import com.example.fakebook.model.entity.User;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.repository.IUserInfoRepository;
import com.example.fakebook.repository.IUserRepository;
import com.example.fakebook.request.ReqRegister;
import com.example.fakebook.request.ReqUpdatePass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
   @Autowired
   private IUserRepository userRepository;

   @Transactional
   public void addNewUser(ReqRegister reqRegister) throws IllegalAccessException {
      Optional<User> userOptional = userRepository
             .findByUsername(reqRegister.getUsername());
      if (userOptional.isPresent()) {
         throw new IllegalAccessException("Đã tồn tại tài khoản");
      } else if (!Objects.equals(reqRegister.getPassword(), reqRegister.getConfirmPassword())) {
         throw new IllegalAccessException("Không trùng khớp");
      }
      userRepository.save(userOptional.get());
   }

   @Transactional
   public Optional<User> checkLogin(User user) throws Exception {
      Optional<User> userOptional = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
      if (userOptional.isPresent()) return userOptional;
      else throw new Exception("Thông tin tài khoản chưa chính xác, hãy chắc chắn rằng bạn đã đăng ký!");
   }

   @Transactional
   public User updatePasswordUser(ReqUpdatePass reqUpdatePass) throws Exception {
      if (Objects.equals(reqUpdatePass.getNewPass(), reqUpdatePass.getOldPass())) {
         throw new Exception("Vui long chon mat khau khac");
      } else {
         Optional<User> userOptional = userRepository.findById(reqUpdatePass.getId());
         if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!Objects.equals(user.getPassword(), reqUpdatePass.getOldPass())) {
               throw new Exception("Mat khau chua chinh xac!");
            }
            user.setPassword(reqUpdatePass.getNewPass());
            return userRepository.save(user);
         }
      }
      return null;
   }

   @Transactional
   public void deleteUser(Long id) throws Exception {
      Optional<User> userOptional = userRepository.findById(id);
      if (userOptional.isPresent()) {
         userRepository.delete(userOptional.get());
      } else {
         throw new Exception("That bai");
      }
   }
}
