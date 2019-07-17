package com.client.demo.spring.config;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 *
 *
 */
@Component
public class StartupApplicationListener {

    public static boolean isStart = true;

    @EventListener
    public void appReady(ContextRefreshedEvent event) {
        if(isStart){
            //    System.out.println("test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            isStart = false;
        }
    }

}
