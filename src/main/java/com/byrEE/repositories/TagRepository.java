package com.byrEE.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * author byrEE
 */

@Repository
public interface TagRepository extends MongoRepository<Tag,Long>{
	Setting findByName(String name);
}