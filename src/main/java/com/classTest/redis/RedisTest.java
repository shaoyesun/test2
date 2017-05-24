package com.classTest.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * Created by root on 16-12-9.
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        jedis.set("runoobkey", "Redis tutorial");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: "+ jedis.get("runoobkey"));

        List<String> list = new ArrayList<String>(jedis.keys("*"));
        for(int i=0; i<list.size(); i++) {
            System.out.println("List of stored keys:: "+list.get(i));
        }

        //redis操作Map
        hmset(jedis);
    }

    //redis操作Map
    public static void hmset(Jedis jedis){
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "jedis");
        map.put("age", "22");
        map.put("qq", "jedis");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);
    }


}
