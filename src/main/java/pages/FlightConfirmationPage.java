package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class.
 */
public class FlightConfirmationPage extends Page {

    public static final String PAGE_NAME = "FLIGHT CONFIRMATION";
    public static final String TITLE = "Flight Confirmation: Mercury Tours";
    public static final int INDEX_OF_PRICE_DEPART = 3;
    public static final int INDEX_OF_PRICE_RETURN = 3;
    public static final int INDEX_OF_STRING_ADDRESS_INF = 3;

    @FindBy(xpath = "(//td[@class='frame_header_info'])[3]/child::*")
    private WebElement fontDepartingEl;

    @FindBy(xpath = "(//td[@class='frame_header_info'])[5]/child::*")
    private WebElement fontReturningEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[10]")
    private WebElement fontPassengersEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[12]")
    private WebElement fontBilledToEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[14]")
    private WebElement fontDeliveryEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[21]")
    private WebElement fontTaxesEl;

    @FindBy(xpath = "(//font[contains(text(),'Confirmation')]//ancestor::tbody[2]//child::font[1])[28]")
    private WebElement fontTotalPriceEl;

    public FlightConfirmationPage(final WebDriver driver) {
        super(driver);
    }

    /**
     *Method.
     */
    public String getWayDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[0];
    }

    /**
     *Method.
     */
    public String getDateDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[1].replaceAll(" @.*", "");
    }

    /**
     *Method.
     */
    public String getFlightDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[1].replaceAll(".*w\\/ ", "");
    }

    /**
     *Method.
     */
    public String getClassDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[2];
    }

    /**
     *Method.
     */
    public int getPriceDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return Integer.parseInt(listOfDepartElenets[INDEX_OF_PRICE_DEPART].replaceAll("[\\D]", ""));
    }

    /**
     *Method.
     */
    public String getWayReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[0];
    }

    /**
     *Method.
     */
    public String getDateReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[1].replaceAll(" @.*", "");
    }

    /**
     *Method.
     */
    public String getFlightReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[1].replaceAll(".*w\\/ ", "");
    }

    /**
     *Method.
     */
    public String getClassReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[2];
    }

    /**
     *Method.
     */
    public int getPriceReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return Integer.parseInt(listOfReturnElenets[INDEX_OF_PRICE_RETURN].replaceAll("[\\D]", ""));
    }

    /**
     *Method.
     */
    public int getCountPassengers() {
        return Integer.parseInt(fontPassengersEl.getText().replaceAll("[\\D]", ""));
    }

    /**
     *Method.
     */
    public int getTaxes() {
        return Integer.parseInt(fontTaxesEl.getText().replaceAll("[\\D]", ""));
    }

    /**
     *Method.
     */
    public int getTotalPrice() {
        return Integer.parseInt(fontTotalPriceEl.getText().replaceAll("[\\D]", ""));
    }

    /**
     *Method.
     */
    public String getBilledToFullName() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        return listOfBilledToElements[0];
    }

    /**
     *Method.
     */
    public String getBilledToAddress() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        return listOfBilledToElements[1];
    }

    /**
     *Method.
     */
    public String getBilledToCity() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        String[] splitBilledToAddress = listOfBilledToElements[INDEX_OF_STRING_ADDRESS_INF].split(", ");
        return splitBilledToAddress[0];
    }

    /**
     *Method.
     */
    public String getBilledToStateProvince() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        String[] splitBilledToAddress = listOfBilledToElements[INDEX_OF_STRING_ADDRESS_INF].split(", ");
        return splitBilledToAddress[1];
    }

    /**
     *Method.
     */
    public String getBilledToPostalCode() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        String[] splitBilledToAddress = listOfBilledToElements[INDEX_OF_STRING_ADDRESS_INF].split(", ");
        return splitBilledToAddress[2];
    }

    /**
     *Method.
     */
    public String getDeliveryAddress() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        return listOfDeliveryElements[0];
    }

    /**
     *Method.
     */
    public String getDeliveryCity() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        String[] splitDeliveryAddress = listOfDeliveryElements[2].split(", ");
        return splitDeliveryAddress[0];
    }

    /**
     *Method.
     */
    public String getDeliveryStateProvince() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        String[] splitDeliveryAddress = listOfDeliveryElements[2].split(", ");
        return splitDeliveryAddress[1];
    }

    /**
     *Method.
     */
    public String getDeliveryPostalCode() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        String[] splitDeliveryAddress = listOfDeliveryElements[2].split(", ");
        return splitDeliveryAddress[2];
    }

    /**
     *Method.
     */
    public void clickButtonBackToHome() {
        WebElement bottonBackToHomeElement = getDriver().findElement(By.xpath("//img[@src='/images/forms/home.gif']"));
        bottonBackToHomeElement.click();
    }
}
