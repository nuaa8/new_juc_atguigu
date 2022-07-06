package com.company.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//集齐7颗龙珠就可以召唤神龙
public class CyclicBarrierDemo {
    //创建固定值
    private static final int NUMBER=7;

    public static void main(String[] args) {
        //创建CyclicBarrier对象
        CyclicBarrier cyclicBarrier=new CyclicBarrier(NUMBER,()->{
            System.out.println("*****集齐7颗龙珠就可以召唤神龙");
        });

        //集齐7颗龙珠的过程
        for(int i=1;i<=7;i++){
            //创建线程
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 星龙珠被收集到了");

                //等待（不到7个龙珠，就等待）
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }


    }

}
