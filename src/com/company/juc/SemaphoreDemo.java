package com.company.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//6辆汽车，3个停车位
public class SemaphoreDemo {
    public static void main(String[] args) {
        //创建Semaphore,设置许可数量
        Semaphore semaphore=new Semaphore(3);//三个停车位

        //模拟6辆汽车（即创建6个线程）
        for(int i=1;i<=6;i++){
            new Thread(()->{
                    try {
                        semaphore.acquire();

                        System.out.println(Thread.currentThread().getName()+" 抢到了车位");
                        //设置随机停车时间
                        TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                        System.out.println(Thread.currentThread().getName()+" ------离开了车位");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        //释放
                        semaphore.release();

                }

            },String.valueOf(i)).start();
        }

    }

}
