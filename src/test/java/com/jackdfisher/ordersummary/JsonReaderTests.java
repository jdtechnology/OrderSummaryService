/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.jackdfisher.ordersummary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonReaderTests {

    @Test
    @DisplayName("readBasket should return a Basket Object")
    void readBasketTest() throws IOException {
	    JsonReader jsonReader = new JsonReader();
	    assertEquals("Basket", jsonReader.readBasket("SUCCESS").getClass().getSimpleName(), "Should be a basket");
    }
    @Test
    @DisplayName("readAccount should return an AccountHolding object")
    void readAccountTest() throws IOException {
	    JsonReader jsonReader = new JsonReader();
	    assertEquals("AccountHoldings", jsonReader.readAccount("SUCCESS").getClass().getSimpleName(), "Should be an Account");
    }
}
