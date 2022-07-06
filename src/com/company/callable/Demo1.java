package com.company.callable;
/**
 *  6.Callable接口
 *      找一个类，既和Runnable有关系，又和Callable有关系
 *           Runnable接口有实现类 FutureTask（中间对象）
 *           FutureTask构造可以传递Callable
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

//比较两个接口
//实现Runnable接口
class MyThread1 implements Runnable{
    @Override
    public void run() {

    }
}
//实现Callable接口
class MyThread2 implements Callable{
    @Override
    public Object call() throws Exception {
        return 200;
    }
}
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口创建线程
        new Thread(new MyThread1(),"AA").start();

        //Callable接口创建线程
        //下面方式，报错
//        new Thread(new MyThread2(),"BB").start();

        //FutureTask
        FutureTask<Integer> futureTask1=new FutureTask<>(new MyThread2());

        //lam表达式简化
        FutureTask<Integer> futureTask2=new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+" come is callable");
            return 1024;
        });

        //创建一个线程
        new Thread(futureTask2,"lucy").start();

        while(!futureTask2.isDone()){
            System.out.println("wait....");
        }

        //调用FutureTask的get方法
        System.out.println(futureTask2.get());

        System.out.println(Thread.currentThread().getName()+" come over");

        //FutureTask原理（在不影响主线程的情况下，单开几个线程执行其他任务，最后 汇总一次）  未来任务
        /**
         * 1.老师上课，口渴了，去买不合适，讲课线程继续
         *  单开启线性找班长帮我买水，把水买回来，需要时直接get
         */
    }
}
