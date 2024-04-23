package com.example.tddstartmadvirus.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculate(PayData payData) {
        LocalDate billingDate = payData.getBillingDate();
        return billingDate.plusMonths(1L);
    }

}
