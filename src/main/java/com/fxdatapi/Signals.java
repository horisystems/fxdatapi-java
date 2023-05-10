package com.fxdatapi;

import java.io.IOException;
import java.util.Optional;
import okhttp3.*;
import okhttp3.MediaType;

public class Signals {

    private static final String BASE_URL = "https://fxdatapi.com/v1/signals";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static Optional<String> getAllSignals(String username) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "?username=" + username)
                .header("Accept", "application/json")
                .header("Cookie", "user_type=member; username=" + username)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Optional.ofNullable(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static Optional<String> getSignalById(String username, String signalId) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + signalId + "?username=" + username)
                .header("Accept", "application/json")
                .header("Cookie", "user_type=member; username=" + username)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Optional.ofNullable(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
