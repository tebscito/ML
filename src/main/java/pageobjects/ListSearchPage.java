package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListSearchPage {
    private WebDriver webDriver;
    private boolean listSearchPageDisplayed;

    @FindBy(name="as_word")
    private WebElement searchBox;

    @FindBy(xpath="//SPAN[@class='main-title'][text()=' Celular Liberado Iphone 6 32 Gb Space Grey ']")
    private WebElement product;

    public void clickFirstProduct(){
        this.product.click();
    }
    public boolean isListSearchPageDisplayed() {
        return this.searchBox.isDisplayed();
    }
}
