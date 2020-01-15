package com.dbardarov.blackfriday.dao.repository;

import com.dbardarov.blackfriday.dao.entity.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends MongoRepository<UserSession, String> {
}
