package com.spring.boot;

import com.spring.boot.activemq.Msg;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableCaching
@EnableScheduling
public class BootApplication implements CommandLineRunner {

    @Autowired
    JmsTemplate jmsTemplate; //2

    @Autowired
    RabbitTemplate rabbitTemplate; //1

    @Bean //2
    public Queue wiselyQueue(){
        return new Queue("my-queue");
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.send("my-destination", new Msg());//3
        rabbitTemplate.convertAndSend("my-queue", "来自RabbitMQ的问候"); //3
    }
}
