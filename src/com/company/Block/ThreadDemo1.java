package com.company.Block;

/**
 * 线程间通信
 * 需求：
 *  -两个线程，一个线程对当前数值加 1，另一个线程对当前数值减 1
 *  使用 synchronized 实现进程间通信
 *      关键字 synchronized 与 wait()/notify()这两个方法一起使用可以实现等待/通知模式，
 *
 */

class Share1{
    private int number=0;
    public synchronized void inr() throws InterruptedException {
        //第二步  判断 干活  通知

//        if(number!=0){ //判断number值是否为0.若果不是0，等待
//            this.wait();//在哪里睡，就在哪里醒 ，下次就在这里直接唤醒，接着执行下面的代码
//        }
        //用while代替if
        while(number!=0){
            this.wait();
        }

        //如果Number是0，就+1操作
        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知其他的线程
        this.notify();
    }

    public synchronized void decr() throws InterruptedException {
        //  判断
//        if(number!=1){ //判断number值是否为1.若果不是1，等待
//            this.wait();
//        }

        while (number!=1){
            this.wait();
        }

        //如果Number是1，就-1操作
        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知其他的线程
        this.notify();
    }

}
public class ThreadDemo1 {
    public static void main(String[] args) {
        Share1 share=new Share1();
        new Thread(()->{
            for(int i=1;i<=10;i++){

                try {
                    share.inr();
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
                    share.inr();
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
