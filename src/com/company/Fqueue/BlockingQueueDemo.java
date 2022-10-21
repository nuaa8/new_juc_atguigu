package com.company.Fqueue;
/**
 * 10.阻塞队列
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);

//        //第一组
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.element());
//
////        System.out.println(blockingQueue.add("w")); //抛出异常
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
////        System.out.println(blockingQueue.remove());   //抛出异常
//        System.out.println("-------");
//
//        //第二组
//        System.out.println(blockingQueue.offer("e"));
//        System.out.println(blockingQueue.offer("f"));
//        System.out.println(blockingQueue.offer("g"));
//        System.out.println(blockingQueue.offer("h"));
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());

        //第三组
//        blockingQueue.put("i");
//        blockingQueue.put("j");
//        blockingQueue.put("k");
//        blockingQueue.put("l");
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());

        //第四组
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d",3L, TimeUnit.SECONDS));//超时退出
    }


}
