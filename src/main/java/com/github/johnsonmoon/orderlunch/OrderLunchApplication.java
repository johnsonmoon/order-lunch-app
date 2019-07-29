package com.github.johnsonmoon.orderlunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableScheduling
@ServletComponentScan
@SpringBootApplication
public class OrderLunchApplication {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void main(String[] args) {
        startup(args);
    }

    static void startup(String... args) {
        String workHome = System.getenv("work.home");
        if (workHome == null || workHome.isEmpty()) {
            workHome = System.getProperty("work.home");
        }
        if (workHome == null || workHome.isEmpty()) {
            workHome = System.getProperty("user.dir");
            System.setProperty("work.home", workHome);
        }
        applicationContext = SpringApplication.run(OrderLunchApplication.class, args);
        ((ConfigurableApplicationContext) applicationContext).registerShutdownHook();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}
