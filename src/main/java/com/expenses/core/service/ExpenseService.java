package com.expenses.core.service;

import com.expenses.core.model.Expense;

import java.util.List;
import java.util.Map;

public interface ExpenseService {

    /**
     * adds the expense to the list of all one time expenses for the given date
     * @param expense the expense to be added to the db
     */
    Expense addExpense(Expense expense);

    int removeExpense(Expense expense);

    List<Expense> findAll();
    /**
     * Returns a list of all the spending categories and how much is spent in them monthly and on
     * a one-time basis. Includes all scheduled expenses that have occurred since being added
     * @return a list of all dated expenses
     */
    Map<String, Double> getCategoryTotals();

    Map<String, Double> getMonthlyTotals();
}
