package steps;

import org.openqa.selenium.WebDriver;
import pages.FlightConfirmationPage;

import java.util.logging.Logger;

public class FlightConfirmationStep {

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

    public FlightConfirmationStep(final WebDriver driver) {
        this.flightConfirmationPage = new FlightConfirmationPage(driver);
    }

    public String getWayDepart() {
        return wayDepart;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public String getFlightDepart() {
        return flightDepart;
    }

    public String getClassDepart() {
        return classDepart;
    }

    public int getPriceDepart() {
        return priceDepart;
    }

    public String getWayReturn() {
        return wayReturn;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public String getFlightReturn() {
        return flightReturn;
    }

    public String getClassReturn() {
        return classReturn;
    }

    public int getPriceReturn() {
        return priceReturn;
    }

    public int getCountPassengers() {
        return countPassengers;
    }

    public int getTaxes() {
        return taxes;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getBilledToFullName() {
        return billedToFullName;
    }

    public String getBilledToAddress() {
        return billedToAddress;
    }

    public String getBilledToCity() {
        return billedToCity;
    }

    public String getBilledToStateProvince() {
        return billedToStateProvince;
    }

    public String getBilledToPostalCode() {
        return billedToPostalCode;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public String getDeliveryStateProvince() {
        return deliveryStateProvince;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public void clickButtonBackToHome() {
        log.info("Click Back To Home Button");
        flightConfirmationPage.clickButtonBackToHome();
    }

    public void initFields(){
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
