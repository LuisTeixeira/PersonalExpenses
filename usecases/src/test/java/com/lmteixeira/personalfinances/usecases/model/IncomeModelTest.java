package com.lmteixeira.personalfinances.usecases.model;

import com.lmteixeira.personalfinances.domain.transaction.Transaction;
import com.lmteixeira.personalfinances.domain.transaction.factory.TransactionFactory;
import com.lmteixeira.personalfinances.usecases.models.IncomeModel;
import com.lmteixeira.personalfinances.usecases.utilities.BigDecimalsUtilities;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class IncomeModelTest {

    @Test
    public void fromTransactionShouldCreateAnIncomeModelWithTheSameValueDescriptionAndTimestampAsTheTransaction() {
        Transaction transaction = new TransactionFactory().createTransaction(BigDecimal.TEN, "Test description", new Date().getTime());
        IncomeModel incomeModel = IncomeModel.fromTransaction(transaction);
        Assert.assertTrue(BigDecimalsUtilities.compareBigDecimals(transaction.getValue(), incomeModel.getValue()));
        Assert.assertEquals(transaction.getDescriptionStringRepresentation(), incomeModel.getDescription());
        Assert.assertEquals(transaction.getTimestamp(), incomeModel.getTimestamp());
    }

}
