package com.client.demo.spring.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "file:${system_property}/mongodb.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:mongodb.properties")
})
public class MongoProperty {
    @Value("${mongo.host}")
    private String host;
    @Value("${mongo.port}")
    private int port;
    @Value("${mongo.username}")
    private String username;
    @Value("${mongo.password}")
    private String password;
    @Value("${mongo.dbname}")
    private String dbname;
    @Value("${mongo.connectionsPerHost}")
    private int connectionsPerHost;
    @Value("${mongo.threadsAllowedToBlockForConnectionMultiplier}")
    private int multiplier;
    @Value("${mongo.connectTimeout}")
    private int connectTimeout;
    @Value("${mongo.maxWaitTime}")
    private int maxWaitTime;
    @Value("${mongo.socketKeepAlive}")
    private boolean socketKeepAlive;
    @Value("${mongo.socketTimeout}")
    private int socketTimeout;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDbname() {
        return dbname;
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public boolean isSocketKeepAlive() {
        return socketKeepAlive;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }
}
