package com.lmteixeira.personalfinances.expenses.factory;

import com.lmteixeira.personalfinances.expenses.TransactionAccount;
import com.lmteixeira.personalfinances.expenses.impl.TransactionAccountImpl;

public class TransactionAccountFactory {

    public TransactionAccount createExpenseAccount() {
        return new TransactionAccountImpl();
    }
}
