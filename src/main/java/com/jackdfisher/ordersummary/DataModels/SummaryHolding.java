package com.jackdfisher.ordersummary.DataModels;

import java.math.BigDecimal;

public class SummaryHolding {
    private String type;
    private String ref;
    private BigDecimal price;

    public SummaryHolding(String type, String ref, BigDecimal price) {
        this.type = type;
        this.ref = ref;
        this.price = price;
    }
}