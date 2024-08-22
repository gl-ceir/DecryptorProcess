/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gl.reader;

import com.gl.reader.configuration.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootConfiguration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.gl.reader"})

public class PasswordDecryption {

    public static void main(String[] args) {
        try {
            PropertyReader propertyReader = new PropertyReader();
            final String encrpy_text = propertyReader.getPropValue(args[0]).trim();
            System.out.println(passwordDecryp(encrpy_text));
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static String passwordDecryp(String password) {
        try {
            BasicTextEncryptor encryptor = new BasicTextEncryptor();
            String secretKey = System.getenv("JASYPT_ENCRYPTOR_PASSWORD");
            encryptor.setPassword(secretKey);
            return encryptor.decrypt(password);
        } catch (Exception e) {
            return null;
        }
    }
}
