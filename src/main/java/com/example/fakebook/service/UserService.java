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

   @Autowired
   private IUserInfoRepository userInfoRepository;

   @Transactional
   public User addNewUser(ReqRegister reqRegister) throws IllegalAccessException {
      Optional<User> userOptional = userRepository.findByUsername(reqRegister.getUsername());
      if (userOptional.isPresent()) {
         throw new IllegalAccessException("Đã tồn tại tài khoản");
      } else if (!Objects.equals(reqRegister.getPassword(), reqRegister.getConfirmPassword())) {
         throw new IllegalAccessException("Không trùng khớp");
      } else {
         User user = new User(reqRegister.getUsername(), reqRegister.getPassword());
         userInfoRepository.save(new UserInfo(
                reqRegister.getUsername(),
                "https://media.istockphoto.com/vectors/default-profile-picture-avatar-photo-placeholder-vector-illustration-vector-id1223671392?k=6&m=1223671392&s=612x612&w=0&h=NGxdexflb9EyQchqjQP0m6wYucJBYLfu46KCLNMHZYM=",
                "https://th.bing.com/th/id/R.ca4d1d61d748cf96ca974337c158376d?rik=e9JslLpSTdOI4w&pid=ImgRaw&r=0",
                user
         ));
         return userRepository.save(user);
      }
   }

   @Transactional
   public Optional<UserInfo> checkLogin(User user) throws Exception {
      Optional<User> userOptional = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
      if (userOptional.isPresent()) return userInfoRepository.findByUserId(userOptional.get().getId());
      else throw new Exception("Thông tin tài khoản chưa chính xác, hãy chắc chắn rằng bạn đã đăng ký!");
   }

   @Transactional
   public User updatePasswordUser(ReqUpdatePass reqUpdatePass) throws Exception {
      if (reqUpdatePass.getOldPass() == reqUpdatePass.getNewPass()) {
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
