package com.mountblue.instagram.service;

import com.mountblue.instagram.model.Comment;
import com.mountblue.instagram.repository.CommentRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CommentServiceIntegrationTest {
    private ObjectId postId;
    @Autowired
    private CommentRepository commentRepository;
    private CommentService commentService;

    @Before
    public void setup() {
        postId = new ObjectId();
        commentService = new CommentService(commentRepository);
    }

    @Test
    public void testAddComment() {
        ObjectId postId = new ObjectId();
        String commentText = "Test comment";

        commentService.addComment(postId, commentText);

        // Retrieve the saved comment from the database and assert its correctness
        Comment savedComment = commentRepository.findByPostId(postId).get(0);
        assertEquals(commentText, savedComment.getCommentText());
    }

    @Test
    public void testDeleteComment() {
        Comment comment = new Comment();
        commentRepository.save(comment);

        commentService.deleteComment(comment.getId());

        // Attempt to retrieve the deleted comment from the database
        Comment deletedComment = commentRepository.findById(comment.getId());
        assertNull(deletedComment);
    }
    @Test
    public void testUpdateComment() {
        String updatedCommentText = "Updated comment text";

        Comment comment = new Comment();
        comment.setId(postId);
        comment.setCommentText("Old comment text");
        commentRepository.save(comment);

        commentService.updateComment(postId, updatedCommentText);

        Comment updatedComment = commentRepository.findById(postId);
        assertEquals(updatedCommentText, updatedComment.getCommentText());
    }
}
