package com.company.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share{
    private int number=0;

    private Lock lock=new ReentrantLock();
    private Condition condition= lock.newCondition();

    //+1
    public void inc() throws InterruptedException {
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
                    share.inc();
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
                    share.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
