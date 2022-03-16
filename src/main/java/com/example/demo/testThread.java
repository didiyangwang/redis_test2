package com.example.demo;

import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.security.RunAs;

/**
 * @author yang
 * @date 2022/3/16-11:53
 **/
@Service
public class testThread implements Runnable {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void run() {
        while(true) {
            //redis中不存在该key时 正常运行
            while (redisTemplate.hasKey("QUEUE_RUN_STOP")) {
                System.out.println("暂停中");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } ;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("正常执行");
        }

    }

    public static void main(String[] args) throws InterruptedException {


        new Thread(new testThread()).start();
        Thread.sleep(1000000);
    }
}
