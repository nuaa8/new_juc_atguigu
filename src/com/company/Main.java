package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
    用户线程:平时用到的普通线程,自定义线程
    守护线程:运行在后台,是一种特殊的线程,比如垃圾回收
    当主线程结束后,用户线程还在运行,JVM 存活
    如果没有用户线程,都是守护线程,JVM 结束
 */
public class Main {

    public static void main(String[] args) {


	// write your code here
        Thread aa=new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"::"+Thread.currentThread().isDaemon());
            while(true){

            }
        },"aa");

        //设置守护线程，在start()之前
        aa.setDaemon(true);
        aa.start();

        System.out.println(Thread.currentThread().getName()+" over");
    }
}
