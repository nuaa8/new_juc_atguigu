package com.company.lock;
/**
 * Hashset线程不安全
 *  解决方案：
 *      CopyOnWriteArraySet
 *HashMap线程不安全
 *  解决方案：
 *      ConcurrentHashMap
 */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadDemo5 {
    public static void main(String[] args) {
        //演示Hashset
//        Set<String> set=new HashSet<>();
//        Set<String> set=new CopyOnWriteArraySet<>();
//
//        for(int i=0;i<199;i++){
//            new Thread(()->{
//                //向集合中添加内容
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合中获取内容
//                System.out.println(set);
//            },String.valueOf(i)).start();
//        }


        //演示HashMap
//        Map<String,String> map=new HashMap<>();

        Map<String,String> map=new ConcurrentHashMap<>();
        for(int i=0;i<199;i++){
            String key=String.valueOf(i);
            new Thread(()->{
                //向集合中添加内容
                map.put(key,UUID.randomUUID().toString().substring(0,8));
                //从集合中获取内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
