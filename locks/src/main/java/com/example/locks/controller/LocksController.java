package com.example.locks.controller;

import cn.hutool.core.date.DateUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author gaoshujiang
 */
@RestController
public class LocksController {
    @Autowired
    RedissonClient redisson;

    @RequestMapping("/uuid")
    @ResponseBody
    public String getUUID(){
        return genCode();
    }
    private String genCode(){
        RLock lock = redisson.getLock("lockName");
        try{
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3. 尝试加锁，最多等待2秒，上锁以后8秒自动解锁
            boolean res = lock.tryLock(2, 60, TimeUnit.SECONDS);
            if(res){ //成功
                //处理业务
                StringBuffer code=new StringBuffer(DateUtil.format(DateUtil.date(), "yyyyMMdd-"));
                code.append(UUID.randomUUID().toString());
                return code.toString();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            //lock.unlock();
        }
        return null;

    }
}
