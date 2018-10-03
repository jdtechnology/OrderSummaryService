package com.jackdfisher.ordersummary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryControllerTests {

    private SummaryController sc;

    public SummaryControllerTests() throws InterruptedException {
        sc = new SummaryController();
        Thread.sleep(2000);
    }

//    @BeforeEach
//    public void startServer() throws InterruptedException {
//
//        Thread.sleep(2000);
//    }
//
//    @AfterEach
//    public void closeServer() {
//        sc.stopServer();
//    }

    @Test
    @DisplayName("Requesting /summary/ should give a 400 response")
    void test400ResponseStatus() throws IOException {
        URL url = new URL("http://localhost:4567/summary/");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int statusCode = http.getResponseCode();
        http.disconnect();
        assertEquals(400, statusCode,"Request without reference should give 400");
    }

    @Test
    @DisplayName("Requesting /summary/SUCCESS should give a 200 response with JSON type")
    void test200ResponseStatus() throws IOException {
        URL url = new URL("http://localhost:4567/summary/SUCCESS");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int statusCode = http.getResponseCode();
        String responseType = http.getContentType();
        http.disconnect();
        assertEquals(200, statusCode,"Request with valid reference should give 200");
        assertEquals("application/json", responseType,"Request with valid reference should give JSON");
    }
}
