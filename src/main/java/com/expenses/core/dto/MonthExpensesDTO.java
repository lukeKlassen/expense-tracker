package com.expenses.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthExpensesDTO {

    private String date;
    private double amount;
}
