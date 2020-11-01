package com.lmteixeira.personalfinances.usecases.model;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.usecases.models.TransactionModel;
import com.lmteixeira.personalfinances.usecases.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionModelTest {

    @Test
    public void fromTransactionShouldCreateAnExpenseModelWithTheSameValueDescriptionAndTimestampAsTheTransaction() {
        Transaction transaction = new TransactionFactory().createTransaction(BigDecimal.TEN, "Test description", new Date().getTime());
        TransactionModel transactionModel = TransactionModel.fromTransaction(transaction);
        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(transaction.getValue(), transactionModel.getValue()));
        Assert.assertEquals(transaction.getDescriptionStringRepresentation(), transactionModel.getDescription());
        Assert.assertEquals(transaction.getTimestamp(), transactionModel.getTimestamp());
    }
}
