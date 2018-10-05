package steps;

import org.openqa.selenium.WebDriver;
import pages.FlightFinderPage;

import java.util.logging.Logger;

import static pages.FlightFinderPage.ServiceClass;
import static pages.FlightFinderPage.Type;

public class FlightFinderStep {

    private static Logger log = Logger.getLogger(FlightFinderStep.class.getName());
    private FlightFinderPage flightFinderPage;
    private int countPassengers;

    public FlightFinderStep(final WebDriver driver) {
        this.flightFinderPage = new FlightFinderPage(driver);
    }

    public int getCountPassengers() {
        return countPassengers;
    }

    public void setType(final Type type) {
        log.info("Set type: " + type);
        flightFinderPage.setType(type);
    }

    public void setPassengers(final int count) {
        log.info("Set count passengers: " + count);
        flightFinderPage.setPassengers(count);
        this.countPassengers = count;
    }

    public void setDepartingFrom(final String city) {
        log.info("Set Departing From city: " + city);
        flightFinderPage.setDepartingFrom(city);
    }

    public void setOn(final String month, final String day) {
        log.info(String.format("Set on month: %s; Set on day: %s", month, day));
        flightFinderPage.setOn(month, day);
    }

    public void setArrivingIn(final String city) {
        log.info("Set Arriving in city: " + city);
        flightFinderPage.setArrivingIn(city);
    }

    public void setReturning(final String month, final String day) {
        log.info(String.format("Set returning month: %s; Set on day: %s", month, day));
        flightFinderPage.setReturning(month, day);
    }

    public void setServiceClass(final ServiceClass serviceClass) {
        log.info("Set service class: " + serviceClass);
        flightFinderPage.setServiceClass(serviceClass);
    }

    public void setAirline(final String airline) {
        log.info("Set Airline: " + airline);
        flightFinderPage.setAirline(airline);
    }

    public void clickContinueButton() {
        log.info("Click Continue Button");
        flightFinderPage.clickContinueImage();
    }


}
