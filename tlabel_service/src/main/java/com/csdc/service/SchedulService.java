package com.csdc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;


@Slf4j
@Service
public class SchedulService {

    // @Scheduled(cron = "*/5 * * * * ?")
    public void print() {


        log.info("hello" + new Date());
    }
}
