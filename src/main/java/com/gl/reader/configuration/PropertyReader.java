package com.gl.reader.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


public class PropertyReader {

    Properties prop = new Properties();

    Properties loadProperties() {
        String filePath = System.getenv("commonConfigurationFilePath");
        try (InputStream inputStream = new FileInputStream(filePath);) {
            prop.load(inputStream);
        } catch (Exception e) {
             System.out.println(e);
        }
        return prop;
    }

    public String getPropValue(String key) {
        try {
            prop = loadProperties();
            if (Objects.nonNull(prop)) {
                String value = prop.getProperty(key).trim();
                return value.startsWith("ENC(")
                        ? value.substring(4, value.lastIndexOf(")")) : value;
            } else {
                return null;
            }
        } catch (Exception e) {
             System.out.println(e);
            return null;
        }
    }
}
