package com.mountblue.instagram.repository;

//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//The purpose of @DataMongoTest is to provide an environment for testing MongoDB repositories with real interactions with the database,
//        but in an isolated context so that your tests don't modify the actual production data.
//        The embedded MongoDB database starts fresh with each test, ensuring test data independence and consistency.
//

//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class CommentRepositoryTest {
//    @Autowired
//    private CommentRepository commentRepository;
//    private ObjectId postId;
//    private Comment savedComment;
//    @Before
//    public void setup() {
//        postId = new ObjectId();
//        Comment comment = new Comment();
//        comment.setPostId(postId);
//        comment.setCommentText("Test comment");
//        savedComment = commentRepository.save(comment);
//    }
//    @After
//    public void teardown() {
//        commentRepository.deleteAll();
//    }
//    @Test
//    public void testFindByPostId() {
//        List<Comment> comments = commentRepository.findByPostId(postId);
//        assertNotNull(comments);
//        assertEquals(1, comments.size());
//        assertEquals(savedComment.getId(), comments.get(0).getId());
//    }
//    @Test
//    public void testDeleteById() {
//        commentRepository.deleteById(savedComment.getId());
//        List<Comment> comments = commentRepository.findByPostId(postId);
//        assertNotNull(comments);
//        assertEquals(0, comments.size());
//    }
//    @Test
//    public void testFindById() {
//        Comment foundComment = commentRepository.findById(savedComment.getId());
//        assertNotNull(foundComment);
//        assertEquals(savedComment.getId(), foundComment.getId());
//    }
//}

import com.mountblue.instagram.model.Comment;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
//@DataMongoTest
public class CommentRepositoryTest {

    @Mock
    private CommentRepository commentRepository;

    private ObjectId postId;
    private Comment savedComment;

    @Before
    public void setup() {
        postId = new ObjectId();
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setCommentText("Test comment");
        savedComment = new Comment();
        savedComment.setId(new ObjectId("609a71fb24a88f0d6b1cd352"));
        savedComment.setPostId(postId);
        savedComment.setCommentText("Test comment");
        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);
        when(commentRepository.findByPostId(eq(postId))).thenReturn(Arrays.asList(savedComment));
        when(commentRepository.findById(eq(savedComment.getId()))).thenReturn(savedComment);
    }

    @After
    public void teardown() {
        reset(commentRepository);
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
        verify(commentRepository, times(1)).deleteById(eq(savedComment.getId()));
    }

    @Test
    public void testFindById() {
        Comment foundComment = commentRepository.findById(savedComment.getId());
        assertNotNull(foundComment);
        assertEquals(savedComment.getId(), foundComment.getId());
    }
}
