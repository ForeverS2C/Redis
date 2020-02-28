package com.slx.boot.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 建立获取spring容器中bean的工具类，
 * 通过Spring Aware（容器感知）来获取到ApplicationContext，
 * 然后根据ApplicationContext获取容器中的Bean，
 * 因为RedisTemplate的bean不能用@Autowired注解注入
 */
@Component //注意添加该注解
public class ApplicationContextHolder implements ApplicationContextAware {

    //web程序一启动,就会把web程序的上下文赋值给applicationContext对象.
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("applicationContext正在初始化,application:"+applicationContext);
        ApplicationContextHolder.applicationContext = applicationContext; // NOSONAR
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if(applicationContext==null){
            System.out.println("applicationContext是空的");
        }else{
            System.out.println("applicationContext不是空的");
        }
//        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T) applicationContext.getBeansOfType(clazz);
    }

    /**
     * 清除applicationContext静态变量.
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
