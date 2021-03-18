package com.expenses.core.service.impl;

import com.expenses.core.dao.ExpenseDao;
import com.expenses.core.dto.MonthExpensesDTO;
import com.expenses.core.dto.SpendingCategoryDTO;
import com.expenses.core.model.Expense;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class ExpenseServiceImplTest {

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Mock
    private ExpenseDao expenseDao;

    @Test
    public void testAddExpenseSuccess()
    {
        Expense e = new Expense("id", "groceries", 10.54, "no comment", "2021-03-09");
        Mockito.when(expenseDao.save(e)).thenReturn(e);
        assertEquals(expenseService.addExpense(e), e);
    }

    @Test
    public void testAddExpenseFailure()
    {
        Expense e = new Expense("id", "groceries", 10.54, "no comment", "2021-03-09");
        Mockito.when(expenseDao.save(e)).thenReturn(null);
        assertNull(expenseService.addExpense(e));
    }

    @Test
    public void testRemoveExpenseFound()
    {
        Expense e = new Expense("id", "groceries", 10.54, "no comment", "2021-03-09");
        Mockito.when(expenseDao.deleteByFields(e.getSpendingCategory(), e.getAmount(), e.getComment(), e.getDate())).thenReturn(1);
        assertEquals(expenseService.removeExpense(e), 1);
    }

    @Test
    public void testRemoveExpenseNotFound()
    {
        Expense e = new Expense("id", "groceries", 10.54, "no comment", "2021-03-09");
        Mockito.when(expenseDao.deleteByFields(e.getSpendingCategory(), e.getAmount(), e.getComment(), e.getDate())).thenReturn(0);
        assertEquals(expenseService.removeExpense(e), 0);
    }

    @Test
    public void testFindAll()
    {
        List<Expense> expenseList = new ArrayList<>();
        Expense e1 = new Expense("id", "groceries", 10.54, "no comment", "2021-03-09");
        Expense e2 = new Expense("12345", "rent", 10000000.54, "some really long comment " +
                "that will never actually be entered into such a field for any good reason", "1998-12-31");
        Expense e3 = new Expense("", "", 0, "", "");
        Expense e4 = new Expense("0912", "groceries", -10.54, "no comment", "2021-03-09");
        expenseList.add(e1);
        expenseList.add(e2);
        expenseList.add(e3);
        expenseList.add(e4);
        Mockito.when(expenseDao.findAll()).thenReturn(expenseList);
        assertEquals(expenseService.findAll(), expenseList);
    }

    @Test
    public void testFindAllReturnsNone()
    {
        Mockito.when(expenseDao.findAll()).thenReturn(new ArrayList<Expense>());
        assertEquals(expenseService.findAll().size(), 0);
    }

    @Test
    public void getCategoryTotalsNull()
    {
        Mockito.when(expenseDao.findAll()).thenReturn(new ArrayList<Expense>());
        assertEquals(expenseService.getCategoryTotals().size(), 0);
    }

    @Test
    public void getCategoryTotalsFilled()
    {
        List<Expense> expenseList = new ArrayList<>();
        Expense e1 = new Expense("id", "groceries", 10.54, "no comment", "2021-03-09");
        Expense e2 = new Expense("12345", "rent", 10000000.54, "some really long comment " +
                "that will never actually be entered into such a field for any good reason", "1998-12-31");
        Expense e3 = new Expense("", "", 0, "", "");
        Expense e4 = new Expense("0912", "groceries", -10.54, "no comment", "2021-03-09");
        expenseList.add(e1);
        expenseList.add(e2);
        expenseList.add(e3);
        expenseList.add(e4);
        Mockito.when(expenseDao.findAll()).thenReturn(expenseList);

        List<SpendingCategoryDTO> returnedMonthlyTotals = expenseService.getCategoryTotals();
        Assert.assertTrue(returnedMonthlyTotals.indexOf(new SpendingCategoryDTO("groceries", 0.00)) != -1);

        Assert.assertTrue(returnedMonthlyTotals.indexOf(new SpendingCategoryDTO("rent", 1000000.54)) != -1);
    }

    @Test
    public void getMonthlyTotalsNull()
    {
        Mockito.when(expenseDao.findAll()).thenReturn(new ArrayList<Expense>());
        assertEquals(expenseService.getMonthlyTotals().size(), 0);
    }

    @Test
    public void getMonthlyTotalsFilled()
    {
        List<Expense> expenseList = new ArrayList<>();
        Expense e1 = new Expense("id", "groceries", 10.54, "no comment", "2021-03-09");
        Expense e2 = new Expense("12345", "rent", 10000000.54, "some really long comment " +
                "that will never actually be entered into such a field for any good reason", "1998-12-31");
        Expense e3 = new Expense("", "", 0, "", "");
        Expense e4 = new Expense("0912", "groceries", -10.54, "no comment", "2021-03-09");
        expenseList.add(e1);
        expenseList.add(e2);
        expenseList.add(e3);
        expenseList.add(e4);
        Mockito.when(expenseDao.findAll()).thenReturn(expenseList);
        List<MonthExpensesDTO> returnedMonthlyTotals = expenseService.getMonthlyTotals();
        Assert.assertTrue(returnedMonthlyTotals.indexOf(new MonthExpensesDTO("2021-03", 0.00)) != -1);

        Assert.assertTrue(returnedMonthlyTotals.indexOf(new MonthExpensesDTO("1998-12", 1000000.54)) != -1);
    }
}
