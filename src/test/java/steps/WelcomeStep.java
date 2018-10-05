package steps;


import org.openqa.selenium.WebDriver;
import pages.WelcomePage;

import java.util.logging.Logger;

public class WelcomeStep {

    private static Logger log = Logger.getLogger(WelcomeStep.class.getName());
    private WelcomePage welcomePage;

    public WelcomeStep(final WebDriver driver) {
        this.welcomePage = new WelcomePage(driver);
    }

    public void inputLogin(final String login) {
        log.info("Set login: " + login);
        welcomePage.inputLogin(login);
    }

    public void inputPassword(final String password) {
        log.info("Set password: " + password);
        welcomePage.inputPassword(password);
    }

    public void clickSignInImage() {
        log.info("Click SignIn Button");
        welcomePage.clickSignInImage();
    }

}
