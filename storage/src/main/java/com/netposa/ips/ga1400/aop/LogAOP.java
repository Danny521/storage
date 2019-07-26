//package com.netposa.dgep.aop;
//
//import com.google.common.base.Stopwatch;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//@Aspect
//@Component
//@Slf4j
//public class LogAOP {
//
//    ThreadLocal<String> controllerThreadLocal = new ThreadLocal<>();
//    ThreadLocal<Stopwatch> stopwatchThreadLocal = new ThreadLocal<>();
//
//    // 定义controller切点
//    @Pointcut("execution(public * com.netposa.dgep.controller..*.*(..))")
//    public void controllerLog() {
//
//    }
//
//    // 定义service切点
//    @Pointcut("execution(public * com.netposa.dgep.service..*.*(..))")
//    public void serviceLog() {
//
//    }
//
//    /**
//     * 记录controller调用参数
//     * @param joinPoint
//     */
//    @Before("controllerLog()")
//    public void beforeMethod(JoinPoint joinPoint){
//        String uuid  = UUID.randomUUID().toString();
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        controllerThreadLocal.set(uuid);
//        stopwatchThreadLocal.set(stopwatch);
//
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpServletRequest httpServletRequest = (HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//
//
//        log.info("---> 操作{} 远程IP {} 调用方法 {} 调用路径 {} 参数 {}",
//                uuid,
//                httpServletRequest.getRemoteHost(),
//                httpServletRequest.getMethod(),
//                httpServletRequest.getContextPath(),
//                Arrays.toString(joinPoint.getArgs())
//        );
//    }
//
//    /**
//     * 记录controller 返回参数
//     * @param result
//     */
//    @AfterReturning(returning = "result",pointcut = "controllerLog()")
//    public void afterReturning(Object result){
//        String uuid  = controllerThreadLocal.get();
//        Stopwatch stopwatch = stopwatchThreadLocal.get();
//        String resultStr = "";
//        if(Objects.nonNull(result)){
//            if(result.toString().length() > 800){
//                resultStr = result.toString().substring(0,800);
//            }else{
//                resultStr = result.toString();
//            }
//        }
//
//        log.info("操作{} 返回 {} 耗时 {} ms",
//                uuid,
//                resultStr,
//                stopwatch.elapsed(TimeUnit.MILLISECONDS)
//        );
//    }
//
//    /**
//     * 记录服务接收参数
//     * @param joinPoint
//     */
//    @Before("serviceLog()")
//    public void beforeServiceMethod(JoinPoint joinPoint){
//        String uuid  = controllerThreadLocal.get();
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        stopwatchThreadLocal.set(stopwatch);
//
//        log.info("操作{} 方法 {} 参数 {}",
//                uuid,
//                joinPoint.getSignature(),
//                Arrays.toString(joinPoint.getArgs())
//        );
//    }
//
//    /**
//     * 记录服务返回参数
//     * @param result
//     */
//    @AfterReturning(returning = "result",pointcut = "serviceLog()")
//    public void afterServiceReturning(Object result){
//        String uuid  = controllerThreadLocal.get();
//        Stopwatch stopwatch = stopwatchThreadLocal.get();
//        String resultStr = "";
//        if(Objects.nonNull(result)){
//            if(result.toString().length() > 800){
//                resultStr = result.toString().substring(0,800);
//            }else{
//                resultStr = result.toString();
//            }
//        }
//
//        log.info("操作{} 返回 {} 耗时 {} ms",
//                uuid,
//                resultStr,
//                stopwatch.elapsed(TimeUnit.MILLISECONDS)
//        );
//    }
//}
