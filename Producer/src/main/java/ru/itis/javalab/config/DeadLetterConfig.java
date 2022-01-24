package ru.itis.javalab.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadLetterConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic_exchange");
    }

    @Bean
    public Queue deadLetterQueue(){
        return QueueBuilder.durable("deadLetterQueue").build();
    }

    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange("deadLetterExchange");
    }

    @Bean
    public Binding DlqBinding(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("deadLetter");
    }

}
