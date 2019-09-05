package base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= "src/configs//Config.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getBrowserName(){
        String browserName = properties.getProperty("browser");
        if(browserName!= null) return browserName;
        else throw new RuntimeException("browserName not specified in the Configuration.properties file.");
    }

    public String getChromeDriverPath(){
        String chromeDriverPath = properties.getProperty("chromedriverpath");
        if(chromeDriverPath!= null) return chromeDriverPath;
        else throw new RuntimeException("chromeDriverPath not specified in the Configuration.properties file.");
    }

    public String getFirefoxDriverPath(){
        String geckoDriverPath = properties.getProperty("geckDriverPath");
        if(geckoDriverPath!= null) return geckoDriverPath;
        else throw new RuntimeException("geckodriverPath not specified in the Configuration.properties file.");
    }

    public String getIEDriverPath(){
        String ieDriverPath = properties.getProperty("ieDriverPath");
        if(ieDriverPath!= null) return ieDriverPath;
        else throw new RuntimeException("geckodriverPath not specified in the Configuration.properties file.");
    }

    public String getAppUsername(){
        String userName = properties.getProperty("username");
        if(userName!= null) return userName;
        else throw new RuntimeException("userName not specified in the Configuration.properties file.");
    }

    public String getAppPassword(){
        String appPassword = properties.getProperty("password");
        if(appPassword!= null) return appPassword;
        else throw new RuntimeException("appPassword not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

}