package com.fxdatapi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class Auth {

    public static Optional<String> loginAndGetUsername(String username, String password) {
        HttpClient client = HttpClient.newHttpClient();
        String json = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fxdatapi.com/v1/login"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Assuming the response body contains the username when login is successful
                return Optional.of(response.body());
            } else {
                System.out.println("Login failed with status code: " + response.statusCode());
                return Optional.empty();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error during login: " + e.getMessage());
            return Optional.empty();
        }
    }
}
