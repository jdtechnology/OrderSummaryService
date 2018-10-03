package com.jackdfisher.ordersummary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSummaryTests {
    private CreateSummary cs;

    {
        try {
            cs = new CreateSummary("SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void generatePricingTest() {
        assertEquals("SummaryPricing", cs.generatePricingObject().getClass().getSimpleName()    , "Should be called Pricing");
    }
    @Test
    void generateHoldingsTest() {
        assertEquals("SummaryState", cs.createSummaryStateObject().getClass().getSimpleName(), "Should be called Pricing");
    }

    @Test
    @DisplayName("createSummary response should return a OrderSummaryResponse")
    void createSummaryTest() {
        assertEquals("OrderSummaryResponse", cs.generateSummary().getClass().getSimpleName(), "Should be a object");
    }

}
