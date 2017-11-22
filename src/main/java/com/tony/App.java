package com.tony;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        new ClassPathXmlApplicationContext("classpath:rpc-invoke-config.xml");
    }
}
