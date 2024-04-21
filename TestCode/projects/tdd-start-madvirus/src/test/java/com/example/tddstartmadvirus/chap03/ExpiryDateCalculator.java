package com.example.tddstartmadvirus.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculate(LocalDate billingDate, int payAmount) {
        return billingDate.plusMonths(1L);
    }

}
