package com.expenses.core.controller.impl;

import com.expenses.core.dto.ExpenseDTO;
import com.expenses.core.dto.ScheduledPaymentDTO;
import com.expenses.core.dto.ShowExpensesResponseDTO;
import com.expenses.core.service.ExpenseService;
import com.expenses.core.service.impl.ExpenseValidationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class ExpenseController {

    private ExpenseValidationServiceImpl expenseValidationService;
    private ExpenseService expenseService;

    @PutMapping(value = "/schedulePayment")
    @ResponseBody
    public void schedulePayment(@RequestParam(value = "payment") ScheduledPaymentDTO payment)
    {
        //TODO: implement scheduled payments
    }

    @PutMapping(value = "/addExpense")
    @ResponseBody
    public String addExpense(@RequestBody ExpenseDTO expense)
    {
        String validationErrors = expenseValidationService.validateExpenseDTO(expense);
        if(!validationErrors.equals(""))
        {
            return validationErrors;
        }
        else
        {
            
        }
    }

    @RequestMapping(value = "/showExpenses")
    public ResponseEntity<ShowExpensesResponseDTO> showAllExpenses()
    {
        return null;
    }

    @DeleteMapping(value = "/removeExpense")
    public void removeExpense(@RequestParam(value = "uploadId") String uploadId){

    }

}
