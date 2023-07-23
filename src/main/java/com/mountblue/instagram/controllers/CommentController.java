package com.mountblue.instagram.controllers;

import com.mountblue.instagram.model.Comment;
import com.mountblue.instagram.service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add-comment/{postId}")
    public String addComment(@PathVariable("postId") ObjectId postId, @RequestParam("commentText") String commentText) {
        commentService.addComment(postId,commentText);
        return "redirect:/all-view/" + postId + "/0";
    }
    @GetMapping("/delete-comment/{commentId}")
    public String deleteComment(@PathVariable("commentId") ObjectId commentId) {
        ObjectId postId = commentService.getPostIdByCommentId(commentId);
        commentService.deleteComment(commentId);
        return "redirect:/all-view/" + postId + "/0";
    }

    @GetMapping("/edit-comment/{commentId}")
    public String editCommentPage(@PathVariable("commentId") ObjectId commentId, Model model) {
        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "edit-comment";
    }
    @PostMapping("/edit-comment/{commentId}")
    public String updateComment(@PathVariable("commentId") ObjectId commentId, @RequestParam("updatedCommentText") String updatedCommentText) {
        commentService.updateComment(commentId, updatedCommentText);
        return "redirect:/all-view/" + commentService.getPostIdByCommentId(commentId) + "/0";
    }



}
