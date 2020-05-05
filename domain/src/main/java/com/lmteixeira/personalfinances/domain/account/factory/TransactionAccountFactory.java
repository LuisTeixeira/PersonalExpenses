package com.lmteixeira.personalfinances.domain.account.factory;

import com.lmteixeira.personalfinances.domain.account.TransactionAccount;
import com.lmteixeira.personalfinances.domain.account.impl.TransactionAccountImpl;

public class TransactionAccountFactory {

    public TransactionAccount createExpenseAccount() {
        return new TransactionAccountImpl();
    }
}
