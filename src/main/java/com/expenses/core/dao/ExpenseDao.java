package com.expenses.core.dao;

import com.expenses.core.model.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseDao extends CrudRepository<Expense, String> { }
