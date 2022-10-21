package com.company.Block;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间通信
 *  -两个线程，一个线程对当前数值加 1，另一个线程对当前数值减 1
 *  使用 Lock 实现进程间通信
 *      Lock 锁的 newContition()方法返回 Condition 对象，Condition 类也可以实现等待/通知模式。
 */

//第一步  创建资源类，定义属性和操作方法
class Share{
    private int number=0;

    //创建Lock
    private Lock lock=new ReentrantLock();
    private Condition condition= lock.newCondition();

    //+1
    public void inc() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            //通知
            condition.signalAll();
        } finally {
            //解锁
            lock.unlock();
        }
    }
    //-1
    public void decr() throws InterruptedException {
            lock.lock();
            try {
                while (number!=1){
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName()+"::"+number);
            } finally{
                lock.unlock();
            }
        }

}
public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share=new Share();
        new Thread(()->{
            for(int i=1;i<=10;i++){
                try {
                    share.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for(int i=1;i<=10;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for(int i=1;i<=10;i++){
                try {
                    share.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for(int i=1;i<=10;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
