package com.example.locks.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RedissonConfig {

    @Bean

    public RedissonClient getClient() {

        Config config = new Config();
        String url="127.0.0.1:6379";
        config.useSingleServer()
                .setAddress(url)
                .setDatabase(1);

        try{
            RedissonClient redisson = Redisson.create(config);
            return redisson;
        }catch (Exception e){
            log.error("RedissonClient init redis url:[{}], Exception:", url, e);
            return null;
        }
    }

}
