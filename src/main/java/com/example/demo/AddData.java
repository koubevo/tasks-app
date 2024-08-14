package com.example.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AddData {
    public static void addCategory(String name, String userId) {

        try {
            String phpUrl = "http://4it115.g6.cz/addData.php?q=add-category";
            URL url = new URL(phpUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            String parameters = "id-user=" + userId + "&name=" + name;
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(parameters.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println("AddCategory response:\n" + response.toString());

            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTask(String name, String priority, String note, String deadlineDate, String deadlineTime, String userId, String categoryId) {

        try {
            String phpUrl = "http://4it115.g6.cz/addData.php?q=add-task";
            URL url = new URL(phpUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            String parameters = "id-user=" + userId + "&name=" + name + "&id-category=" + categoryId + "&note=" + note + "&deadline-date=" + deadlineDate+ "&deadline-time=" + deadlineTime + "&priority=" + priority;
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(parameters.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println("AddTask response:\n" + response.toString());

            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
