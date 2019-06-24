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
