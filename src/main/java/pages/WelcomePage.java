package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class.
 */
public class WelcomePage extends Page {

    public static final String PAGE_NAME = "WELCOME";
    public static final String TITLE = "Welcome: Mercury Tours";

    @FindBy(xpath = "//input [@type='text' and @name='userName']")
    private WebElement loginField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input [@type='image' and @name='login']")
    private WebElement signInImage;

    public WelcomePage(final WebDriver driver) {
        super(driver);
    }

    /**
     *Method.
     */
    public void singIn(final String login, final String password) {
        inputLogin("tutorial");
        inputPassword("tutorial");
        clickSignInImage();
    }

    /**
     *Method.
     */
    public void inputLogin(final String login) {
        loginField.sendKeys(login);
    }

    /**
     *Method.
     */
    public void inputPassword(final String password) {
        passwordField.sendKeys(password);
    }

    /**
     *Method.
     */
    public void clickSignInImage() {
        signInImage.click();
    }

}
