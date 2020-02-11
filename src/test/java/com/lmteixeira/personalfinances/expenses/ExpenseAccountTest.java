package com.lmteixeira.personalfinances.expenses;

import org.junit.Assert;
import org.junit.Test;

public class ExpenseAccountTest {

    @Test
    public void newAccountTotalIsZero() {
        ExpenseAccount expenseAccount = new ExpenseAccount();
        Double total = expenseAccount.getTotal();
        Assert.assertEquals(0.0d, total, 0.0d);
    }

}
