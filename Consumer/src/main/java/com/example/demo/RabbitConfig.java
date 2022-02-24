package com.example.demo;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    static final String MESS_QUEUE_CAR = "car";
    static final String MESS_QUEUE_STUDENT = "student";
    static final String MESS_QUEUE_WORKER = "worker";

    static final String MESS_QUEUE = "message_queue";
    static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String MESSAGE_TOPIC_KEY = "message_topic_key";

    @Bean
    public Queue queue_common() {
        return new Queue(MESS_QUEUE);
    }

    @Bean
    public Queue queue_student() {
        return new Queue(MESS_QUEUE_STUDENT);
    }

    @Bean
    public Queue queue_worker() {
        return new Queue(MESS_QUEUE_WORKER);
    }

    @Bean
    public Queue queue_car() {
        return new Queue(MESS_QUEUE_CAR);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding binding_c(@Qualifier("queue_common") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with("common");
    }


    @Bean
    public Binding binding_s(@Qualifier("queue_student") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with("student");
    }


    @Bean
    public Binding binding_w(@Qualifier("queue_worker") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with("worker");
    }


    @Bean
    public Binding binding_car(@Qualifier("queue_car") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with("car");
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate template(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(messageConverter());
        return template;
    }


}
