package resources.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {

    public RequestSpecification buildRequestSpec() throws IOException {
        /*
        Create 'Logs' directory in target folder if it does not exist.
         */
        File logDirectory = new File("target" + File.separator + "Logs");
        if (!logDirectory.exists()) {
            logDirectory.mkdirs(); // Creates the directory and necessary parent directories
        }

        PrintStream log =
                new PrintStream(
                        Files.newOutputStream(Paths.get(logDirectory + File.separator + "log.txt"))
                );

        return new RequestSpecBuilder()
                .setBaseUri(getGlobalValue("baseURL"))
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build();
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties globalProperties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src" + File.separator + "test" + File.separator + "java" + File.separator + "resources" + File.separator + "global.properties");
        globalProperties.load(fileInputStream);
        return globalProperties.getProperty(key);
    }
}
