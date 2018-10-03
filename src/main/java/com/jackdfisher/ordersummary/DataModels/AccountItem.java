package com.jackdfisher.ordersummary.DataModels;

import java.math.BigDecimal;

public class AccountItem {
    private String type;
    private String ref;
    private String description;
    private BigDecimal price;

    public String getType() {
        return type;
    }

    public String getRef() {
        return ref;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Ref: %s, Description: %s, Price: %s", type, ref, description, price);
    }

}