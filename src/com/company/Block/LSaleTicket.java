package com.company.Block;
/**
 * 2.Lock接口
 *
 *  需求：3个售票员，卖出30张票
 *
 * 使用  Lock  实现需求
 *
 *  1.Lock 锁实现提供了比使用同步方法和语句可以获得的更广泛的锁操作。它们允许更灵活的结构，可能具有非常不同的属性，并且可能支持多个关联的条件对
 * 象。Lock 提供了比 synchronized 更多的功能。
 *  2.Lock 不是 Java 语言内置的，synchronized 是 Java 语言的关键字，因此是内置特性。Lock 是一个类，通过这个类可以实现同步访问；
 *  3.Lock 和 synchronized 有一点非常大的不同，采用 synchronized 不需要用户去手动释放锁，当 synchronized 方法或者 synchronized 代码块执行完之后，
 * 系统会自动让线程释放对锁的占用；而 Lock 则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。
 *
 *
 */

import java.util.concurrent.locks.ReentrantLock;

//第一步  创建资源类，定义属性和操作方法
class LTicket {
    private int number = 30;

    //创建可重入锁
    private final ReentrantLock lock=new ReentrantLock();//ReentrantLock(),或者ReentrantLock(false)非公平锁，都被AA卖出去了，ReentrantLock(true)公平锁
    //卖票方法
    public void sale(){
        //上锁
        lock.lock();
        try{
        if(number>0){
            System.out.println(Thread.currentThread().getName() + " :卖出:" + (number--) + " 剩下：" + number);
        }
    } finally {
        //解锁
        lock.unlock();
        }
    }

}
public class LSaleTicket {
    //第二步  创建多个线程，调用资源类的操作方法
    //创建多个线程
    public static void main(String[] args) {

        LTicket ticket=new LTicket();

        new Thread(()->{  //lambda表达式
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"AA").start();

        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"BB").start();

        new Thread(()->{
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        },"CC").start();
    }
}
