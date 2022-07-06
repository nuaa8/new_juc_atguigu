package com.company.juc;
/**
 * 8.JUC强大的辅助类
 *      1.减少计数CountDownLatch
 *      2.循环栅栏CyclicBarrier
 *      3.信号灯Semaphore
 *
 */

import java.util.concurrent.CountDownLatch;

//演示CountDownLatch
public class CountDownLatchDemo {
    //6个同学离开教室之后，班长锁门
    public static void main(String[] args) throws InterruptedException {

        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch=new CountDownLatch(6);

        //6个同学离开教室之后
        for(int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 号同学离开了教室");

                //计数 -1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //等待(不等于0，一直等待)
        countDownLatch.await();
        //等于0，才执行这段代码
        System.out.println(Thread.currentThread().getName()+" 班长锁门走人了");
    }
}
