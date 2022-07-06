package com.company.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（又叫递归锁,进入第一道大门，里面的门都可以进入）：即可以随意进出
 *      synchronized(隐式)和Lock（显示）都是可重入锁
 */
public class SyncLockDemo {

    //同步方法
    public synchronized void add(){
        add();
    }
    public static void main(String[] args) {
        //Lock可重入锁
        Lock lock=new ReentrantLock();
        //创建线程
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "外层");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "内层");
                } finally {
                    lock.unlock();
                }
            }finally{
                    lock.unlock();
                }
        },"t1").start();


//        new SyncLockDemo().add();

//        synchronized
//    Object o=new Object();
//    new Thread(()->{
        //同步代码块
//        synchronized(o){
//            System.out.println(Thread.currentThread().getName()+" 外层");
//            synchronized(o){
//                System.out.println(Thread.currentThread().getName()+" 中层");
//                synchronized(o){
//                    System.out.println(Thread.currentThread().getName()+" 内层");
//                }
//            }
//        }
//    },"t1").start();




    }
}
