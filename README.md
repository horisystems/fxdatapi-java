## Currency API Java Client

[![Build Status](https://github.com/horisystems/fxdatapi-java/actions/workflows/ci.yml/badge.svg)](https://github.com/horisystems/fxdatapi-java/actions/workflows/ci.yml)

A Java client for interacting with [Currency API](https://horisystems.com/currency-api/).

[Hori Systems](https://horisystems.com) provides customers with accurate and up-to-date currency data for making informed decisions in the global financial market, including Gold, Silver and Special Drawing Rights. Our reliable exchange rate data assist businesses in confidently managing foreign transactions, investments and risks. Accounting, E-commerce, CRM, and ERP software companies will find it helpful to integrate with us instead of developing a custom in-house solution.

### Requirements

This library is tested with Java versions 11, 14, 15, 16 and 17.

### Installation

#### Maven

Add this dependency to your project's POM:

```java
<dependency>
  <groupId>com.fxdatapi</groupId>
  <artifactId>fxdatapi-java</artifactId>
  <version>X.X.X</version>
</dependency>
```

#### Gradle

Add this dependency to your `build.gradle` file:

```
compile 'com.fxdatapi:fxdatapi-java:X.X.X'
```

### Usage Example

This library is a Java client you can use to interact with the [Currency API](https://horisystems.com/currency-api/).

```java
package myapp;

import com.fxdatapi;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Optional;

public class MyApp {

    public static void main(String[] args) {
        String username = "your_username";
        String password = "your_password";

        Optional<String> loginResponse = Auth.loginAndGetUsername(username, password);

        if (loginResponse.isPresent()) {
            // Convert
            Optional<String> conversionResult = Convert.convert(username, "2023_04_19", "GBP", "EUR", "500");
            if (conversionResult.isPresent()) {
                System.out.println("Conversion result: " + conversionResult.get());
            } else {
                System.out.println("Conversion failed.");
            }

            // ConvertAll
            Optional<String> conversionAllResult = ConvertAll.convertAll(username, "2023_04_02", "GBP", "120");
            if (conversionAllResult.isPresent()) {
                System.out.println("Conversion all result: " + conversionAllResult.get());
            } else {
                System.out.println("Conversion all failed.");
            }

            // DailyAverage
            Optional<String> dailyAverageResult = DailyAverage.getDailyAverage(username, "2023_04_02");
            if (dailyAverageResult.isPresent()) {
                System.out.println("Daily average result: " + dailyAverageResult.get());
            } else {
                System.out.println("Fetching daily average failed.");
            }

            // WeeklyAverage
            Optional<String> weeklyAverageResult = WeeklyAverage.getWeeklyAverage(username, "2023_04_03", "2023_04_07");
            if (weeklyAverageResult.isPresent()) {
                System.out.println("Weekly average result: " + weeklyAverageResult.get());
            } else {
                System.out.println("Fetching weekly average failed.");
            }

            // MonthlyAverage
            Optional<String> monthlyAverageResult = MonthlyAverage.getMonthlyAverage(username, 2023, 04);
            if (monthlyAverageResult.isPresent()) {
                System.out.println("Monthly average result: " + monthlyAverageResult.get());
            } else {
                System.out.println("Fetching monthly average failed.");
            }

            // Currencies
            Optional<String> currenciesResult = Currencies.getAllCurrencies(username, 19, 4, 2024);
            if (currenciesResult.isPresent()) {
                System.out.println("All currencies result: " + currenciesResult.get());
            } else {
                System.out.println("Fetching all currencies failed.");
            }

            // Currencies - getCurrencyById
            Optional<String> currencyByIdResult = Currencies.getCurrencyById(username, "594bffc4-d095-11ed-9e30-acde48001122", 19, 4, 2023);
            if (currencyByIdResult.isPresent()) {
                System.out.println("Currency by ID result: " + currencyByIdResult.get());
            } else {
                System.out.println("Fetching currency by ID failed.");
            }

            // Historical
            Optional<String> allHistoricalDataResult = Historical.getAllHistoricalData(username, "2023_04_02", 19, 4, 2023);
            if (allHistoricalDataResult.isPresent()) {
                System.out.println("All historical data result: " + allHistoricalDataResult.get());
            } else {
                System.out.println("Fetching all historical data failed.");
            }

            // Historical - getHistoricalById
            Optional<String> historicalDataByIdResult = Historical.getHistoricalDataById(username, "fe1ee1c4-d162-11ed-a2dc-acde48001122", "2023_04_02", 19, 4, 2023);
            if (historicalDataByIdResult.isPresent()) {
                System.out.println("Historical data by ID result: " + historicalDataByIdResult.get());
            } else {
                System.out.println("Fetching historical data by ID failed.");
            }

            // MarginsSpreads - getAllMarginsSpreads
            Optional<String> marginsSpreadsAllResult = MarginsSpreads.getAllMarginsSpreads(username, 19, 4, 2023);
            if (marginsSpreadsAllResult.isPresent()) {
                System.out.println("All Margins Spreads result: " + marginsSpreadsAllResult.get());
            } else {
                System.out.println("Fetching all Margins Spreads failed.");
            }
            
            // MarginsSpreads - getMarginsSpreadsById
            Optional<String> marginsSpreadsByIdResult = MarginsSpreads.getMarginsSpreadsById(username, "8e694000-e3c2-11ed-b5bd-acde48001122", 19, 4, 2023);
            if (marginsSpreadsByIdResult.isPresent()) {
                System.out.println("Margins Spreads by ID result: " + marginsSpreadsByIdResult.get());
            } else {
                System.out.println("Fetching Margins Spreads by ID failed.");
            }

            // Performances
            Optional<String> performancesResult = Performances.getAllPerformances(username);
            if (performancesResult.isPresent()) {
                System.out.println("Performances result: " + performancesResult.get());
            } else {
                System.out.println("Fetching performances failed.");
            }

            // Performances - getPerformanceById
            Optional<String> performanceByIdResult = Performances.getPerformanceById(username, "8edd9b12-e3c2-11ed-b5bd-acde48001122");
            if (performanceByIdResult.isPresent()) {
                System.out.println("Performance by ID result: " + performanceByIdResult.get());
            } else {
                System.out.println("Fetching Performance by ID failed.");
            }

            // Signals
            Optional<String> signalsResult = Signals.getAllSignals(username);
            if (signalsResult.isPresent()) {
                System.out.println("Signals result: " + signalsResult.get());
            } else {
                System.out.println("Fetching signals failed.");
            }

            // Signals - getSignalById
            Optional<String> signalByIdResult = Signals.getSignalById(username, "8e694050-e3c2-11ed-b5bd-acde48001122");
            if (signalByIdResult.isPresent()) {
                System.out.println("Signal by ID result: " + signalByIdResult.get());
            } else {
                System.out.println("Fetching Signal by ID failed.");
            }

        } else {
            System.out.println("Login failed.");
        }
    }
}
```

### Setting up Currency API Account

Subscribe [here](https://horisystems.com/currency-api/) for a user account.

### Using the Currency API

You can read the [API documentation](https://docs.fxdatapi.com/) to understand what's possible with the Currency API. If you need further assistance, don't hesitate to [contact us](https://horisystems.com/contact/).

### License

This project is licensed under the [BSD 3-Clause License](./LICENSE).

### Copyright

(c) 2020 - 2023 [Hori Systems Limited](https://horisystems.com).
