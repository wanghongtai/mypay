//package com.wht.pay.utils;
//
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
//public class MyCallableTest {
//
//
//  public static void main(String[] args) throws ExecutionException, InterruptedException {
//    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//    executor.setMaxPoolSize(20);
//    executor.setCorePoolSize(10);
//    executor.setQueueCapacity(5000);
//    executor.initialize();
//
//    long start = System.currentTimeMillis();
//    //类似与run方法的实现, Callable是一个接口，在call中手写逻辑代码
//    List<Callable> callables = new ArrayList<>();
//    for (int i = 0; i < 10; i++) {
//      Callable<Integer> callable = new Callable<Integer>() {
//        @Override
//        public Integer call() throws Exception {
//          Integer res = new Random().nextInt(100);
//          Thread.sleep(1000);
//          System.out.println("任务执行:获取到结果 :" + res);
//          return res;
//        }
//      };
//      callables.add(callable);
//    }
//
//
//    //创建一个异步任务
//    final List<Future<Integer>> futures = new ArrayList<>();
//    for (final Callable callable : callables) {
//      futures.add(executor.submit(callable));
//    }
//
//    int count = 0;
//    for (Future<Integer> future : futures) {
//      //futureTask.get() 得到我们想要的结果
//      //该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
//      count += future.get();
//    }
//    long end = System.currentTimeMillis();
//    System.out.println("线程池的任务全部完成:结果为:" + count + "，main线程关闭，进行线程的清理");
//    //清理线程池
//    executor.shutdown();
//  }
//
//}
