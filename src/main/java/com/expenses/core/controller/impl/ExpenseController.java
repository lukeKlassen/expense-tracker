package com.expenses.core.controller.impl;

import com.expenses.core.dto.ExpenseDTO;
import com.expenses.core.dto.ExpenseReportDTO;
import com.expenses.core.dto.ScheduledPaymentDTO;
import com.expenses.core.dto.ShowExpensesResponseDTO;
import com.expenses.core.mapper.ExpenseMapper;
import com.expenses.core.model.Expense;
import com.expenses.core.service.ExpenseService;
import com.expenses.core.service.impl.ExpenseValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseValidationServiceImpl expenseValidationService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ExpenseMapper expenseMapper;


    @PutMapping(value = "/schedulePayment")
    @ResponseBody
    public void schedulePayment(@RequestParam(value = "payment") ScheduledPaymentDTO payment)
    {
        //TODO: implement scheduled payments
    }

    @PutMapping(value = "/addExpense")
    @ResponseBody
    public String addExpense(@RequestBody ExpenseDTO expenseDTO)
    {
        String validationErrors = expenseValidationService.validateExpenseDTO(expenseDTO);
        if(!validationErrors.equals(""))
        {
            return validationErrors;
        }
        else
        {
            Expense expense = expenseMapper.fromExpenseDTO(expenseDTO);
            return expenseService.addExpense(expense).toString();
        }
    }

    @RequestMapping(value = "/showExpenses")
    public ResponseEntity<List<ExpenseDTO>> showAllExpenses()
    {
        return ResponseEntity.ok().body(expenseService.findAll().stream().map((Expense expense) ->
                expenseMapper.fromExpense(expense)).collect(Collectors.toList()));
    }

    @DeleteMapping(value = "/removeExpense")
    public String removeExpense(@RequestBody ExpenseDTO expenseDTO){
        Expense expense = expenseMapper.fromExpenseDTO(expenseDTO);
        if(expenseService.removeExpense(expense) > 0)
            return "Expense successfully removed";
        else
            return "Error in removing expense - Expense could not be found";
    }

    @RequestMapping(value = "/getExpenseReport")
    public ResponseEntity<ExpenseReportDTO> getExpenseReport()
    {
        ExpenseReportDTO expenseReport = new ExpenseReportDTO();
        expenseReport.setCategoryTotals(expenseService.getCategoryTotals());
        expenseReport.setMonthlyTotals(expenseService.getMonthlyTotals());

        return ResponseEntity.ok().body(expenseReport);
    }

}
