package com.work.framework;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.work.framework.UserData;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonDataReader {

    public static List<UserData> readJsonData(String filePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<UserData>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
