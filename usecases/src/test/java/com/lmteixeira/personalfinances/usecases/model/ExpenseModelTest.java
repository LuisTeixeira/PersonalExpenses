package com.lmteixeira.personalfinances.usecases.model;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.usecases.models.ExpenseModel;
import com.lmteixeira.personalfinances.usecases.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class ExpenseModelTest {

    @Test
    public void fromTransactionShouldCreateAnExpenseModelWithTheSameValueDescriptionAndTimestampAsTheTransaction() {
        Transaction transaction = new TransactionFactory().createTransaction(BigDecimal.TEN, "Test description", new Date().getTime());
        ExpenseModel expenseModel = ExpenseModel.fromTransaction(transaction);
        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(transaction.getValue(), expenseModel.getValue()));
        Assert.assertEquals(transaction.getDescriptionStringRepresentation(), expenseModel.getDescription());
        Assert.assertEquals(transaction.getTimestamp(), expenseModel.getTimestamp());
    }
}
