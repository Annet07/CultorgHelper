package ru.itis.javalab.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NightSetupConfig {

    private final String queueName = "nightSetupQueue";

    @Autowired
    private TopicExchange topicExchange;

    @Bean
    public Queue nightSetupQueue(){
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding nightSetupBinding(){
        return BindingBuilder.bind(nightSetupQueue()).to(topicExchange).with("pdf.night.setup");
    }
}
