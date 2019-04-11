package com.zmyjn.common.aspect;


import com.alibaba.druid.support.json.JSONUtils;
import com.zmyjn.common.annotation.SysLog;
import com.zmyjn.core.util.HttpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class SysLogAspect {


    @Pointcut("@annotation(com.zmyjn.common.annotation.SysLog)")
    private void cut() { }


    /**
     * 定制一个环绕通知
     * @param joinPoint
     */
    @Around("cut()")
    public void advice(ProceedingJoinPoint joinPoint){
        System.out.println("环绕通知之开始");
        try {
            joinPoint.proceed();

            //1.获取到所有的参数值的数组
            Object[] args = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            //2.获取到方法的所有参数名称的字符串数组
            String[] parameterNames = methodSignature.getParameterNames();
            Method method = methodSignature.getMethod();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            System.out.println("方法："+ className + "." + methodName);
            System.out.println("args："+ args[0] );
            System.out.println("json参数:"+JSONUtils.toJSONString(args[0]));

            System.out.println("---------------参数列表开始-------------------------");
            for (int i =0 ,len=parameterNames.length;i < len ;i++){
                System.out.println("参数名："+ parameterNames[i] + " = " +args[i]);
            }
            System.out.println("---------------参数列表结束-------------------------");
//            RedisHandel redis=(RedisHandel)method.getAnnotation(RedisHandel.class);
//            System.out.println("自定义注解 key:" + redis.key());
//            System.out.println("自定义注解 keyField:" + redis.keyField());
//            Class cla=method.getClass();
//            if(cla.isAnnotationPresent(RedisHandel.class)){
//                RedisHandel redisHandel =(RedisHandel)cla.getAnnotation(RedisHandel.class);
//                String key=redisHandel.key();
//                String keyField=redisHandel.keyField();
//                System.out.println("key = " + key);
//                System.out.println("keyField = " + keyField);
//            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("环绕通知之结束");
    }

    //当想获得注解里面的属性，可以直接注入改注解
    @Before("cut()&&@annotation(sysLog)")
    public void record(JoinPoint joinPoint, SysLog sysLog) {
        System.out.println("ssss:"+sysLog.value());
        System.out.println("ip:"+HttpUtils.getIdAddr());
        System.out.println("ip2:"+HttpUtils.getIdAddr2());


    }

//    @After("recordLog()")
//    public void after() {
//        this.printLog("已经记录下操作日志@After 方法执行后");
//    }

}
