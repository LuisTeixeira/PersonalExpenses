package com.lmteixeira.personalfinances.transaction.factory;

import com.lmteixeira.personalfinances.transaction.Transaction;
import com.lmteixeira.personalfinances.transaction.impl.TransactionImpl;

import java.math.BigDecimal;

public class TransactionFactory {

    public Transaction createTransaction(BigDecimal expenseValue, String description, Long timestamp) {
        return new TransactionImpl( expenseValue, description, timestamp );
    }

}
