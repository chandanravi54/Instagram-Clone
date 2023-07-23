package com.mountblue.instagram.service;

import com.mountblue.instagram.model.Comment;
import com.mountblue.instagram.repository.CommentRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentService commentService;
//In summary, use @Mock for pure Mockito tests when creating isolated mock objects,
// use @MockBean for Spring tests to replace Spring beans with mock implementations during integration testing,
// and use @InjectMocks in Spring tests to automatically inject mocks into the class under test based on field types.
    private ObjectId postId;
    private String commentText;

    @Before
    public void setup() {
        postId = new ObjectId();
        commentText = "Test comment";
    }

    @Test
    public void testAddComment() {
        // Perform the addComment method
        commentService.addComment(postId, commentText);

        // Verify that the save method of the commentRepository was called with the correct argument
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void testDeleteComment() {
        // Perform the deleteComment method
        ObjectId commentId = new ObjectId();
        commentService.deleteComment(commentId);

        // Verify that the deleteById method of the commentRepository was called with the correct argument
        verify(commentRepository, times(1)).deleteById(eq(commentId));
    }
}
