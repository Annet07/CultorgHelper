package ru.itis.javalab;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args){
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(rabbitTemplate);
        container.setQueueNames("replyQueue");
        return container;
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
