package com.byrEE.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * author byrEE
 */

@Repository
public interface UserRepository extends MongoRepository<User,Long>{
	User findByEmail(String email);
}