package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;

public class SauceDemoYourCartPage {

    private WebDriver driver;

    ArrayList<String> cartAddedValues = new ArrayList<String>();

    @FindBy(className = "subheader")
    WebElement YourCardHeader;

    @FindBy(id="shopping_cart_container")
    WebElement shoppingCart;

    @FindBy(xpath="//button[contains(text(),'Open Menu')]")
    WebElement openMenu;

    @FindBy(xpath="//a[@id='logout_sidebar_link']")
    WebElement logOutBtn;

    public SauceDemoYourCartPage(WebDriver driver){
        this.driver=driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return YourCardHeader.getText().contains("Products");
    }

    public ArrayList<String> getItemsAddedToCard(int noOfItems){
        int cartList = driver.findElements(By.xpath("//div[@class='cart_item']")).size();
        if(noOfItems==cartList){
            for(int i=1;i<=cartList;i++){
                String productName = driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_name']")).getText();
                String itemPrice = driver.findElement(By.xpath("//div[@class='cart_item']["+i+"]//div[@class='inventory_item_price']")).getText();
                cartAddedValues.add(productName);
                cartAddedValues.add(itemPrice);
            }
        }
        return cartAddedValues;
    }

    public boolean isItemsAddedToCard(ArrayList<String> cartAddedValues , ArrayList<String> itemsAddedToCard) {
        boolean check = true;
        for(int j=0;j<cartAddedValues.size();j++) {
            if(cartAddedValues.contains(itemsAddedToCard.get(j))){
                continue;
            }else{
                check=false;
            }
        }
        return check;
    }


    public void clickShoppingCart(){
        shoppingCart.click();
    }
    public void clickOnOpenMenu(){
        openMenu.click();
    }
    public void clickLogoutButton(){
        logOutBtn.click();
    }
}
