package com.currensees;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class Currencies {

    public static Optional<String> getAllCurrencies(String username, int day, int month, int year) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String url = String.format("https://currensees.com/v1/currencies?username=%s&day=%d&month=%d&year=%d", username, day, month, year);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .header("Cookie", "user_type=member; username=" + username)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Optional.of(response.body());
            } else {
                System.out.println("Fetching all currencies failed with status code: " + response.statusCode());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<String> getCurrencyById(String username, String currencyId, int day, int month, int year) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String url = String.format("https://currensees.com/v1/currencies/%s?username=%s&day=%d&month=%d&year=%d", currencyId, username, day, month, year);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .header("Cookie", "user_type=member; username=" + username)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Optional.of(response.body());
            } else {
                System.out.println("Fetching currency by ID failed with status code: " + response.statusCode());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
