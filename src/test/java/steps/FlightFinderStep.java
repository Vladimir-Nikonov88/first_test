package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.FlightFinderPage;

import java.util.logging.Logger;

import static pages.FlightFinderPage.ServiceClass;
import static pages.FlightFinderPage.Type;

/**
 * Class.
 */
public class FlightFinderStep extends ScenarioSteps {

    private static Logger log = Logger.getLogger(FlightFinderStep.class.getName());
    private FlightFinderPage flightFinderPage;
    private int countPassengers;

    /**
     *Method.
     */
    public int getCountPassengers() {
        return countPassengers;
    }

    /**
     *Method.
     */
    @Step
    public void setType(final Type type) {
        log.info("Set type: " + type);
        flightFinderPage.setType(type);
    }

    /**
     *Method.
     */
    @Step
    public void setPassengers(final int count) {
        log.info("Set count passengers: " + count);
        flightFinderPage.setPassengers(count);
        this.countPassengers = count;
    }

    /**
     *Method.
     */
    @Step
    public void setDepartingFrom(final String city) {
        log.info("Set Departing From city: " + city);
        flightFinderPage.setDepartingFrom(city);
    }

    /**
     *Method.
     */
    @Step
    public void setOn(final String month, final String day) {
        log.info(String.format("Set on month: %s; Set on day: %s", month, day));
        flightFinderPage.setOn(month, day);
    }

    /**
     *Method.
     */
    @Step
    public void setArrivingIn(final String city) {
        log.info("Set Arriving in city: " + city);
        flightFinderPage.setArrivingIn(city);
    }

    /**
     *Method.
     */
    @Step
    public void setReturning(final String month, final String day) {
        log.info(String.format("Set returning month: %s; Set on day: %s", month, day));
        flightFinderPage.setReturning(month, day);
    }

    /**
     *Method.
     */
    @Step
    public void setServiceClass(final ServiceClass serviceClass) {
        log.info("Set service class: " + serviceClass);
        flightFinderPage.setServiceClass(serviceClass);
    }

    /**
     *Method.
     */
    @Step
    public void setAirline(final String airline) {
        log.info("Set Airline: " + airline);
        flightFinderPage.setAirline(airline);
    }

    /**
     *Method.
     */
    @Step
    public void clickContinueButton() {
        log.info("Click Continue Button");
        flightFinderPage.clickContinueImage();
    }


}
