package com.example.fakebook.service;

import com.example.fakebook.model.entity.NotificationUser;
import com.example.fakebook.repository.INotificationRepository;
import com.example.fakebook.request.ReqId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
   private final INotificationRepository notificationRepository;

   public NotificationService(INotificationRepository notificationRepository) {
      this.notificationRepository = notificationRepository;
   }

   public List<NotificationUser> showAllByUserId(Long id) throws Exception {
      return notificationRepository.showAll(id);
   }

   @Transactional
   public void deleteById(Long id) throws Exception {
      Optional<NotificationUser> notificationUserOptional
             = notificationRepository.findById(id);
      if (notificationUserOptional.isPresent()) {
         notificationRepository.deleteById(id);
      } else {
         throw new Exception("Khong ton tai");
      }
   }

   @Transactional
   public void createNotification(NotificationUser notificationUser) throws Exception {
      notificationRepository.save(notificationUser);
   }

   @Transactional
   public void deleteAllById(ReqId reqId) {
      notificationRepository.deleteAllByToUserInfoId(reqId.getId());
   }

}
