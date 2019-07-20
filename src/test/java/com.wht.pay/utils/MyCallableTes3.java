//package com.wht.pay.utils;
//
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
//public class MyCallableTes3 {
//
//  public static void main(String[] args) throws ExecutionException, InterruptedException {
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//    executor.setMaxPoolSize(20);
//    executor.setCorePoolSize(5);
//    executor.setQueueCapacity(5000);
//    executor.initialize();
//
//    long start = System.currentTimeMillis();
//    new MyCallableTes3().process(executor);
//
//    long end = System.currentTimeMillis();
//    System.out.println("main线程关闭，进行线程的清理");
//    //清理线程池
//    executor.shutdown();
//  }
//
//  public void process(ThreadPoolTaskExecutor executor) throws ExecutionException, InterruptedException {
//
//    //创建一个异步任务
//    final List<Future<Integer>> futures = new ArrayList<>();
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//    futures.add(executor.submit(()-> new MyTask().doSomething()));
//
//    int count = 0;
//    for (Future<Integer> future : futures) {
//      //futureTask.get() 得到我们想要的结果
//      //该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
//      count += future.get();
//    }
//    System.out.println("线程池的任务全部完成:结果为:" + count);
//  }
//
//  private class MyTask {
//    int doSomething(){
//      Integer res = new Random().nextInt(100);
//      try {
//        Thread.sleep(1000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//      System.out.println("任务执行:获取到结果 :" + res);
//      return res;
//    }
//  }
//}
