package com.expenses.core.service.impl;

import com.expenses.core.dto.ExpenseDTO;
import com.expenses.core.service.ExpenseValidationService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseValidationServiceImpl implements ExpenseValidationService {

    @Override
    public String validateExpenseDTO(ExpenseDTO expense)
    {
        if(nullFields(expense))
        {
            return "All fields must be filled";
        }
        else if(expense.getAmount() <= 0)
        {
            return "Expense amount must not be negative or zero";
        }
        return "";
    }

    private boolean nullFields(ExpenseDTO expense)
    {
        if(expense.getComment() == null || expense.getDate() == null ||
                expense.getSpendingCategory() == null)
        {
            return true;
        }
        return false;
    }

}
