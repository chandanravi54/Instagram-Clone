package com.mountblue.instagram.controllers;

import com.mountblue.instagram.model.Comment;
import com.mountblue.instagram.repository.CommentRepository;
import com.mountblue.instagram.service.CommentService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentService commentService;

    private ObjectId postId;
    @Autowired
    private CommentRepository commentRepository;
    @Before
    public void setup() {
        postId = new ObjectId();
    }

    @Test
    @Transactional
    public void testAddComment() throws Exception {
        String commentText = "Test comment";

        mockMvc.perform(MockMvcRequestBuilders.post("/add-comment/" + postId)
                        .param("commentText", commentText)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/all-view/" + postId + "/0"));
        List<Comment> comments = commentRepository.findByPostId(postId);
        assertEquals(1, comments.size());
        assertEquals(commentText, comments.get(0).getCommentText());
        // You can add further assertions or verifications here
    }

//    @Test
//    @Transactional
//    public void testDeleteComment1() throws Exception {
//        Comment comment = new Comment();
//        String commentText = "Test comment";
//        Comment savedComment = commentService.addComment(postId,commentText); // Assuming this method is available in your service
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/delete-comment/" + savedComment.getId()))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//
//        // You can add further assertions or verifications here
//    }
@Test
public void testDeleteComment() throws Exception {
    ObjectId postId = new ObjectId();
    String commentText = "Test comment";

    // Add a comment to the database for testing deletion
    Comment comment = new Comment();
    comment.setPostId(postId);
    comment.setCommentText(commentText);
    Comment savedComment = commentRepository.save(comment);

    mockMvc.perform(MockMvcRequestBuilders.get("/delete-comment/" + savedComment.getId()))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

    // Verify that the comment was deleted from the database
    List<Comment> comments = commentRepository.findByPostId(postId);
    assertEquals(0, comments.size());
}
}
