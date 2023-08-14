package com.mountblue.instagram.repository;

import com.mountblue.instagram.model.Comment;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CommentRepositoryIntegrationTest {

    @Autowired
    private CommentRepository commentRepository;

    private ObjectId postId;
    private Comment savedComment;

    @Before
    public void setup() {
        postId = new ObjectId();
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setCommentText("Test comment");
        savedComment = commentRepository.save(comment);
    }

    @After
    public void teardown() {
        commentRepository.deleteAll();
    }

    @Test
    public void testFindByPostId() {
        List<Comment> comments = commentRepository.findByPostId(postId);
        assertNotNull(comments);
        assertEquals(1, comments.size());
        assertEquals(savedComment.getId(), comments.get(0).getId());
    }

    @Test
    public void testDeleteById() {
        commentRepository.deleteById(savedComment.getId());
        List<Comment> comments = commentRepository.findByPostId(postId);
        assertNotNull(comments);
        assertEquals(0, comments.size());
    }

    @Test
    public void testFindById() {
        Comment foundComment = commentRepository.findById(savedComment.getId());
        assertNotNull(foundComment);
        assertEquals(savedComment.getId(), foundComment.getId());
    }
}
