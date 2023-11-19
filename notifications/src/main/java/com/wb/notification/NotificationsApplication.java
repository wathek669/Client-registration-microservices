package com.wb.notification;

import com.wb.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.wb.notification","com.wb.amqp"} )
@EnableFeignClients
@EnableEurekaClient
public class NotificationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationsApplication.class, args);
    }
       /* @Bean
        CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,NotificationConfig config){
            return args->{producer.publish("foo",
                    config.getInternalExchange(),
                    config.getInternalNotificationRoutingKey());};

    }*/
}
