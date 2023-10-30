package com.wb.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                        .customId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build());
        return false ;
    }
}
