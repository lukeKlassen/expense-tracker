package com.expenses.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseReportDTO
{
    private List<SpendingCategoryDTO> categoryTotals;
    private List<MonthExpensesDTO> monthlyTotals;
}
