package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.FlightConfirmationPage;

import java.util.logging.Logger;

/**
 * Class.
 */
public class FlightConfirmationStep extends ScenarioSteps {

    private static Logger log = Logger.getLogger(BookAFlightStep.class.getName());
    private FlightConfirmationPage flightConfirmationPage;

    private String wayDepart;
    private String dateDepart;
    private String flightDepart;
    private String classDepart;
    private int priceDepart;
    private String wayReturn;
    private String dateReturn;
    private String flightReturn;
    private String classReturn;
    private int priceReturn;
    private int countPassengers;
    private int taxes;
    private int totalPrice;
    private String billedToFullName;
    private String billedToAddress;
    private String billedToCity;
    private String billedToStateProvince;
    private String billedToPostalCode;
    private String deliveryAddress;
    private String deliveryCity;
    private String deliveryStateProvince;
    private String deliveryPostalCode;

    /**
     *Method.
     */
    public String getWayDepart() {
        return wayDepart;
    }

    /**
     *Method.
     */
    public String getDateDepart() {
        return dateDepart;
    }

    /**
     *Method.
     */
    public String getFlightDepart() {
        return flightDepart;
    }

    /**
     *Method.
     */
    public String getClassDepart() {
        return classDepart;
    }

    /**
     *Method.
     */
    public int getPriceDepart() {
        return priceDepart;
    }

    /**
     *Method.
     */
    public String getWayReturn() {
        return wayReturn;
    }

    /**
     *Method.
     */
    public String getDateReturn() {
        return dateReturn;
    }

    /**
     *Method.
     */
    public String getFlightReturn() {
        return flightReturn;
    }

    /**
     *Method.
     */
    public String getClassReturn() {
        return classReturn;
    }

    /**
     *Method.
     */
    public int getPriceReturn() {
        return priceReturn;
    }

    /**
     *Method.
     */
    public int getCountPassengers() {
        return countPassengers;
    }

    /**
     *Method.
     */
    public int getTaxes() {
        return taxes;
    }

    /**
     *Method.
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     *Method.
     */
    public String getBilledToFullName() {
        return billedToFullName;
    }

    /**
     *Method.
     */
    public String getBilledToAddress() {
        return billedToAddress;
    }

    /**
     *Method.
     */
    public String getBilledToCity() {
        return billedToCity;
    }

    /**
     *Method.
     */
    public String getBilledToStateProvince() {
        return billedToStateProvince;
    }

    /**
     *Method.
     */
    public String getBilledToPostalCode() {
        return billedToPostalCode;
    }

    /**
     *Method.
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     *Method.
     */
    public String getDeliveryCity() {
        return deliveryCity;
    }

    /**
     *Method.
     */
    public String getDeliveryStateProvince() {
        return deliveryStateProvince;
    }

    /**
     *Method.
     */
    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    /**
     *Method.
     */
    @Step
    public void clickButtonBackToHome() {
        log.info("Click Back To Home Button");
        flightConfirmationPage.clickButtonBackToHome();
    }

    /**
     *Method.
     */
    public void initFields() {
        this.wayDepart = flightConfirmationPage.getWayDepart();
        this.dateDepart = flightConfirmationPage.getDateDepart();
        this.flightDepart = flightConfirmationPage.getFlightDepart();
        this.classDepart = flightConfirmationPage.getClassDepart();
        this.priceDepart = flightConfirmationPage.getPriceDepart();
        this.wayReturn = flightConfirmationPage.getWayReturn();
        this.dateReturn = flightConfirmationPage.getDateReturn();
        this.flightReturn = flightConfirmationPage.getFlightReturn();
        this.classReturn = flightConfirmationPage.getClassReturn();
        this.priceReturn = flightConfirmationPage.getPriceReturn();
        this.countPassengers = flightConfirmationPage.getCountPassengers();
        this.taxes = flightConfirmationPage.getTaxes();
        this.totalPrice = flightConfirmationPage.getTotalPrice();
        this.billedToFullName = flightConfirmationPage.getBilledToFullName();
        this.billedToAddress = flightConfirmationPage.getBilledToAddress();
        this.billedToCity = flightConfirmationPage.getBilledToCity();
        this.billedToStateProvince = flightConfirmationPage.getBilledToStateProvince();
        this.billedToPostalCode = flightConfirmationPage.getBilledToPostalCode();
        this.deliveryAddress = flightConfirmationPage.getDeliveryAddress();
        this.deliveryCity = flightConfirmationPage.getDeliveryCity();
        this.deliveryStateProvince = flightConfirmationPage.getDeliveryStateProvince();
        this.deliveryPostalCode = flightConfirmationPage.getDeliveryPostalCode();
    }
}
