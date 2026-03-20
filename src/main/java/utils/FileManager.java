package utils;

import org.json.JSONArray;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {
    public static boolean SaveJson(String path, String content) {
        Path filePath = Path.of(path);
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            Files.writeString(filePath, content, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("File create error! " + e.getMessage());
            return false;
        }
        return true;
    }

    public static HashMap<String, String> parseApodResponse(String apod) {
        if (apod.isEmpty()) return null;
        HashMap<String, String> res = new HashMap<>();
        if (apod.startsWith("<html>")) {
            res.put("error", Jsoup.parse(apod).title());
            return res;
        }

        var response = new JSONArray(apod).getJSONObject(0);
        for (String key : response.keySet()) {
            res.put(key, response.optString(key, ""));
        }
        return res;
    }
}
