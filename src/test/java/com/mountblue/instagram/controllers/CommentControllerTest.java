package com.mountblue.instagram.controllers;

import com.mountblue.instagram.service.CommentService;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;
//@Mock for non-Spring tests when creating simple mock objects, and use
    private ObjectId postId;
    private ObjectId commentId;
    private String commentText;
//MockBean for Spring tests to mock Spring beans and isolate dependencies during integration testing.
    @Before
    public void setup() {
      //postId = new ObjectId();
        postId=new ObjectId("609a71fb24a88f0d6b1cd353");
        //commentId = new ObjectId();
        commentId=new ObjectId("609a71fb24a88f0d6b1cd352");
     // commentService=new CommentService();
        commentText = "Test comment";
    }

    @After
    public void teardown() {
        // You can perform any necessary cleanup here, although it might not be needed in this case.
    }

    @Test
    public void testAddComment() throws Exception {
        // Mock the behavior of the CommentService
        doNothing().when(commentService).addComment(eq(postId), eq(commentText));

        // Perform the request and verify the response
        mockMvc.perform(post("/add-comment/" + postId)
                        .param("commentText", commentText))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/all-view/" + postId + "/0"));

        // Verify that the CommentService method was called with the correct arguments
        verify(commentService, times(1)).addComment(eq(postId), eq(commentText));
    }


    @Test
    public void testDeleteComment() throws Exception {
        // Mock the behavior of the CommentService
        ObjectId postId = new ObjectId();
        when(commentService.getPostIdByCommentId(commentId)).thenReturn(postId);
        doNothing().when(commentService).deleteComment(eq(commentId));

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/delete-comment/" + commentId)) // Use the get method from MockMvcRequestBuilders
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/all-view/" + postId + "/0"));

        // Verify that the CommentService method was called with the correct argument
        verify(commentService, times(1)).deleteComment(eq(commentId));
    }
}
