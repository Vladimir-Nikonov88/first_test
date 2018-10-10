package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import pages.BookAFlightPage;

import java.util.logging.Logger;

import static pages.BookAFlightPage.AddressType;

/**
 * Class.
 */
public class BookAFlightStep extends ScenarioSteps {

    private static Logger log = Logger.getLogger(BookAFlightStep.class.getName());
    private BookAFlightPage bookAFlightPage;

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

    private int passengers;
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

    public BookAFlightStep(final Pages pages) {
            super(pages);
    }

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
    public int getPassengers() {
        return passengers;
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
    public void setPassengers(final int number, final String firstName, final String lastName, final String meal) {
        log.info(String.format("Set Passenger Info (number: %s, first name: %s, last name: %s, meal: %s)",
                number, firstName, lastName, meal));
       bookAFlightPage.setPassengers(number, firstName, lastName, meal);
    }


    /**
     *Method.
     */
    @Step
    public void setCreditCard(final String type, final String number, final String month, final String year,
                              final String firstName, final String middleName, final String lastName) {

        log.info(String.format("Set Credit Card Info (type: %s, number: %s, month: %s, year: %s, "
                + "first name: %s, middle name: %s, last name: %s)",
                type, number, month, year, firstName, middleName, lastName));

        bookAFlightPage.setCreditCard(type, number, month, year, firstName, middleName, lastName);
        this.billedToFullName = firstName + " " + middleName + " " + lastName;
    }

    /**
     *Method.
     */
    @Step
    public void setAddress(final boolean checkBox, final String address, final String city, final String stateProvince,
                           final String postalCode, final String country, final AddressType addressType) {
        log.info(String.format("Set %s Address (address: %s, city: %s, State/Province: %s, Postal Code: %s,"
                        + " country: %s)",
                addressType, address, city, stateProvince, postalCode, country));
        bookAFlightPage.setAddress(checkBox, address, city, stateProvince, postalCode, country, addressType);
        switch (addressType) {
            case Billing:
                this.billedToAddress = address;
                this.billedToCity = city;
                this.billedToStateProvince = stateProvince;
                this.billedToPostalCode = postalCode;
                break;
            case Delivery:
                this.deliveryAddress = address;
                this.deliveryCity = city;
                this.deliveryStateProvince = stateProvince;
                this.deliveryPostalCode = postalCode;
                break;
            default:
                throw new IllegalArgumentException("Unknown addressType");
        }
    }

    /**
     *Method.
     */
    public void clickSecurePurchaseButton() {
        log.info("Click Secure Purchase Button");
        bookAFlightPage.clickSecurePurchase();
    }

    /**
     *Method.
     */
    public void initFields() {
        this.wayDepart = bookAFlightPage.getWayDepart();
        this.dateDepart = bookAFlightPage.getDateDepart();
        this.flightDepart = bookAFlightPage.getFlightDepart();
        this.classDepart = bookAFlightPage.getClassDepart();
        this.priceDepart = bookAFlightPage.getPriceDepart();
        this.wayReturn = bookAFlightPage.getWayReturn();
        this.dateReturn = bookAFlightPage.getDateReturn();
        this.flightReturn = bookAFlightPage.getFlightReturn();
        this.classReturn = bookAFlightPage.getClassReturn();
        this.priceReturn = bookAFlightPage.getPriceReturn();
        this.passengers = bookAFlightPage.getPassengers();
        this.taxes = bookAFlightPage.getTaxes();
        this.totalPrice = bookAFlightPage.getTotalPrice();
    }
}
