package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.delete("QUEUE_RUN_STOP");
//        redisTemplate.opsForValue().set("a", "1");
//        Object a = redisTemplate.opsForValue().get("a");
//        System.out.println(a);
    }

}
