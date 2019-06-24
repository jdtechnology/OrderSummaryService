package com.jackdfisher.ordersummary.DataModels;

import java.util.Arrays;

public class Basket {
    private String status;
    private BasketItem[] items;

    public BasketItem[] getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("Status: %s, Items: %s", status, Arrays.toString(items));
    }
}