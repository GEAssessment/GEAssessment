package base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class InitializeLogging {
    public static Logger log = Logger.getLogger("Regression");
    // static block
    static{
        String propDir = System.getProperty("user.dir");
        //PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure(propDir+"\\src\\log4j.properties");

        //log the message to file
        log.trace("Logging Initialized");
    }
}