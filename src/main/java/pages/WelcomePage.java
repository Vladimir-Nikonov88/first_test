package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends Page {

    public static final String PAGE_NAME = "WELCOME";
    public static final String TITLE = "Welcome: Mercury Tours";

    @FindBy(xpath = "//input [@type='text' and @name='userName']")
    private WebElement loginField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input [@type='image' and @name='login']")
    private WebElement signInImage;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public void singIn(String login, String password) {
        inputLogin("tutorial");
        inputPassword("tutorial");
        clickSignInImage();
    }

    private void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    private void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    private void clickSignInImage() {
        signInImage.click();
    }

    public FlightFinderPage initFlightFinderPage() {
        if (FlightFinderPage.TITLE.equals(this.driver.getTitle())) {
            return new FlightFinderPage(this.driver);
        } else {
            throw new IllegalArgumentException("WebDriver is not correspond page "+ FlightFinderPage.PAGE_NAME);
        }
    }

}
