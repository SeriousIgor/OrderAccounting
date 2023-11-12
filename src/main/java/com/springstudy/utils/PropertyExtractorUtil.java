package com.springstudy.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyExtractorUtil {
    private static Properties properties;
    static {
        properties = new Properties();
        URL url = PropertyExtractorUtil.class.getClassLoader().getResource("application.properties");
        if (url != null) {
            try{
                properties.load(new FileInputStream(url.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
