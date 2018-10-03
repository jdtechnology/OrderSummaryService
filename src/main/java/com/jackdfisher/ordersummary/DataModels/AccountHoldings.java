package com.jackdfisher.ordersummary.DataModels;

import java.util.Arrays;

public class AccountHoldings {
    AccountItem[] items;

    public AccountItem[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return String.format("Items: %s", Arrays.toString(items));
    }
}