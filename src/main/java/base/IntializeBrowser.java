package base;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class IntializeBrowser extends InitializeLogging {
    // this browser variable will be used through the framework
    protected static WebDriver driver;
    ConfigFileReader configFileReader;

    @BeforeSuite
    public void setUp() {
        configFileReader = new ConfigFileReader();
        String userDir = System.getProperty("user.dir");
        String browserToChoose = configFileReader.getBrowserName();
        log.info("Trying to browser : "+browserToChoose);
        if(browserToChoose.equalsIgnoreCase("firefox") || browserToChoose.equalsIgnoreCase("ff")){
            System.setProperty("webdriver.gecko.driver", userDir+"\\"+configFileReader.getFirefoxDriverPath());
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(configFileReader.getApplicationUrl());
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }else if(browserToChoose.equalsIgnoreCase("chrome") || browserToChoose.equalsIgnoreCase("gc")){
            System.setProperty("webdriver.chrome.driver", userDir+"\\"+configFileReader.getChromeDriverPath());
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(configFileReader.getApplicationUrl());
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }else if(browserToChoose.equalsIgnoreCase("internet explorer") || browserToChoose.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver", userDir+"\\"+configFileReader.getIEDriverPath());
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            driver.get(configFileReader.getApplicationUrl());
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        log.info(browserToChoose+" browser opened successfully");
    }
    @AfterSuite
    public void tearDown() throws Exception {
        driver.quit();
    }
}