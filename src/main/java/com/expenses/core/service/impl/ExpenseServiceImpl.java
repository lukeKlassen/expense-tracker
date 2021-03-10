package com.expenses.core.service.impl;

import com.expenses.core.dao.ExpenseDao;
import com.expenses.core.model.Expense;
import com.expenses.core.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public Expense addExpense(Expense expense) {
        return expenseDao.save(expense);
    }

    @Override
    @Transactional
    public int removeExpense(Expense expense){
        return expenseDao.deleteByFields(expense.getSpendingCategory(), expense.getAmount(), expense.getComment(),
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
    public Map<String, Double> getCategoryTotals()
    {
        List<Expense> expenses = findAll();
        Map<String, Double> categoryTotals = new HashMap<>();

        expenses.forEach((Expense e ) -> {
            String category = e.getSpendingCategory().toLowerCase();
            if(!categoryTotals.containsKey(category))
            {
                categoryTotals.put(category, e.getAmount());
            }
            else
            {
                categoryTotals.replace(category, categoryTotals.get(category) + e.getAmount());
            }
        });

        return categoryTotals;
    }

    @Override
    public Map<String, Double> getMonthlyTotals()
    {
        List<Expense> expenses = findAll();
        Map<String, Double> monthlyTotals = new HashMap<>();

        expenses.forEach((Expense e ) -> {
            if(!e.getDate().equals("")) {
                String month = e.getDate().substring(0, 7);
                if (!monthlyTotals.containsKey(month)) {
                    monthlyTotals.put(month, e.getAmount());
                } else {
                    monthlyTotals.replace(month, monthlyTotals.get(month) + e.getAmount());
                }
            }
        });

        return monthlyTotals;
    }
}
