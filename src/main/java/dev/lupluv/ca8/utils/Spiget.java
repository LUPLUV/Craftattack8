package dev.lupluv.ca8.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Spiget {

    private static final String USER_AGENT  = "LUPLUV";// Change this!

    public static String getLatestVersion(String resource){
        try {
            URL url = new URL("https://api.spiget.org/v2/resources/" + resource + "/versions/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", USER_AGENT);// Set User-Agent

            // If you're not sure if the request will be successful,
            // you need to check the response code and use #getErrorStream if it returned an error code
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            // This could be either a JsonArray or JsonObject
            JsonElement element = new JsonParser().parse(reader);
            if (element.isJsonArray()) {
                // Is JsonArray
            } else if (element.isJsonObject()) {
                // Is JsonObject
                JsonObject jsonObject = element.getAsJsonObject();
                String version = jsonObject.get("name").getAsString();
                return version;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public static String getLatestVersionId(String resource){
        try {
            URL url = new URL("https://api.spiget.org/v2/resources/" + resource + "/versions/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", USER_AGENT);// Set User-Agent

            // If you're not sure if the request will be successful,
            // you need to check the response code and use #getErrorStream if it returned an error code
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            // This could be either a JsonArray or JsonObject
            JsonElement element = new JsonParser().parse(reader);
            if (element.isJsonArray()) {
                // Is JsonArray
            } else if (element.isJsonObject()) {
                // Is JsonObject
                JsonObject jsonObject = element.getAsJsonObject();
                String id = jsonObject.get("id").getAsString();
                return id;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public static String getLatestDownload(String resource, String jarName, String repName, String append){
        String ver = getLatestVersion(resource);
        return "https://github.com/LUPLUV/" + repName + "/releases/download/" + ver + "/" + jarName + "-" + ver + append + ".jar";
    }

}
