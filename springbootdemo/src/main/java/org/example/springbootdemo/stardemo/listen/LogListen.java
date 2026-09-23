package org.example.springbootdemo.stardemo.listen;

import com.crm.mystater.event.LogEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听器处理逻辑
 * 1.正常继承ApplicationListener，默认使用同步处理监听事件的逻辑
 * 2.编写applicationEventMulticaster设置线程组，实现自己线程池监听事件的逻辑
 * 3.应用开启@EnableAsync，具体的方法或类使用@Async实现异步监听事件的逻辑
 */
@Component
public class LogListen implements ApplicationListener<LogEvent> {
    @Override
    @Async
    public void onApplicationEvent(LogEvent event) {
        System.out.println("收到日志事件，日志内容：" + event.getSource());
    }
}
