package com.mountblue.instagram.repository;

import com.mountblue.instagram.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    void deleteByUserName(String userName);

    User findByUserName(String userName);

    User findById(ObjectId id);

}
