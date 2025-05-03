package org.example.finance;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
    private static final double FINE_PER_DAY = 5.0;

    public double calculateFine(LocalDate dueDate) {
        LocalDate currentDate = LocalDate.now();
        if(currentDate.isAfter(dueDate)) {
            return FINE_PER_DAY * ChronoUnit.DAYS.between(dueDate, currentDate);
        }
        return 0.0;
    }
}
