//package com.wht.pay.common;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.Future;
//
///**
// * Created by linkli on 1/9/2018.
// */
//@Component
//public class ContextAwareThreadExecutor extends ThreadPoolTaskExecutor {
//
//  @Value("${ContextAwareThreadExecutor.corePoolSize}")
//  private Integer corePoolSize;
//
//  @Value("${ContextAwareThreadExecutor.maxPoolSize}")
//  private Integer maxPoolSize;
//
//  @Value("${ContextAwareThreadExecutor.queueCapacity}")
//  private Integer queueCapacity;
//
//  /**
//   * post construct.
//   */
//  @PostConstruct
//  public void initExecutor() {
//    this.setCorePoolSize(corePoolSize);
//    this.setMaxPoolSize(maxPoolSize);
//    this.setQueueCapacity(queueCapacity);
//  }
//
//  @Override
//  public <T> Future<T> submit(Callable<T> callable) {
//    return super.submit(
//        new ContextAwareCallable(callable, RequestContextHolder.currentRequestAttributes()));
//  }
//
//  public <T> void parallelCallAndWait(Callable<T>... callables) throws
//          Exception {
//    parallelCall(false, callables);
//  }
//
//  public <T> void parallelCallAndForget(Callable<T>... callables) throws
//          Exception {
//    parallelCall(true, callables);
//  }
//
//  private <T> void parallelCall(boolean fireAndForget, Callable<T>... callables) throws
//          Exception {
//    final List<Future<T>> futures = new ArrayList<>();
//    for (final Callable callable : callables) {
//      futures.add(submit(callable));
//    }
//
//    if (!fireAndForget) {
//      for (final Future future : futures) {
//        future.get();
//      }
//    }
//  }
//}