package com.lmteixeira.personalfinances.testdata;

import com.lmteixeira.personalfinances.domain.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.stream.Collectors;

public class LoadUsersFromFile {

    private static final String USER_JSON_FILE_NAME = "/users.json";

    private JSONParser parser = new JSONParser();

    public User[] getUsersFromFile() {
        User[] users = null;
        try {
            Object obj = parser.parse(readFileFromResources());
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("users");
            users = new User[jsonArray.size()];
            int index = 0;
            Iterator<JSONObject> items = jsonArray.iterator();
            while (items.hasNext()) {
                JSONObject item = items.next();
                String email = (String) item.get("email");
                User user = new User(email);
                users[index] = user;
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    private String readFileFromResources() throws URISyntaxException, IOException {
        InputStream in = getClass().getResourceAsStream(USER_JSON_FILE_NAME);
        String jsonString = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
        return jsonString;
    }
}
