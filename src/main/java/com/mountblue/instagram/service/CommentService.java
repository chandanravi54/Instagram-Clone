package com.mountblue.instagram.service;

import com.mountblue.instagram.model.Comment;
import com.mountblue.instagram.repository.CommentRepository;
import com.mountblue.instagram.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    public void addComment(ObjectId postId, String commentText) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setCommentText(commentText);
        commentRepository.save(comment);
    }

    public void deleteComment(ObjectId commentId) {
        commentRepository.deleteById(commentId);
    }

    public ObjectId getPostIdByCommentId(ObjectId commentId) {
        Comment comment = commentRepository.findById(commentId);
        return comment.getPostId();
    }

    public Comment getCommentById(ObjectId commentId) {
        return commentRepository.findById(commentId);

    }

    public void updateComment(ObjectId commentId, String updatedCommentText) {
        Comment comment = commentRepository.findById(commentId);
        comment.setCommentText(updatedCommentText);
        commentRepository.save(comment);
    }
}
