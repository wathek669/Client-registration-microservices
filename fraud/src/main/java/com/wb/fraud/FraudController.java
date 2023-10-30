package com.wb.fraud;

import com.wb.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudCheckService fraudCheckService;

    //public FraudController(FraudCheckService fraudCheckService) {
      //  this.fraudCheckService = fraudCheckService;
    //} we use @AllArgsConstructor instead

    @GetMapping(path="{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerID){
       boolean isfraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
       log.info("fraud check request for customer {}",customerID);
       return new FraudCheckResponse(isfraudulentCustomer);
    }
}
