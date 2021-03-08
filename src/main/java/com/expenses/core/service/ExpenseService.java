package com.expenses.core.service;

import com.expenses.core.model.Expense;

import java.util.List;

public interface ExpenseService {

    /**
     * adds the expense to the list of all one time expenses for the given date
     * @param expense the expense to be added to the db
     */
    void addExpense(Expense expense);

    /**
     * Returns a list of all the spending categories and how much is spent in them monthly and on
     * a one-time basis. Includes all scheduled expenses that have occurred since being added
     * @return a list of all dated expenses
     */
    List<Expense> showExpenses();
}
