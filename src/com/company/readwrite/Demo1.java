package com.company.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

//演示读写锁降级(写锁 降级为  读锁 )
public class Demo1 {
    public static void main(String[] args) {
        //可重入读写锁对象
        ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();//读锁
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();//写锁

        //锁降级
        //1 获取写锁
        writeLock.lock();
        System.out.println("atguigu");

        //2 获取读锁
        readLock.lock();
        System.out.println("-----read");

        //3 释放写锁
        writeLock.unlock();
        //4 释放读锁
        readLock.unlock();
    }
}
