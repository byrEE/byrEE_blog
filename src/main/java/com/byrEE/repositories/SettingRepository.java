package com.byrEE.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.byrEE.models.Setting;
import org.springframework.stereotype.Repository;
/**
 * author byrEE
 */

@Repository
public interface SettingRepository extends MongoRepository<Setting,Long>{
	Setting findByKey(String key);
}