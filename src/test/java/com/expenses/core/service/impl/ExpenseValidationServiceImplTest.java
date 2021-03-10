package com.expenses.core.service.impl;

import com.expenses.core.dto.ExpenseDTO;
import com.expenses.core.model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ExpenseValidationServiceImplTest {

    @InjectMocks
    private ExpenseValidationServiceImpl expenseValidationService;

    @Test
    public void unfilledExpenseInput(){
        ExpenseDTO e = new ExpenseDTO(null, 10.54, "no comment", "2021-03-09");
        assertEquals("All fields must be filled", expenseValidationService.validateExpenseDTO(e));

        e = new ExpenseDTO("groceries", 10.54, null, "2021-03-09");
        assertEquals("All fields must be filled", expenseValidationService.validateExpenseDTO(e));

        e = new ExpenseDTO("groceries", 10.54, "no comment", null);
        assertEquals("All fields must be filled", expenseValidationService.validateExpenseDTO(e));

        e = new ExpenseDTO(null, 10.54, null, null);
        assertEquals("All fields must be filled", expenseValidationService.validateExpenseDTO(e));
    }

    @Test
    public void negativeExpenseInput(){
        ExpenseDTO e = new ExpenseDTO("groceries", -10.54, "no comment", "2021-03-09");
        assertEquals("Expense amount must not be negative or zero", expenseValidationService.validateExpenseDTO(e));

        e = new ExpenseDTO("groceries", 0, "no comment", "2021-03-09");
        assertEquals("Expense amount must not be negative or zero", expenseValidationService.validateExpenseDTO(e));
    }

    @Test
    public void validExpenseInput(){
        ExpenseDTO e = new ExpenseDTO("groceries", 10.54, "no comment", "2021-03-09");
        assertEquals("", expenseValidationService.validateExpenseDTO(e));
    }
}
