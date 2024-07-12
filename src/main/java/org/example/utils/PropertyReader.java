package org.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {


    public static String readkey(String key) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/java/org/example/resources/TestData.properties"));
        Properties prop = new Properties();
        prop.load(fileInputStream);

        if (prop.getProperty(key) == null) {
            System.out.println("not abl to find the key");

        }
            return prop.getProperty(key);

    }

}
