package com.expenses.core.controller.impl;

import com.expenses.core.dto.ExpenseDTO;
import com.expenses.core.dto.ExpenseReportDTO;
import com.expenses.core.dto.MonthExpensesDTO;
import com.expenses.core.dto.ScheduledPaymentDTO;
import com.expenses.core.dto.ShowExpensesResponseDTO;
import com.expenses.core.dto.SpendingCategoryDTO;
import com.expenses.core.mapper.ExpenseMapper;
import com.expenses.core.model.Expense;
import com.expenses.core.service.ExpenseService;
import com.expenses.core.service.impl.ExpenseValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin(origins = "http://localhost:63342")
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

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/showExpenses")
    public ResponseEntity<List<ExpenseDTO>> showAllExpenses()
    {
        List<ExpenseDTO> expenses = expenseService.findAll().stream().map((Expense expense) ->
                expenseMapper.fromExpense(expense)).collect(Collectors.toList());
        expenses.sort((e1, e2) -> compareDates(e1.getDate(), e2.getDate()));
        return ResponseEntity.ok().body(expenses);
    }

    @DeleteMapping(value = "/removeExpense")
    public String removeExpense(@RequestBody ExpenseDTO expenseDTO){
        Expense expense = expenseMapper.fromExpenseDTO(expenseDTO);
        if(expenseService.removeExpense(expense) > 0)
            return "Expense successfully removed";
        else
            return "Error in removing expense - Expense could not be found";
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/getExpenseReport")
    public ResponseEntity<ExpenseReportDTO> getExpenseReport()
    {
        ExpenseReportDTO expenseReport = new ExpenseReportDTO();
        List<SpendingCategoryDTO> sortedSpendingCategories = expenseService.getCategoryTotals();
        sortedSpendingCategories.sort((e1, e2) -> e1.getCategoryName().compareTo(e2.getCategoryName()));
        expenseReport.setCategoryTotals(sortedSpendingCategories);

        List<MonthExpensesDTO> sortedMonthlyExpenses = expenseService.getMonthlyTotals();
        sortedMonthlyExpenses.sort((e1,e2) -> compareDates(e1.getDate(), e2.getDate()));
        expenseReport.setMonthlyTotals(sortedMonthlyExpenses);

        return ResponseEntity.ok().body(expenseReport);
    }

    private int compareDates(String date1, String date2)
    {
        int year1 = Integer.parseInt(date1.substring(0,4))*100;
        int year2 = Integer.parseInt(date2.substring(0,4))*100;
        int month1 = Integer.parseInt(date1.substring(5,7));
        int month2 = Integer.parseInt(date2.substring(5,7));

        return year2+month2 - year1+month1;
    }

}
