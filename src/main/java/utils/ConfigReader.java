package utils;

import java.io.InputStream;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

    private static Properties prop;

 
    private static final Logger log =
            LogManager.getLogger(ConfigReader.class);

    static {

        try {

            log.info("Loading config.properties file...");

            prop = new Properties();

            InputStream input =
                    ConfigReader.class.getClassLoader()
                            .getResourceAsStream("config.properties");

            if (input == null) {
                log.error(" config.properties NOT found!");
                throw new RuntimeException("config.properties missing");
            }

            prop.load(input);

            log.info(" config.properties loaded successfully");

        } catch (Exception e) {

            log.error(" Failed to load config.properties", e);
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {

        String value = prop.getProperty(key);

        log.info("Reading property -> {} : {}", key, value);

        return value;
    }
}