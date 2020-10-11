package com.lmteixeira.personalfinances.hazelcastrepo.model;

import com.lmteixeira.personalfinances.domain.account.TransactionAccount;
import com.lmteixeira.personalfinances.domain.transaction.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HazelcastTransactionAccount implements Serializable {

    private static final long serialVerisionUID = 1L;

    private List<HazelcastTransaction> transactions;

    public HazelcastTransactionAccount() {
        transactions = new ArrayList<>();
    }

    public static HazelcastTransactionAccount fromTransactionAccount(TransactionAccount transactionAccount) {
        HazelcastTransactionAccount hazelcastTransactionAccount = new HazelcastTransactionAccount();
        for (Transaction transaction : transactionAccount.getTransactions()) {
            hazelcastTransactionAccount.transactions.add(HazelcastTransaction.fromTransaction(transaction));
        }
        return hazelcastTransactionAccount;
    }

    public List<HazelcastTransaction> getHazelcastTransactions() {
        return transactions;
    }
}
