package com.classTest.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * Created by root on 16-12-9.
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
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
    }


}
