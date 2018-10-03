package com.jackdfisher.ordersummary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jackdfisher.ordersummary.DataModels.OrderSummaryResponse;

import java.io.IOException;

import static spark.Spark.exception;
import static spark.Spark.*;

public class SummaryController {
    Gson gson = new GsonBuilder().create();

    public SummaryController() {

        before((req, res) -> {
            String path = req.pathInfo();
            if (path.endsWith("/"))
                res.redirect(path.substring(0, path.length() - 1));
        });

        get("/summary", (req, res) -> {
            res.status(400);
            return gson.toJson(new ErrorResponse("You must provide a reference"));
        });
        get("/summary/:ref", (req, res) -> {
                    CreateSummary cs = new CreateSummary(req.params(":ref"));
                    OrderSummaryResponse os = cs.generateSummary();
                    if (os != null) {
                        res.type("application/json");
                        return gson.toJson(os);
                    }
                    res.status(400);
                    res.type("application/json");
                    return gson.toJson(new ErrorResponse("Could not generate the Summary"));
                }
        );
        exception(IOException.class, (e, req, res) -> {
            res.status(400);
            res.type("application/json");
            res.body(gson.toJson(new ErrorResponse(e)));
        });
        exception(RuntimeException.class, (e, req, res) -> {
            res.status(400);
            res.type("application/json");
            res.body(gson.toJson(new ErrorResponse(e)));
        });
    }

    public void stopServer() {
        stop();
    }

}

class ErrorResponse {

    private String error = "there was an error with your request";
    private String sourceOfError;

    public ErrorResponse(String message, String... args) {
        this.sourceOfError = String.format(message, args);
    }

    public ErrorResponse(Exception e) {
        this.sourceOfError = e.getMessage();
    }
}
