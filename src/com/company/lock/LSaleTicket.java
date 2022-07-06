package com.company.lock;
/**
 * 2.Lock接口
 */

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.locks.ReentrantLock;

//第一步  创建资源类，定义属性和操作方法
class LTicket {
    private int number = 30;

    //创建可重入锁
    private final ReentrantLock lock=new ReentrantLock();
    //买票方法
    public void sale(){
        //上锁
        lock.lock();
        try{
        if(number>0){
            System.out.println(Thread.currentThread().getName() + " :卖出:" + (number--) + " 剩下：" + number);
        }
    } finally {
        lock.unlock();
        }
    }

}
public class LSaleTicket {
    //第二步  创建多个线程，调用资源类的操作方法
    //创建多个线程
    public static void main(String[] args) {
        LTicket ticket=new LTicket();

        new Thread(()->{
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
