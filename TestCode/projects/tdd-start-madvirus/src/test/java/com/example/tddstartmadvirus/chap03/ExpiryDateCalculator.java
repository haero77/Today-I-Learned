package com.example.tddstartmadvirus.chap03;

import java.time.LocalDate;
import java.util.Objects;

public class ExpiryDateCalculator {

    public LocalDate calculate(PayData payData) {
        final long addedMonth = 1L;

        if (Objects.nonNull(payData.getFirstBillingDate())) {
            final LocalDate candidateExpiryDate = payData.getBillingDate().plusMonths(addedMonth);

            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExpiryDate.getDayOfMonth()) {
                return candidateExpiryDate.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }

        return payData.getBillingDate().plusMonths(addedMonth);
    }

}
