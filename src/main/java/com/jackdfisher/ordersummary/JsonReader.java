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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jackdfisher.ordersummary.DataModels.AccountHoldings;
import com.jackdfisher.ordersummary.DataModels.Basket;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JsonReader {

    public String retrieveJsonString(String ref, boolean isBasket) throws IOException {
        String url = "https://secure-basin-40956.herokuapp.com/api/" + (isBasket ? "basket/" : "account/") + ref;
        try (Scanner scanner = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Basket readBasket(String ref) throws IOException {
//	    String tstring = "{\"status\":\"VALID\",\"items\":[{\"type\": \"hardware\", \"ref\": \"MINIBOX\", \"price\": \"50.00\",\"action\": \"ADD\"},{\"type\": \"subscription\", \"ref\": \"MANCHESTERTV\", \"price\": \"20.00\",\"action\": \"ADD\"},{\"type\": \"subscription\", \"ref\": \"CINEMA\", \"price\": \"10.00\",\"action\": \"REMOVE\"}]}";

        String tstring = retrieveJsonString(ref, true);
        InputStream is = new ByteArrayInputStream(tstring.getBytes(StandardCharsets.UTF_8));
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().create();
            Basket b = gson.fromJson(reader, Basket.class);
            return b;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AccountHoldings readAccount(String ref) throws IOException {
//	    String tstring = "{\"items\": [{\"type\": \"subscription\", \"ref\": \"ENTERTAINMENT\",\"description\": \"Sky Entertainment\",\"price\": \"45.00\"},{\"type\": \"subscription\", \"ref\": \"CINEMA\",\"description\": \"Sky Cinema\",\"price\": \"10.00\"},{\"type\": \"subscription\", \"ref\": \"SPORTS\",\"description\": \"Sky Sports\",\"price\": \"18.00\"},{\"type\": \"hardware\", \"ref\": \"Q2TB\",\"description\": \"SKY Q 2TB Box\",\"price\": \"199.99\"},{\"type\": \"hardware\",\"ref\": \"TOUCHREMOTE\",\"description\": \"SKY Q Touch Remote\",\"price\": \"10.99\"}]}";
        String tstring = retrieveJsonString(ref, false);
	    InputStream is = new ByteArrayInputStream(tstring.getBytes(StandardCharsets.UTF_8));
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().create();
            AccountHoldings a = gson.fromJson(reader, AccountHoldings.class);
            return a;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
