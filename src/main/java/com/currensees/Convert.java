package com.currensees;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class Convert {

    public static Optional<String> convert(String username, String date, String baseCurrency, String targetCurrency, String amount) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String json = String.format(
                    "{" +
                            "\"username\": \"%s\"," +
                            "\"date\": \"%s\"," +
                            "\"base_currency\": \"%s\"," +
                            "\"target_currency\": \"%s\"," +
                            "\"amount\": \"%s\"" +
                            "}", username, date, baseCurrency, targetCurrency, amount);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://currensees.com/v1/convert"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Cookie", "user_type=member; username=" + username)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Optional.of(response.body());
            } else {
                System.out.println("Conversion failed with status code: " + response.statusCode());
                System.out.println("Response body: " + response.body());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
