package com.jackdfisher.ordersummary;

import com.jackdfisher.ordersummary.DataModels.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

public class CreateSummary {

    private String ref;
    private Basket basketResponse;
    private AccountHoldings accountResponse;
    private JsonReader jsonReader = new JsonReader();


    public CreateSummary(String ref) throws IOException {
        this.ref = ref;
        retrieveBasket();
        retrieveAccount();
    }

    private void retrieveBasket() throws IOException {
        this.basketResponse = jsonReader.readBasket(ref);
    }

    private void retrieveAccount() throws IOException {
        this.accountResponse = jsonReader.readAccount(ref);
    }

    public SummaryPricing generatePricingObject() {

        BigDecimal totalRecPriceChange = BigDecimal.ZERO;
        BigDecimal totalNonRecPriceChange = BigDecimal.ZERO;

        BigDecimal currentPrice = BigDecimal.ZERO;

        for (AccountItem item : accountResponse.getItems()) {
            if ("subscription".equals(item.getType().toLowerCase())) currentPrice = currentPrice.add(item.getPrice());
        }

        for (BasketItem item : basketResponse.getItems()) {
            if ("add".equals(item.getAction().toLowerCase())) {
                if ("subscription".equals(item.getType().toLowerCase())) {
                    totalRecPriceChange = totalRecPriceChange.add(item.getPrice());
                } else totalNonRecPriceChange = totalNonRecPriceChange.add(item.getPrice());
            } else if ("remove".equals(item.getAction().toLowerCase())) {
                if ("subscription".equals(item.getType().toLowerCase())) {
                    totalRecPriceChange = totalRecPriceChange.subtract(item.getPrice());
                } else totalNonRecPriceChange = totalNonRecPriceChange.subtract(item.getPrice());
            }
        }
        BigDecimal newPrice = currentPrice.add(totalRecPriceChange);

        return new SummaryPricing(new SummaryChanges(new SummaryAmount(totalRecPriceChange),
                new SummaryAmount(totalNonRecPriceChange)),
                new SummaryAmount(currentPrice), new SummaryAmount(newPrice));

    }

    public SummaryState createSummaryStateObject() {
        SummaryHolding[] sh = Stream.concat(Arrays.stream(accountResponse.getItems())
                        .map(item -> new SummaryHolding(item.getType(), item.getRef(), item.getPrice())),
                Arrays.stream(basketResponse.getItems())
                        .map(item -> new SummaryHolding(item.getType(), item.getRef(), item.getPrice())))
                .toArray(SummaryHolding[]::new);
        return new SummaryState(sh);
    }

    public OrderSummaryResponse generateSummary() {
        return new OrderSummaryResponse(generatePricingObject(), createSummaryStateObject());
    }
}