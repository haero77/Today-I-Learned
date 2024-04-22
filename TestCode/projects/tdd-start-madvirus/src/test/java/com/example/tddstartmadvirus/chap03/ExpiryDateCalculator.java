package com.example.tddstartmadvirus.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculate(LocalDate billingDate, int payAmount) {
        PayData payData = PayData.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();
        return calculate(payData);
    }

    public LocalDate calculate(PayData payData) {
        return payData.getBillingDate().plusMonths(1L);
    }

}
