package org.tikim.boot.controller.sample;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("RedisSampleController")
@RequestMapping(value = "/sample/redis")
public class RedisSampleController {
    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @GetMapping(value = "/test")
    public ResponseEntity<String> test() {
        valueOperations.set("test","테스트2");
        return new ResponseEntity<String>(valueOperations.get("test"), HttpStatus.OK);
    }
}
