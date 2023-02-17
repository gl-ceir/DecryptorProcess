package com.gl.reader.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyReader {

    Properties prop = new Properties();
    private static final Logger logger = LogManager.getLogger(PropertyReader.class);

    Properties loadProperties() {
         String filePath = "/u01/ceirapp/configuration/configuration.properties"; 
        try (InputStream inputStream = new FileInputStream(filePath);) {
            prop.load(inputStream);
        } catch (IOException io) {
           // logger.error(io.toString(), (Throwable) io);
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
        } catch (Exception io) {
         //   logger.error(io.toString(), (Throwable) io);
            return null;
        }
    }
}
