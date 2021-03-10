package com.expenses.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseReportDTO
{
    private Map<String, Double> categoryTotals;
    private Map<String, Double> monthlyTotals;
}
