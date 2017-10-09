package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver webDriver;
    private boolean mainPageDisplayed;

    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(name="as_word")
    private WebElement searchBox;

    @FindBy(xpath = "//I[@class='nav-icon-search']")
    private WebElement searchButton;

    public void enterSearch(String search){
        this.searchBox.sendKeys(search);
    }
    public void clickSearchButton(){
        this.searchButton.click();
    }

    public boolean isMainPageDisplayed() {
        return this.searchBox.isDisplayed();
    }
}
