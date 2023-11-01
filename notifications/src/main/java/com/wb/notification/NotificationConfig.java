package com.wb.notification;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Configuration
public class NotificationConfig {
    // this config is used to manage the rmq application.yml props
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange ;
    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue ;
    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey ;
    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }
    @Bean
    public Queue notificationQueue(){
        return new Queue(this.notificationQueue);
    }
    @Bean
    public Binding internalToNotificationBinding (){
        return BindingBuilder.bind(notificationQueue())
                .to(internalTopicExchange())
                .with(internalNotificationRoutingKey);
    }



    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationQueue() {
        return notificationQueue;
    }

    public String getInternalNotificationRoutingKey() {
        return internalNotificationRoutingKey;
    }
}
