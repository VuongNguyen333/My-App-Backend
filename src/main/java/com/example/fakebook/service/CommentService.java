package com.example.fakebook.service;

import com.example.fakebook.model.entity.Comment;
import com.example.fakebook.model.entity.NotificationUser;
import com.example.fakebook.model.entity.PostUser;
import com.example.fakebook.model.entity.UserInfo;
import com.example.fakebook.repository.ICommentRepository;
import com.example.fakebook.repository.INotificationRepository;
import com.example.fakebook.repository.IPostUserRepository;
import com.example.fakebook.repository.IUserInfoRepository;
import com.example.fakebook.request.CreateCommentReq;
import com.example.fakebook.request.DeleteCommentReq;
import com.example.fakebook.request.PostIdReq;
import com.example.fakebook.request.ReqId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

   @Autowired
   private ICommentRepository commentRepository;

   @Autowired
   private IPostUserRepository postUserRepository;

   @Autowired
   private IUserInfoRepository userInfoRepository;

   @Autowired
   private INotificationRepository notificationRepository;

   private List<Comment> show(ReqId reqId) {
      return commentRepository.showAllByPost(reqId.getId());
   }

   public List<Comment> showAllComment(PostIdReq postIdReq) {
      return commentRepository.showAllByPost(postIdReq.getPostId());
   }

   @Transactional
   public Comment newComment(CreateCommentReq createCommentReq) {
      PostUser postUser = postUserRepository.findById(createCommentReq.getPostId()).get();
      UserInfo userInfo = userInfoRepository.findById(createCommentReq.getUserInfoId()).get();
      Comment newComment = new Comment(
             createCommentReq.getContent(),
             new Date(),
             userInfo,
             postUser
      );
      commentRepository.save(newComment);
      notificationRepository.save(new NotificationUser(
             new Date(),
             userInfo.getName() + " đã bình luận về bài viết của bạn!",
             userInfo,
             postUser.getUserInfo(),
             postUser.getId()
      ));
      return newComment;
   }

   @Transactional
   public void deleteComment(DeleteCommentReq deleteCommentReq) throws Exception {
      Comment comment = commentRepository.findById(deleteCommentReq.getCommentId()).get();
      UserInfo postUser = comment.getUserInfo();
      if (postUser.getId() != deleteCommentReq.getUserInfoId()) {
         throw new Exception("Thất bại");
      } else {
         commentRepository.delete(comment);
      }
   }
}
