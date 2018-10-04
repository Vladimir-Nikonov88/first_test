package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FlightConfirmationPage extends Page {

    public static final String PAGE_NAME = "FLIGHT CONFIRMATION";
    public static final String TITLE = "Flight Confirmation: Mercury Tours";

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

    @FindBy(xpath = "(//td[@class='frame_header_info'])[3]/child::*")
    WebElement fontDepartingEl;

    @FindBy(xpath = "(//td[@class='frame_header_info'])[5]/child::*")
    WebElement fontReturningEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[10]")
    WebElement fontPassengersEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[12]")
    WebElement fontBilledToEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[14]")
    WebElement fontDeliveryEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[21]")
    WebElement fontTaxesEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[28]")
    WebElement fontTotalPriceEl;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
        initFields();
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

    private void initFields() {

        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        String[] listOfBilledToElenets = fontBilledToEl.getText().split("\n");
        String[] listOfDeliveryElenets = fontDeliveryEl.getText().split("\n");

        String[] splitBilledToAddress = listOfBilledToElenets[3].split(", ");
        String[] splitDeliveryAddress = listOfDeliveryElenets[2].split(", ");

        this.wayDepart = listOfDepartElenets[0];
        this.dateDepart = listOfDepartElenets[1].replaceAll(" @.*","");
        this.flightDepart = listOfDepartElenets[1].replaceAll(".*w\\/ ","");
        this.classDepart = listOfDepartElenets[2];
        this.priceDepart = Integer.parseInt(listOfDepartElenets[3].replaceAll("[\\D]",""));
        this.wayReturn = listOfReturnElenets[0];
        this.dateReturn = listOfReturnElenets[1].replaceAll(" @.*","");
        this.flightReturn = listOfReturnElenets[1].replaceAll(".*w\\/ ","");
        this.classReturn = listOfReturnElenets[2];
        this.priceReturn = Integer.parseInt(listOfReturnElenets[3].replaceAll("[\\D]",""));
        this.passengers = Integer.parseInt(fontPassengersEl.getText().replaceAll("[\\D]",""));
        this.taxes = Integer.parseInt(fontTaxesEl.getText().replaceAll("[\\D]",""));
        this.totalPrice = Integer.parseInt(fontTotalPriceEl.getText().replaceAll("[\\D]",""));
        this.billedToFullName = listOfBilledToElenets[0];
        this.billedToAddress = listOfBilledToElenets[1];
        this.billedToCity = splitBilledToAddress[0];
        this.billedToStateProvince = splitBilledToAddress[1];
        this.billedToPostalCode = splitBilledToAddress[2];

        this.deliveryAddress = listOfDeliveryElenets[0];
        this.deliveryCity = splitDeliveryAddress[0];
        this.deliveryStateProvince = splitDeliveryAddress[1];
        this.deliveryPostalCode = splitDeliveryAddress[2];
    }

    public void clickBottonBackToHome() {
        WebElement bottonBackToHomeElement = driver.findElement(By.xpath("//img[@src='/images/forms/home.gif']"));
        bottonBackToHomeElement.click();
    }
}
