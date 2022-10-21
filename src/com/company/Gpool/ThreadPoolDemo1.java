package com.company.Gpool;
/**
 * 11.线程池
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//演示线程池三种常用分类
public class ThreadPoolDemo1 {
    public static void main(String[] args) {

        //一池N线程    一池五线程（即5个银行柜台员工号，处理10个顾客的请求）
//        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);

        //一池一线程
//        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();

        //一池可扩容线程(根据需求，自己随机创建几个线程)
        ExecutorService threadPool1 = Executors.newCachedThreadPool();
        try{
            //10个顾客请求
            for(int i=1;i<=10;i++){
                //执行
                threadPool1.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");//pool-1-thread-5  线程池默认名字
                });
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            //关闭
            threadPool1.shutdown();
        }

    }
}
