import base.IntializeBrowser;
import base.ConfigFileReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.SauceDemoLoginPage;
import pageobjects.SauceDemoHomePage;
import pageobjects.SauceDemoYourCartPage;
import java.util.ArrayList;

public class validateItemsAddedToCart extends IntializeBrowser {
    ConfigFileReader cfr;
    String username;
    String pwd;
    SauceDemoHomePage homepage;
    SauceDemoLoginPage login;
    SauceDemoYourCartPage cartPage;
    ArrayList<String> itemsAddedToCard;
    ArrayList<String> itemsAddedToCardAfterSelection;
    String[] listToBeAdded = {"Sauce Labs Onesie","Sauce Labs Bike Light"};

    @BeforeClass
    public void setup() {
        homepage = new SauceDemoHomePage(driver);
        cartPage = new SauceDemoYourCartPage(driver);
        ConfigFileReader cfr = new ConfigFileReader();
        username = cfr.getAppUsername();
        pwd = cfr.getAppPassword();
        login = new SauceDemoLoginPage(driver);

        //Check if page is opened
        Assert.assertTrue(login.isPageOpened());
        //Enter Username and Password
        login.set_email(username);
        login.set_password(pwd);

        //Click on Login Button
        login.clickLoginButton();
    }

    //Test Case to add specific given no of items to the cart and verify whether items added to the cart
    @Test
    public void addRequiredItemsToCard(){
        homepage.isPageOpened();
        ArrayList<String> specCartValues = homepage.addSpecificItemsToCart(listToBeAdded);
        homepage.clickShoppingCart();
        cartPage.isPageOpened();
        int noOfItemsNeedtobeAdded = listToBeAdded.length;
        itemsAddedToCardAfterSelection = cartPage.getItemsAddedToCard(noOfItemsNeedtobeAdded);
        Assert.assertTrue(cartPage.isItemsAddedToCard(specCartValues, itemsAddedToCardAfterSelection));
        cartPage.clickOnOpenMenu();
        cartPage.clickLogoutButton();
    }
}
