package com.currensees;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class Historical {

    public static Optional<String> getAllHistoricalData(String username, String date, int day, int month, int year) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String url = String.format("https://currensees.com/v1/historical?username=%s&date=%s&day=%d&month=%d&year=%d", username, date, day, month, year);

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
                System.out.println("Fetching all historical data failed with status code: " + response.statusCode());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<String> getHistoricalDataById(String username, String dataId, String dateString, int day, int month, int year) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            String url = String.format("https://currensees.com/v1/historical/%s?username=%s&date_string=%s&day=%d&month=%d&year=%d", dataId, username, dateString, day, month, year);

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
                System.out.println("Fetching historical data by ID failed with status code: " + response.statusCode());
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
