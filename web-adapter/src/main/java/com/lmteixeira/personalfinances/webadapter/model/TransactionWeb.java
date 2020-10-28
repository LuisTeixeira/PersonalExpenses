package com.lmteixeira.personalfinances.webadapter.model;

import java.math.BigDecimal;

public class TransactionWeb {

    private String description;
    private BigDecimal value;

    public TransactionWeb(String description, Double value) {
        this.description = description;
        this.value = BigDecimal.valueOf(value);
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }
}
