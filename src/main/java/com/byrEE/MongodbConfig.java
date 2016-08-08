package com.byrEE;

import java.util.Properties;


import com.byrEE.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MongodbConfig {

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
             
    
}
