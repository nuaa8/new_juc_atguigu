package com.company.lock;

class Share1{
    private int number=0;
    public synchronized void inr() throws InterruptedException {
        //第二步  判断 干活  通知
        if(number!=0){ //判断number值是否为0.若果不是0，等待
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
        if(number!=1){ //判断number值是否为1.若果不是1，等待
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
