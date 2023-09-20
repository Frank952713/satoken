package com.example.satoken;

import com.example.satoken.model.User;
import com.example.satoken.redis.RedisKey;
import com.example.satoken.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void contextLoads(){
        User user = new User("zhangsan", 17);
        RedisUtil.set(RedisKey.getImportWorkFormKey(1L),user);
        System.out.println(RedisUtil.get(RedisKey.getImportWorkFormKey(1L)));
    }
}
