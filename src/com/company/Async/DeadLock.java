package com.company.Async;

import java.util.concurrent.TimeUnit;

/**
 * 死锁：
 *      多个进程在执行时，因竞争资源而造成一种相互等待的现象，若果没有外力的干预，他们无法执行下去
 * 产生死锁的原因：
 *      1.系统资源不足
 *      2.进程运行推进顺序不合适
 *      3.资源分配不当
 *
 *  验证是否是死锁
 *      1.jps  类似linux ps -ef
 *      2.jstack jvm 自带堆栈跟踪工具
 *
 * 演示死锁
 */
public class DeadLock {
    //创建两个对象
    static Object a=new Object();
    static Object b=new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized(a){
                System.out.println(Thread.currentThread().getName()+" 持有锁a,试图获取锁b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(b){
                    System.out.println(Thread.currentThread().getName()+" 获取锁b");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized(b){
                System.out.println(Thread.currentThread().getName()+" 持有锁b,试图获取锁a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(a){
                    System.out.println(Thread.currentThread().getName()+" 获取锁a");
                }
            }
        },"B").start();
    }

}
