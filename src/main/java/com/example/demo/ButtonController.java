package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yang
 * @date 2022/3/16-11:12
 **/
@Controller
public class ButtonController {
    private Logger logger= LoggerFactory.getLogger(ButtonController.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("start")
    @ResponseBody
    public void setQueueSwtich(){
        //开始按钮，如果存在该key,就删除掉该key
        if(redisTemplate.hasKey("QUEUE_RUN_STOP")){
            redisTemplate.delete("QUEUE_RUN_STOP");
        }
       
    }
    @GetMapping("stop")
    @ResponseBody
    public void setQueueSwtich2(){
        //停止按钮，如果存在该key,就返回，否则添加一个key
        if(redisTemplate.hasKey("QUEUE_RUN_STOP")){
            return;
        }
        redisTemplate.opsForValue().set("QUEUE_RUN_STOP", "Y");
        logger.info("添加停止标识"+redisTemplate.opsForValue().get("QUEUE_RUN_STOP"));
    }
    @GetMapping("/")
    public String index(){
        return "button";
    }

}
