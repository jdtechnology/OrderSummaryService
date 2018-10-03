package com.jackdfisher.ordersummary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummaryControllerTests {

    private SummaryController sc;

    public SummaryControllerTests() throws IOException {
    }

    @BeforeEach
    public void startServer() throws InterruptedException {
        sc = new SummaryController();
        Thread.sleep(20000);
    }

    @AfterEach
    public void closeServer() {
        sc.stopServer();
    }

    @Test
    @DisplayName("Requesting /summary/ should give a 400 response")
    void test400ResponseStatus() throws IOException {
        URL url = new URL("http://localhost:4567/summary/");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int statusCode = http.getResponseCode();
        assertEquals(400, statusCode,"Request without reference should give 400");
    }

    @Test
    @DisplayName("Requesting /summary/SUCCESS should give a 200 response with JSON type")
    void test200ResponseStatus() throws IOException {
        URL url = new URL("http://localhost:4567/summary/SUCCESS");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int statusCode = http.getResponseCode();
        String responseType = http.getContentType();
        assertEquals(200, statusCode,"Request with valid reference should give 200");
        assertEquals("application/json", responseType,"Request with valid reference should give JSON");
    }
    @Test
    @DisplayName("Requesting /summary/invalidreference should give a json response")
    void test200ResponseJSON() throws IOException {
        URL url = new URL("http://localhost:4567/summary/invalidreference");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        String actualJson = getResponseBody(http);
        String expectedJson = "{\"error\":\"there was an error with your request\",\"sourceOfError\":\"Server returned HTTP response code: 400 for URL: https://secure-basin-40956.herokuapp.com/api/basket/invalidreference\"}";
        assertEquals(expectedJson, actualJson,"Request without reference should give 400");
    }

    String getResponseBody(HttpURLConnection conn) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
