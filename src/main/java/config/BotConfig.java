package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BotConfig {
    private static final String CONFIG_FILE = "config/config.properties";
    private static final Properties properties = new Properties();
    private static String error;

    static {
        try {
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            error = "Bot config error";
        }
    }

    public static String getBotToken() {
        if (error != null) return error;
        return properties.getProperty("botToken");
    }

    public static String getNasaToken() {
        if (error != null) return error;
        return properties.getProperty("nasaToken");
    }
}
