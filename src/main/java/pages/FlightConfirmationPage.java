package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightConfirmationPage extends Page {

    public static final String PAGE_NAME = "FLIGHT CONFIRMATION";
    public static final String TITLE = "Flight Confirmation: Mercury Tours";

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

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getWayDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[0];
    }

    public String getDateDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[1].replaceAll(" @.*", "");
    }

    public String getFlightDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[1].replaceAll(".*w\\/ ", "");
    }

    public String getClassDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return listOfDepartElenets[2];
    }

    public int getPriceDepart() {
        String[] listOfDepartElenets = fontDepartingEl.getText().split("\n");
        return Integer.parseInt(listOfDepartElenets[3].replaceAll("[\\D]", ""));
    }

    public String getWayReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[0];
    }

    public String getDateReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[1].replaceAll(" @.*", "");
    }

    public String getFlightReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[1].replaceAll(".*w\\/ ", "");
    }

    public String getClassReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return listOfReturnElenets[2];
    }

    public int getPriceReturn() {
        String[] listOfReturnElenets = fontReturningEl.getText().split("\n");
        return Integer.parseInt(listOfReturnElenets[3].replaceAll("[\\D]", ""));
    }

    public int getCountPassengers() {
        return Integer.parseInt(fontPassengersEl.getText().replaceAll("[\\D]", ""));
    }

    public int getTaxes() {
        return Integer.parseInt(fontTaxesEl.getText().replaceAll("[\\D]", ""));
    }

    public int getTotalPrice() {
        return Integer.parseInt(fontTotalPriceEl.getText().replaceAll("[\\D]", ""));
    }

    public String getBilledToFullName() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        return listOfBilledToElements[0];
    }

    public String getBilledToAddress() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        return listOfBilledToElements[1];
    }

    public String getBilledToCity() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        String[] splitBilledToAddress = listOfBilledToElements[3].split(", ");
        return splitBilledToAddress[0];
    }

    public String getBilledToStateProvince() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        String[] splitBilledToAddress = listOfBilledToElements[3].split(", ");
        return splitBilledToAddress[1];
    }

    public String getBilledToPostalCode() {
        String[] listOfBilledToElements = fontBilledToEl.getText().split("\n");
        String[] splitBilledToAddress = listOfBilledToElements[3].split(", ");
        return splitBilledToAddress[2];
    }

    public String getDeliveryAddress() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        return listOfDeliveryElements[0];
    }

    public String getDeliveryCity() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        String[] splitDeliveryAddress = listOfDeliveryElements[2].split(", ");
        return splitDeliveryAddress[0];
    }

    public String getDeliveryStateProvince() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        String[] splitDeliveryAddress = listOfDeliveryElements[2].split(", ");
        return splitDeliveryAddress[1];
    }

    public String getDeliveryPostalCode() {
        String[] listOfDeliveryElements = fontDeliveryEl.getText().split("\n");
        String[] splitDeliveryAddress = listOfDeliveryElements[2].split(", ");
        return splitDeliveryAddress[2];
    }

    public void clickButtonBackToHome() {
        WebElement bottonBackToHomeElement = driver.findElement(By.xpath("//img[@src='/images/forms/home.gif']"));
        bottonBackToHomeElement.click();
    }
}
