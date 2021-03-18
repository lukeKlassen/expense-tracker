package com.expenses.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpendingCategoryDTO {

    private String categoryName;
    private double amount;
}
