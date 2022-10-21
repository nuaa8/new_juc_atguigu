package com.company.Ccallable;
/**
 *  6.Callable接口
 *
 *  1.问题：
 *      目前我们学习了有两种创建线程的方法-一种是通过创建 Thread 类，另一种是通过使用 Runnable 创建线程。但是，Runnable 缺少的一项功能是，当线程
 * 终止时（即 run（）完成时），我们无法使线程返回结果。为了支持此功能，Java 中提供了 Callable 接口。
 *  2.方法：找一个类，既和Runnable有关系，又和Callable有关系
 *           Runnable接口有实现类 FutureTask（中间对象）
 *           FutureTask构造可以传递Callable
 *  3.Callable接口的特点如下（重点）
 *     1.为了实现 Runnable，需要实现不返回任何内容的 run（）方法，而对于Callable，需要实现在完成时返回结果的 call（）方法。
 *     2.call（）方法可以引发异常，而 run（）则不能。
 *     3.为实现 Callable 而必须重写 call 方法
 *     4.不能直接替换 runnable,因为 Thread 类的构造方法根本没有 Callable
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
        //找一个类，既和Runnable有关系，又和Callable有关系
        //            Runnable接口有实现类 FutureTask（中间对象）
        //            FutureTask构造可以传递Callable

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
