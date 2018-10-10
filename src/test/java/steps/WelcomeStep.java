package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.WelcomePage;

import java.util.logging.Logger;

/**
 * Class.
 */
public class WelcomeStep extends ScenarioSteps {

    private static Logger log = Logger.getLogger(WelcomeStep.class.getName());
    private WelcomePage welcomePage;

    /**
     *Method.
     */
    @Step
    public void inputLogin(final String login) {
        log.info("Set login: " + login);
        welcomePage.inputLogin(login);
    }

    /**
     *Method.
     */
    @Step
    public void inputPassword(final String password) {
        log.info("Set password: " + password);
        welcomePage.inputPassword(password);
    }

    /**
     *Method.
     */
    @Step
    public void clickSignInImage() {
        log.info("Click SignIn Button");
        welcomePage.clickSignInImage();
    }

}
