package com.cst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class StoryController {


    @Autowired
    RedisTemplate redisTemplate;

    static Map<String,Integer> map = new HashMap<>();
    static {
        map.put("ID1",200);
        map.put("ID2",199);
    }
//
//    @GetMapping("/test")
//    public String test(){
//        redisTemplate.opsForValue().set("name",200);
//        Integer name = (Integer)redisTemplate.opsForValue().get("name");
//        return "hello world..."+name;
//    }



    //第一次启动，本地有，redis没有就添加，本地没有，redis有就删除.本地和redis都有就不做
    @GetMapping("/set")
    public String setData(){
        //添加
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("ocr");
        boundZSetOperations.add("id1",0);
        boundZSetOperations.add("id2",0);
        boundZSetOperations.add("id3",0);
        //删除
        boundZSetOperations.remove("id3");

        return "hello world...";
    }

    @GetMapping("/reset")
    public String resetAll(){
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("ocr");
        Set<String> range = boundZSetOperations.range(0, -1);
        range.forEach(e->{
            boundZSetOperations.add(e,0);
        });
        return "hello world...";
    }

    //获取指定区间的值
    @GetMapping("/get")
    public String getData(){
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("ocr");

        boundZSetOperations.rangeByScore(0,200).forEach(v -> {
            System.err.println("集合中的值:" + v);
        });
        return "hello world...";
    }

    //自增
    @GetMapping("/incr")
    public String incrreq(){
        incr("id1");
        return "hello world...decr";
    }

    public String incr(String key){
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("ocr");
        boundZSetOperations.incrementScore(key,1);
        return "hello world...decr";
    }





}
