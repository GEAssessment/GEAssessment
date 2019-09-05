package pageobjects;

import base.IntializeBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;

public class SauceDemoHomePage extends IntializeBrowser {

    private WebDriver driver;
    ArrayList<String> cartValues = new ArrayList<String>();
    ArrayList<String> allItemNames = new ArrayList<String>();
    String productName;

    @FindBy(className = "product_label")
    WebElement productLabel;

    @FindBy(id="shopping_cart_container")
    WebElement shoppingCart;

    public SauceDemoHomePage(WebDriver driver){
        this.driver=driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(){
        return productLabel.getText().contains("Products");
    }

    public ArrayList<String> getAllItemNames() {
        //Get All the items name and add to arratlist
        int allItemNamesSize = driver.findElements(By.xpath("//div[@class='inventory_list']/div//div[@class='inventory_item_name']")).size();
        for (int k = 1; k <=allItemNamesSize; k++) {
            productName = driver.findElement(By.xpath("//div[@class='inventory_list']/div[" + k + "]//div[@class='inventory_item_name']")).getText();
            allItemNames.add(productName);
        }
        return allItemNames;
    }

    public ArrayList<String> addSpecificItemsToCart(String itemsToBeAdd[]){
        ArrayList<String> itemNames = getAllItemNames();
        boolean flag = true;
        for(int j=0;j<itemsToBeAdd.length;j++) {
            if(!itemNames.contains(itemsToBeAdd[j])){
                flag = false;
            }
        }
        Assert.assertTrue(flag);
        int itemsList = driver.findElements(By.xpath("//div[@class='inventory_list']/div")).size();

        for(int k=0;k<itemsToBeAdd.length;k++) {
            for(int l=1;l<=itemsList;l++){
                productName = driver.findElement(By.xpath("//div[@class='inventory_list']/div["+l+"]//div[@class='inventory_item_name']")).getText();
                if(productName.equals(itemsToBeAdd[k])) {
                    String itemPrice = driver.findElement(By.xpath("//div[@class='inventory_list']/div["+l+"]//div[@class='inventory_item_price']")).getText().split("\\$")[1];
                    cartValues.add(productName);
                    cartValues.add(itemPrice);
                    driver.findElement(By.xpath("//div[@class='inventory_list']/div["+l+"]//button[@class='btn_primary btn_inventory']")).click();
                }
            }
        }
        return cartValues;
    }

    public void clickShoppingCart(){
        shoppingCart.click();
    }
}
