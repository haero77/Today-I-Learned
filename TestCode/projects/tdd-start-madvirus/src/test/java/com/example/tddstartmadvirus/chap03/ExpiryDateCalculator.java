package com.example.tddstartmadvirus.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculate(PayData payData) {
        return payData.getBillingDate().plusMonths(1L);
    }

}
