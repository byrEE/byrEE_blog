package com.byrEE.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.byrEE.models.Tag;
import com.byrEE.models.Setting;

import org.springframework.stereotype.Repository;
/**
 * author byrEE
 */

@Repository
public interface TagRepository extends MongoRepository<Tag,Long>{
	Tag findByName(String name);
}