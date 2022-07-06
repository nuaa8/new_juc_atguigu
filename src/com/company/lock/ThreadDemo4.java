package com.company.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 5.集合的线程安全
 *      1.ArrayList集合线程不安全
 *      解决方法：
 *          1.Vector
 *          2.Collections
 *          3.CopyOnWriteArrayList
*       2.Hashset线程不安全
 *      解决方案：
 *          CopyOnWriteArraySet
 *      3.HashMap线程不安全
 *      解决方案：
 *           ConcurrentHashMap
 *      list集合线程不安全
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        //创建ArrayList集合
//        List<String> list=new ArrayList<>();

        //1.Vector解决
//        List<String> list=new Vector<>();  //方法，比较古老，不推荐用

        //2.Collections解决
//        List<String> list= Collections.synchronizedList(new ArrayList<>());//方法，比较古老，不推荐用

        //3.CopyOnWriteArrayList（写时复制技术，先复制内容，在写入内容，最后覆盖合并）解决, 推荐使用该方法
        List<String> list=new CopyOnWriteArrayList<>();

        for(int i=0;i<19;i++){
            //当多个线程一边存一边取的时候，会发生  并发修改异常
            new Thread(()->{
                //向集合中添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合中获取内容
                System.out.println(list);  //之所以会发生错误，是因为可能有的数据还没有存入
            },String.valueOf(i)).start();
        }
    }
}
