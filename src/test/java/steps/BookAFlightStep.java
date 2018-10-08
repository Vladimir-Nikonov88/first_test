package steps;

import org.openqa.selenium.WebDriver;
import pages.BookAFlightPage;

import java.util.logging.Logger;

import static pages.BookAFlightPage.AddressType;

public class BookAFlightStep {

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

    public BookAFlightStep(final WebDriver driver) {
        this.bookAFlightPage = new BookAFlightPage(driver);
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

    public int getPassengers() {
        return passengers;
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

    public void setPassengers(final int number, final String firstName, final String lastName, final String meal) {
        log.info(String.format("Set Passenger Info (number: %s, first name: %s, last name: %s, meal: %s)",
                number, firstName, lastName, meal));
       bookAFlightPage.setPassengers(number, firstName, lastName, meal);
    }

    //Block Credit Card
    public void setCreditCard(final String type, final String number, final String month, final String year,
                              final String firstName, final String middleName, final String lastName) {

        log.info(String.format("Set Credit Card Info (type: %s, number: %s, month: %s, year: %s, "
                + "first name: %s, middle name: %s, last name: %s)",
                type, number, month, year, firstName, middleName, lastName));

        bookAFlightPage.setCreditCard(type, number, month, year, firstName, middleName, lastName);
        this.billedToFullName = firstName + " " + middleName + " " + lastName;
    }

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

    public void clickSecurePurchaseButton() {
        log.info("Click Secure Purchase Button");
        bookAFlightPage.clickSecurePurchase();
    }

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
