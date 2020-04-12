package com.lmteixeira.personalfinances.transaction;

import java.math.BigDecimal;

public class TransactionFactory {

    public Transaction createTransaction(BigDecimal expenseValue, String description, Long timestamp) {
        return new Transaction( expenseValue, description, timestamp );
    }

}
