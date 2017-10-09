package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
    private WebDriver webDriver;

    @FindBy(id="BidButtonTop")
    private WebElement buyButton;


    public boolean isProductPageDisplayed() {
        return this.buyButton.isDisplayed();
    }
}
