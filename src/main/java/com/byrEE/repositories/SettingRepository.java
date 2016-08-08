package com.byrEE.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * author byrEE
 */

@Repository
public interface SettingRepository extends MongoRepository<Setting,Long>{
	Setting findByKey(String key);
}