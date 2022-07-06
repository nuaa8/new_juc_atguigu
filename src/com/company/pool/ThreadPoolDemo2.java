package com.company.pool;
/**
 *
 */

import java.util.concurrent.*;

//自定义线程池创建
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy()
        );

        try{
            //10个顾客请求
            for(int i=1;i<=10;i++){
                //执行
                threadPool.execute(()->{  //当执行 execute()时，创建线程
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            //关闭
            threadPool.shutdown();
        }


    }
}
