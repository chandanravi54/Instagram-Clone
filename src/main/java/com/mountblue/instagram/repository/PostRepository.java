package com.mountblue.instagram.repository;

import com.mountblue.instagram.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, Integer> {
    Post findByPostId(ObjectId id);
}
