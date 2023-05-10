package com.fxdatapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class MonthlyAverage {

    public static Optional<String> getMonthlyAverage(String username, String year, String month) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fxdatapi.com/v1/monthly_average/" + year + "/" + month))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Cookie", "user_type=member; username=" + username)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Optional.of(response.body());
            } else {
                System.out.println("Fetching monthly average failed with status code: " + response.statusCode());
                System.out.println("Response body: " + response.body());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
