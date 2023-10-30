package com.wb.clients.fraud;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fraud")
public interface FraudClient {
    //this is actually an interface that will target the fraud controller so that any microserv that need to
    //talk to fraud can use this interface tadaaaaaa oh and don't forget to add the dependency on you client pom.xml
    @GetMapping(path="api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerID);
}
