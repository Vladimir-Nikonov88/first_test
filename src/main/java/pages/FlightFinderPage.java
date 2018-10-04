package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FlightFinderPage extends Page{

    public static final String PAGE_NAME = "FLIGHT FINDER";
    public static final String TITLE = "Find a Flight: Mercury Tours:";

    private int countPassengers;

    public enum Type {
        Round_Trip,
        One_Way
    }

    public enum ServiceClass {
        EconomyClass,
        BusinessClass,
        FirstClass
    }

    @FindBy(xpath = "//input [@name = 'tripType' and @value = 'oneway']")
    private WebElement radioButtonType;

    @FindBy(xpath = "//select [@name = 'passCount']")
    private WebElement selectElementPassengers;

    @FindBy(xpath = "//select [@name = 'fromPort']")
    private WebElement selectDepartingFrom;

    @FindBy(xpath = "//select [@name = 'fromMonth']")
    private WebElement selectOnMonth;

    @FindBy(xpath = "//select [@name = 'fromDay']")
    private WebElement selectOnDay;

    @FindBy(xpath = "//select [@name = 'toPort']")
    private WebElement selectArrivingIn;

    @FindBy(xpath = "//select [@name = 'toMonth']")
    private WebElement selectReturningToMonth;

    @FindBy(xpath = "//select [@name = 'toDay']")
    private WebElement selectReturningToDay;

    @FindBy(xpath = "//select [@name = 'airline']")
    private WebElement selectAirline;

    @FindBy(xpath = "//input [@name = 'findFlights']")
    private WebElement imageContinue;

    public FlightFinderPage(WebDriver driver) {
        super(driver);
    }

    public int getCountPassengers() {
        return countPassengers;
    }

    public void setType (Type type) {
        String xpath;
        switch (type) {
            case Round_Trip:
                xpath = "//input [@name = 'tripType' and @value = 'roundtrip']";
                break;
            case One_Way:
                xpath = "//input [@name = 'tripType' and @value = 'oneway']";
                break;
                default:
                    throw new IllegalArgumentException();
        }
        WebElement radioButton = driver.findElement(By.xpath(xpath));
        radioButton.click();
    }

    public void setPassengers (int count) {
        Select selectPassengers = new Select(selectElementPassengers);
        selectPassengers.selectByValue(String.valueOf(count));
        this.countPassengers = count;
    }

    public void setDepartingFrom (String city) {
        Select selectPassengers = new Select(selectDepartingFrom);
        selectPassengers.selectByValue(city);
    }

    public void setOn (String month, String day) {
        Select selectOnMonths = new Select(selectOnMonth);
        Select selectOnDays = new Select(selectOnDay);
        selectOnMonths.selectByVisibleText(month);
        selectOnDays.selectByValue(day);
    }

    public void setArrivingIn (String city) {
        Select selectArriving = new Select(selectArrivingIn);
        selectArriving.selectByValue(city);
    }

    public void setReturning (String month, String day) {
        Select selectToMonths = new Select(selectReturningToMonth);
        Select selectToDays = new Select(selectReturningToDay);
        selectToMonths.selectByVisibleText(month);
        selectToDays.selectByValue(day);
    }

    public void setServiceClass (ServiceClass serviceClass) {
        String xpath;
        switch (serviceClass) {
            case EconomyClass:
                xpath = "//input [@name = 'servClass' and @value = 'Coach']";
                break;
            case BusinessClass:
                xpath = "//input [@name = 'servClass' and @value = 'Business']";
                break;
            case FirstClass:
                xpath = "//input [@name = 'servClass' and @value = 'First']";
                break;
            default:
                throw new IllegalArgumentException();
        }
        WebElement radioButton = driver.findElement(By.xpath(xpath));
        radioButton.click();
    }

    public void setAirline (String airline) {
        Select selectAirlines = new Select(selectAirline);
        selectAirlines.selectByVisibleText(airline);
    }

    public void clickContinueImage() {
        imageContinue.click();
    }

    public SelectFlightPage initSelectFlightPage() {
        if (SelectFlightPage.TITLE.equals(this.driver.getTitle())) {
            return new SelectFlightPage(this.driver);
        } else {
            throw new IllegalArgumentException("WebDriver is not correspond page "+ SelectFlightPage.PAGE_NAME);
        }
    }

}
