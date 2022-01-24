package ru.itis.javalab.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConcertApplicationConfig {

    private final String queueName = "concertApplicationQueue";

    @Autowired
    private TopicExchange topicExchange;

    @Bean
    public Queue concertApplicationQueue(){
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding concertApplicationBinding(){
        return BindingBuilder.bind(concertApplicationQueue()).to(topicExchange).with("pdf.concert.application");
    }

}
