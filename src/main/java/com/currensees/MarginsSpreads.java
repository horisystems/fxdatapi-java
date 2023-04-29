package com.currensees;

import java.io.IOException;
import java.util.Optional;
import okhttp3.*;
import okhttp3.MediaType;

public class MarginsSpreads {

    private static final String BASE_URL = "https://currensees.com/v1/margins_spreads";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static Optional<String> getAllMarginsSpreads(String username, int day, int month, int year) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "?username=" + username + "&day=" + day + "&month=" + month + "&year=" + year)
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

    public static Optional<String> getMarginsSpreadsById(String username, String uuid, int day, int month, int year) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + uuid + "?username=" + username + "&day=" + day + "&month=" + month + "&year=" + year)
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
