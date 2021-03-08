package com.expenses.core.dto;

import com.expenses.core.model.Expense;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ShowExpensesResponseDTO {

    @JsonProperty("expenseList")
    private List<Expense> expenseList;
}
