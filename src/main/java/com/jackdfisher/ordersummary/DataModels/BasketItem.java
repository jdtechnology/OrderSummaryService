package com.jackdfisher.ordersummary.DataModels;

import java.math.BigDecimal;

public class BasketItem {
    private String type;
    private String ref;
    private BigDecimal price;
    private String action;

    public String getType() {
        return type;
    }

    public String getRef() {
        return ref;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Ref: %s, Price: %s, action: %s", type, ref, price, action);
    }
}
