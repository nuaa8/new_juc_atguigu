package com.company.Async;

/**
 *
 *
 * 多线程编程步骤（上部）
 *       第一步：创建资源类，定义属性和操作方法
 *       第二步：创建多线程，调用资源类的操作方法
 *
 * 需求：3个售票员，卖出30张票
 * 使用  synchronized  实现需求
 *
 */

//第一步  创建 资源类，定义属性和操作方法
class Ticket {
    //票数
    private int number = 30;
    //操作方法：卖票
    public synchronized void sale() {
        //判断：是否有票
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + " :卖出:" + (number--) + " 剩下：" + number);
        }
    }
}
public class SaleTicket {

    public static void main(String[] args) {
        //第二步  创建多个线程，调用资源类的操作方法
        Ticket ticket=new Ticket();
        //创建三个线程
        new Thread(new Runnable(){ //匿名内部类
            @Override
            public void run() {
                //调用卖票方法
                for(int i=0;i<40;i++){
                    ticket.sale();
                }
            }
        },"AA").start();  //AA线程一直占用资源，采用 synchronized 不需要用户去手动释放锁，当 synchronized 方法或者 synchronized 代码块执行完之后，
        //系统会自动让线程释放对锁的占用；而 Lock 则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。

        new Thread(new Runnable(){
            @Override
            public void run() {
                //调用卖票方法
                for(int i=0;i<40;i++){
                    ticket.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                //调用卖票方法
                for(int i=0;i<40;i++){
                    ticket.sale();
                }
            }
        },"CC").start();

    }
}
