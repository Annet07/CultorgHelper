package ru.itis.javalab.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcertCostumeConfig {
    private final String queueName = "concertCostumeQueue";

    @Autowired
    private TopicExchange topicExchange;

    @Bean
    public Queue concertCostumeQueue(){
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding concertCostumeBinding(){
        return BindingBuilder.bind(concertCostumeQueue()).to(topicExchange).with("pdf.concert.costume");
    }
}
