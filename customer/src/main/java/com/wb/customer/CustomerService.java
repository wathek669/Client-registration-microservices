package com.wb.customer;

import com.wb.amqp.RabbitMQMessageProducer;
import com.wb.clients.fraud.FraudCheckResponse;
import com.wb.clients.fraud.FraudClient;
;
import com.wb.clients.notification.NotificationClient;
import com.wb.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    //private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        /*FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()); */
        //feign doing its work here
        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster ya zebiiiiiiiiiiiiiiiiiiii") ;
        }
        //customerRepository.save(customer);

        // async communication between notification and customer requests
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to watheks microservices...",
                        customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,"internal.exchange","internal.notification.routing-key"
        );
          }

}
