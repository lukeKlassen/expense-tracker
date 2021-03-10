package com.expenses.core.dao;

import com.expenses.core.model.Expense;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExpenseDao extends CrudRepository<Expense, String>
{
    @Modifying
    @Query("DELETE FROM Expense e WHERE (e.spendingCategory=:spendingCategory AND e.amount=:amount AND e.comment=:comment AND e.date=:date)")
    int deleteByFields(@Param("spendingCategory") String spendingCategory, @Param("amount") double amount,
                                 @Param("comment") String comment, @Param("date") String date);
}
