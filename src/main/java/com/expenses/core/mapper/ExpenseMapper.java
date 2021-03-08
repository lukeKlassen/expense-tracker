package com.expenses.core.mapper;

import com.expenses.core.dto.ExpenseDTO;
import com.expenses.core.model.Expense;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ExpenseMapper
{

    Expense fromExpenseDTO(ExpenseDTO expenseDTO);

    ExpenseDTO fromExpense(Expense expense);
}
