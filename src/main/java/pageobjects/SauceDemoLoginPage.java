package pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.IntializeBrowser;

public class SauceDemoLoginPage extends IntializeBrowser {

    @FindBy(id="user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement loginPassword;

    @FindBy(xpath = "//input[@value='LOGIN']")
    WebElement submitBtn;

    public SauceDemoLoginPage(WebDriver driver){
        this.driver=driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void set_email(String email){
        userName.clear();
        userName.sendKeys(email);
    }

    public void set_password(String password){
        loginPassword.clear();
        loginPassword.sendKeys(password);
    }

    public void  clickLoginButton(){
        submitBtn.click();
    }

    public boolean isPageOpened(){
        return driver.getTitle().toString().contains("Swag Labs");
    }
}
