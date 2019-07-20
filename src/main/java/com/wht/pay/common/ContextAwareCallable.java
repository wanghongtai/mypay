//package com.wht.pay.common;
//
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import java.util.concurrent.Callable;
//
///**
// * Created by linkli on 1/9/2018.
// */
//public class ContextAwareCallable<T> implements Callable<T> {
//  private final Callable<T> callable;
//  private final RequestAttributes context;
//
//  public ContextAwareCallable(Callable<T> callable, RequestAttributes context) {
//    this.callable = callable;
//    this.context = context;
//  }
//
//  @Override
//  public T call() throws Exception {
//    if (context != null) {
//      RequestContextHolder.setRequestAttributes(context);
//    }
//
//    try {
//      return callable.call();
//    } finally {
//      RequestContextHolder.resetRequestAttributes();
//    }
//  }
//}