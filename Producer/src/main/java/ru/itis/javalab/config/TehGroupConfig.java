package ru.itis.javalab.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TehGroupConfig {

    private final String queueName = "tehGroupQueue";

    @Autowired
    private TopicExchange topicExchange;

    @Bean
    public Queue tehGroupQueue(){
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding tehGroupBinding(){
        return BindingBuilder.bind(tehGroupQueue()).to(topicExchange).with("pdf.teh.group");
    }
}
