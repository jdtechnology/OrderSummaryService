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
/*
{"status":"VALID","items":[{"type": "hardware", "ref": "MINIBOX", "price": "50.00","action": "ADD"},{"type": "subscription", "ref": "MANCHESTERTV", "price": "20.00","action": "ADD"},{"type": "subscription", "ref": "CINEMA", "price": "10.00","action": "REMOVE"}]}
 */
