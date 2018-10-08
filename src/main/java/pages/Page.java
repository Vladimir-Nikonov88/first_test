package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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

}
