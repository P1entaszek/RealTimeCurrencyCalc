package com.prod.realtimecurrencycalc.TestUtils;

/**
 * Created by Piotr Jaszczurowski on 30.09.2019.
 */

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TestUtils {

    private TestUtils() {
    }

    public static <JsonStringObject, JsonObjectClass extends Class<JsonStringObject>> JsonStringObject loadJson(String path, JsonObjectClass classT) {
        JsonStringObject json = (JsonStringObject) getFileString(path);
        return new Gson().fromJson(String.valueOf(json), classT);
    }

    private static String getFileString(String path) {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    TestUtils.class.getClassLoader().getResourceAsStream(path)));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read from resource at: $path");
        }

    }

}
