package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.logging.Logger;

/**
 * Class.
 */
public class SupportSteps extends ScenarioSteps {

    private static Logger log = Logger.getLogger(WelcomeStep.class.getName());

    @Step
    public static void checkValuePage(final String namePage,
                                final String nameVariable,
                                final Object expected,
                                final Object actual) {
        String mark = "";
        if (expected instanceof String && actual instanceof String) {
            mark = "%s";
        } else if (expected instanceof Integer && actual instanceof Integer) {
            mark = "%d";
        } else {
            throw new IllegalArgumentException();
        }
        if (expected.equals(actual)) {
            log.info(String.format("CORRECT value Page: %s, name: %s \texpected = " + mark + ", actual = "
                    + mark, namePage, nameVariable, expected, actual));
        } else {
            log.info(String.format("INCORRECT!!! value Page: %s, name: %s \texpected = " + mark + ", actual = "
                    + mark, namePage, nameVariable, expected, actual));
            // Assert.fail();
        }
    }
}