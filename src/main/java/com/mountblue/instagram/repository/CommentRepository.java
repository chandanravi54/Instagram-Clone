package com.mountblue.instagram.repository;

import com.mountblue.instagram.model.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,Integer> {
    List<Comment> findByPostId(ObjectId id);
    void deleteById(ObjectId commentId);

    Comment findById(ObjectId commentId);
}
