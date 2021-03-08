package com.expenses.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpenseDTO {

    private String spendingCategory;
    private double amount;
    private String comment;
    private String date;
}
