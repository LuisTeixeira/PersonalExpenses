package com.lmteixeira.personalfinances.account.factory;

import com.lmteixeira.personalfinances.account.TransactionAccount;
import com.lmteixeira.personalfinances.account.impl.TransactionAccountImpl;

public class TransactionAccountFactory {

    public TransactionAccount createExpenseAccount() {
        return new TransactionAccountImpl();
    }
}
