package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class.
 */
public abstract class Page {

    private WebDriver driver;


    public Page(final WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Method.
     * @return
     */
    protected WebDriver getDriver() {
        return this.driver;
    }

    /**
     * Method.
     * @return
     */
    protected void wait(final WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 2);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                return element.isDisplayed();
            }
        });
    }

}
