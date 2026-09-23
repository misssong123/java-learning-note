package com.crm.mystater.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogService {
    private static final Logger logger= LoggerFactory.getLogger(LogService.class);
    public LogService(){
        logger.info("----------------LogService init----------------");
    }

}
