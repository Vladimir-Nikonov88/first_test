package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    public WebDriver driver;

    public Page (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
