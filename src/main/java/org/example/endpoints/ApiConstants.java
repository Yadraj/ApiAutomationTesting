package org.example.endpoints;

import org.example.utils.PropertyReader;

import java.io.IOException;

public class ApiConstants {

    public static String Base_URL;

    static {

        try {
            Base_URL = PropertyReader.readkey("url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public static String CREATE_BOOKING="/booking";
        public static String UPDATE_BOOKING="/booking";

}
