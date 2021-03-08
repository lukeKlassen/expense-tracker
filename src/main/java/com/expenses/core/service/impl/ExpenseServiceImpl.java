package com.expenses.core.service.impl;

import com.expenses.core.dao.ExpenseDao;
import com.expenses.core.model.Expense;
import com.expenses.core.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public void addExpense(Expense expense) {
        expenseDao.save(expense);
    }

    @Override
    @Transactional
    public void removeExpense(Expense expense){
        expenseDao.deleteByFields(expense.getSpendingCategory(), expense.getAmount(), expense.getComment(),
                expense.getDate());
    }

    @Override
    public List<Expense> findAll()
    {
        List<Expense> expenses = new ArrayList<>();
        expenseDao.findAll().forEach(expenses::add);
        return expenses;
    }
    @Override
    public List<Expense> showExpenses() {
        return null;
    }
}
