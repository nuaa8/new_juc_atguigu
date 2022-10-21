package com.company.Block;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  线程间定制化通信
 *       案例介绍
 *    需求: A 线程打印 5 次 A，B 线程打印 10 次 B，C 线程打印 15 次 C,按照
 *    此顺序循环 10 轮==
 */

//第一步 创建类资源
class ShareResource{
    //定义标志位
    private int flag=1;
    //创建lock锁
    private Lock lock=new ReentrantLock();//1 AA 2 BB 3 CC
    //创建三个condition
    private Condition c1= lock.newCondition();
    private Condition c2= lock.newCondition();
    private Condition c3= lock.newCondition();

    //打印5次，参数第几轮
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try{
            //判断
            while (flag!=1){
                //等待
                c1.await();
            }
            //干活
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+" :: "+i+" :轮数:"+loop);
            }
            //通知
            flag=2;//修改标志位 2
            c2.signal();//通知BB线程

        }finally {
            lock.unlock();
        }
    }
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try{
            while (flag!=2){
                c2.await();
            }
            for (int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+" :: "+i+" :轮数:"+loop);
            }

            //通知
            flag=3;//修改标志位 3
            c3.signal();//通知CC线程
        }finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try{
            while (flag!=3){
                c3.await();
            }
            for (int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+" :: "+i+" :轮数:"+loop);
            }

            //通知
            flag=1;//修改标志位 1
            c1.signal();//通知AA线程
        }finally {
            lock.unlock();
        }
    }


}


public class ThreadDemo3 {
    public static void main(String[] args)   {
        ShareResource shareResource=new ShareResource();
        new Thread(()->{
            for(int i=1;i<=10;i++){
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for(int i=1;i<=10;i++){
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for(int i=1;i<=10;i++){
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}
