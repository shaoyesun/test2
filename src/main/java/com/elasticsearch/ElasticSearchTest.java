package com.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @参考:http://www.656463.com/article/vU7jYr.htm
 * @ 1.添加索引
 * @ 2.删除索引中指定类别的指定记录
 * @ 3.获取指定索引的指定类别的指定记录
 */
public class ElasticSearchTest {

    //创建客户端，所有的操作都由客户端开始，这个就好像是JDBC的Connection对象
    public static Client getClient() {
        Client client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("192.168.8.53", 9300));
        return client;
    }

    //关闭
    public static void closeClient(Client client) {
        client.close();
    }

    //添加索引
    public static void addIndex() {
        Client client = getClient();
        String json = ESUtils.toJson(new LogModel());
        IndexResponse response = client.prepareIndex("secisland", "tweet")
                //必须为对象单独指定ID
                .setId("4")
                .setSource(json)
                .execute()
                .actionGet();
        System.out.println(response.getId());
        closeClient(client);
    }

    //删除索引中指定类别的指定记录
    public static void delIndex() {
        Client client = getClient();
        DeleteResponse response = client.prepareDelete("secisland", "tweet", "4")
                .execute().actionGet();
        System.out.println(response.getId());
        closeClient(client);
    }

    //获取指定索引的指定类别的指定记录
    public static void getIndex() {
        Client client = getClient();
        GetResponse response = client.prepareGet("secisland", "tweet", "1")
                .execute().actionGet();
        System.out.println(response.getSource());
        closeClient(client);
    }

    public static void main(String[] args) {
        //添加索引
        //addIndex();

        //delIndex();

        getIndex();
    }
}
