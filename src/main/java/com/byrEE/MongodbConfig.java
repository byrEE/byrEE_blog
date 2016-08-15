package com.byrEE;

import java.util.Properties;


import com.byrEE.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Arrays;

//import java.util.ResourceBundle;
  
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.data.mongodb.MongoDbFactory;
//import com.mongodb.MongoClientOptions.Builder;
//import com.mongodb.MongoClientURI;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
 
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan
@EnableMongoRepositories
public class MongodbConfig extends AbstractMongoConfiguration{

    /*
    @Value("${mongo.connectionsPerHost}")
    private String connectionsPerHost;
    @Value("${mongo.threadsAllowedToBlockForConnectionMultiplier}")
    private String threadsAllowedToBlockForConnectionMultiplier;
    @Value("${mongo.connectTimeout}")
    private String connectTimeout;
    @Value("${mongo.maxWaitTime}")
    private String maxWaitTime;
    @Value("${mongo.autoConnectRetry}")
    private String  autoConnectRetry;
    @Value("${mongo.socketKeepAlive}")
    private String socketKeepAlive;
    @Value("${mongo.socketTimeout}")
    private String socketTimeout;
    @Value("${mongo.slaveOk}")
    private String slaveOk;
    @Value("1")
    private int writeNumber;
    @Value("0")
    private int writeTimeout;
    @Value("true")
    private boolean writeFsync;    

    */ 
   
    @Value("${mongo.database}")
    private String dbname;
   
    @Value("${mongo.host}")
    private String dbhost;
   
    @Value("${mongo.port}")
    private String dbport;
     
    @Value("${mongo.username}")
    private String username;
     
    @Value("${mongo.password}")
    private String password;
     
    @Override
    protected String getDatabaseName() {
        return this.dbname;
    }

    public MongodbConfig(){
            if(null == dbport || "".equalsIgnoreCase(dbport.trim())){
                dbport = "27017";
            }
        }

    @Override
    @Bean(name = "mongodb")
    public Mongo mongo() throws Exception {
        ServerAddress serverAdress = new  ServerAddress(dbhost, Integer.valueOf(dbport));        
        MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbname , password.toCharArray());
        //Do not use new Mongo(), is deprecated.
        Mongo mongo =  new MongoClient(serverAdress, Arrays.asList(credential));
        mongo.setWriteConcern(WriteConcern.SAFE);
        return mongo;
    }

    /*

    private static final ResourceBundle bundle = ResourceBundle.getBundle("mongodb");

    private String uri = bundle.getString("mongo.uri");

    @Bean
    public  MongoDbFactory mongoDbFactory() throws Exception {
        // mongo连接池的参数
        MongoClientOptions.Builder mongoClientOptions =
            MongoClientOptions.builder().socketTimeout(3000).connectTimeout(3000)
                .connectionsPerHost(20);
        // 设置连接池
        MongoClientURI mongoClientURI = new MongoClientURI(uri, mongoClientOptions);
        return new SimpleMongoDbFactory(mongoClientURI);
    }      
    */
             
    
}
