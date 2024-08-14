package com.example.demo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Type;
import java.util.Map;
import java.io.StringReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class getData {


    public boolean correctInfo;

    public static List<Category> getCategories(String userID) {
        try {
            String phpUrl = "http://4it115.g6.cz/getData.php?q=select-categories&id-user=" + userID;
            URL url = new URL(phpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(String.valueOf(response));
                    JSONArray jsonArray = (JSONArray) obj;
                    Map<String, Category> cats = new HashMap<>();
                    List<Category> catsList = new ArrayList<>();
                    for (Object element : jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;

                        String categoryId = (String) jsonObject.get("category_id");
                        String name = (String) jsonObject.get("name");
                        //melo by se to tady pridat do tech kategorii
                        Category cat = new Category();
                        cat.setCategoryId(categoryId);
                        cat.setName(name);
                        cats.put(categoryId, cat);
                        catsList.add(cat);
                        System.out.println("Category ID: " + categoryId + ", Name: " + name);
                    }
                    return catsList;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Task getTask(String id) { //Když je id "32", vrací to null, i když by to měl být task Vyvenčit otroka.
        try {
            String phpUrl = "http://4it115.g6.cz/getData.php?q=select-task&id-task=" + id;
            URL url = new URL(phpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(String.valueOf(response));
                    JSONArray jsonArray = (JSONArray) obj;

                    for (Object element : jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;

                        Task task = new Task((String) jsonObject.get("name"), (String) jsonObject.get("note"), (String) jsonObject.get("edited_date"), (String) jsonObject.get("priority"), (String) jsonObject.get("added_date"), (String) jsonObject.get("deadline_date"), (String) jsonObject.get("deadline_time"), (String) jsonObject.get("task_id"), (String) jsonObject.get("user_id"), (String) jsonObject.get("category_id"), (String) jsonObject.get("done"));


                        System.out.println("Task ID: " + (String) jsonObject.get("task_id") + ", Name: " + jsonObject.get("name"));
                        return task;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Login response:\n" + response.toString());
            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }

            connection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static List<Task> getTasks(String userID, String categoryID) {
        String idUser = userID;
        String idCategory = categoryID;


        try {
            String phpUrl = "http://4it115.g6.cz/getData.php?q=select-tasks&id-user=" + idUser + "&id-category=" + idCategory;
            URL url = new URL(phpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(String.valueOf(response));
                    JSONArray jsonArray = (JSONArray) obj;
                    List<Task> taskList = new ArrayList<>();
                    for (Object element : jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;

                        String taskId = (String) jsonObject.get("task_id");
                        String name = (String) jsonObject.get("name");
                        String priority = (String) jsonObject.get("priority");
                        String note = (String) jsonObject.get("note");
                        String deadlineDate = (String) jsonObject.get("deadline_date");
                        String deadlineTime = (String) jsonObject.get("deadline_time");
                        String addedDate = (String) jsonObject.get("added_date");
                        String editedDate = (String) jsonObject.get("edited_date");
                        String done = (String) jsonObject.get("done");
                        String userId = (String) jsonObject.get("user_id");
                        String categoryId = (String) jsonObject.get("category_id");

                        Task cat = new Task(name, note, editedDate, priority, addedDate, deadlineDate, deadlineTime, taskId, userId, categoryId, done);

                        taskList.add(cat);
                        System.out.println("Task ID: " + categoryId + ", Name: " + name);
                    }
                    return taskList;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Response from PHP file:\n" + response.toString());
            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }



            connection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getStats (String userID) {
        String idUser = userID;


        try {
            String phpUrl = "http://4it115.g6.cz/getStats.php?id=" + idUser;
            URL url = new URL(phpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(String.valueOf(response));
                    JSONArray jsonArray = (JSONArray) obj;
                    Map<String, String> stats = new HashMap<>();
                    for (Object element : jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;

                        String done = (String) jsonObject.get("done");
                        String notDone = (String) jsonObject.get("notDone");
                        String perc = (String) jsonObject.get("perc");
                        stats.put("done", done);
                        stats.put("notDone", notDone);
                        stats.put("perc", perc);
                    }
                    System.out.println(stats);
                    return  stats;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Response from PHP file:\n" + response.toString());
            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }



            connection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void getAllTasks(String userID) {
        String idUser = userID;


        try {
            String phpUrl = "http://4it115.g6.cz/getData.php?q=select-all-tasks&id-user=" + idUser;
            URL url = new URL(phpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                System.out.println("Response from PHP file:\n" + response.toString());
            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User login(String username, String password) {
        try {
            String phpUrl = "http://4it115.g6.cz/login.php";
            URL url = new URL(phpUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            String parameters = "username=" + username + "&password=" + password;
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

                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(String.valueOf(response));
                    JSONArray jsonArray = (JSONArray) obj;

                    for (Object element : jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;

                        String userId = (String) jsonObject.get("user_id");
                        String name = (String) jsonObject.get("name");
                        username = (String) jsonObject.get("username");
                        //melo by se to tady pridat do tech kategorii
                        User user = new User();
                        user.setUserID(userId);
                        user.setUserName(name);
                        user.setUserUsername(username);
                        //users.put(userId, user);
                        System.out.println("User ID: " + userId + ", Name: " + name+ ", username: " + username);
                        return user;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Login response:\n" + response.toString());
            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }

            connection.disconnect();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User register(String username, String password, String name) {
        //String username = "newUser";
        //String password = "kokos877";
       //String name = "Jan";

        try {
            String phpUrl = "http://4it115.g6.cz/register.php";
            URL url = new URL(phpUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setDoOutput(true);

            String parameters = "username=" + username + "&password=" + password+ "&name=" + name;
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

                JSONParser parser = new JSONParser();

                try {
                    Object obj = parser.parse(String.valueOf(response));
                    JSONArray jsonArray = (JSONArray) obj;

                    for (Object element : jsonArray) {
                        JSONObject jsonObject = (JSONObject) element;

                        String userId = (String) jsonObject.get("user_id");
                        User user = new User();
                        user.setUserID(userId);
                        user.setUserName(name);
                        user.setUserUsername(username);
                        //users.put(userId, user);
                        System.out.println("User ID: " + userId + ", Name: " + name+ ", username: " + username);
                        return user;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println("Register response:\n" + response.toString());

            } else {
                System.out.println("Failed to make the request. Response code: " + responseCode);
            }

            connection.disconnect();

            //System.out.println("User ID: " + userId + ", Name: " + name+ ", username: " + username);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
