package com.crm.mystater.configuration;

import com.crm.mystater.event.LogEvent;
import com.crm.mystater.properties.MyProperties;
import com.crm.mystater.service.LogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(LogService.class)
public class MyStarterAutoConfiguration {
    @Resource
    private ApplicationContext applicationContext;
    @Bean
    public LogService logService() {
        applicationContext.publishEvent(new LogEvent("logService init"));
        return new LogService();
    }
    @Bean
    public MyProperties myProperties() {
        applicationContext.publishEvent(new LogEvent("logService init"));
        return new MyProperties();
    }
}
