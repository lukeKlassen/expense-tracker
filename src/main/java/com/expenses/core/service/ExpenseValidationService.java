package com.expenses.core.service;

import com.expenses.core.dto.ExpenseDTO;

public interface ExpenseValidationService {

    String validateExpenseDTO(ExpenseDTO expense);
}
