package com.aexsharing.api.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(final String username);

    @Query(value = "{" +
            "    username:?0,\n" +
            "    password:?1}\n" +
            "}")
    User findByUnameAndPwd(String username, String password);
}
