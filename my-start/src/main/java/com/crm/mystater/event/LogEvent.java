package com.crm.mystater.event;

import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {

    public LogEvent(String message) {
        super(message);
    }
}
