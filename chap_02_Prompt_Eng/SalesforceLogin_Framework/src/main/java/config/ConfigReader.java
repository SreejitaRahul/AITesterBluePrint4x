package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties not found on the classpath");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to load config.properties", exception);
        }
    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getUrl() {
        return PROPERTIES.getProperty("url");
    }

    public static String getValidUsername() {
        return PROPERTIES.getProperty("valid.username");
    }

    public static String getValidPassword() {
        return PROPERTIES.getProperty("valid.password");
    }

    public static String getInvalidUsername() {
        return PROPERTIES.getProperty("invalid.username");
    }

    public static String getInvalidPassword() {
        return PROPERTIES.getProperty("invalid.password");
    }

    public static boolean isRememberMeEnabled() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("rememberme"));
    }

    public static int getImplicitTimeout() {
        return Integer.parseInt(PROPERTIES.getProperty("implicit.timeout"));
    }

    public static int getExplicitTimeout() {
        return Integer.parseInt(PROPERTIES.getProperty("explicit.timeout"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(PROPERTIES.getProperty("page.load.timeout"));
    }
}
