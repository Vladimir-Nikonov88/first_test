package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BookAFlightPage extends Page {

    public static final String PAGE_NAME = "BOOK A FLIGHT";
    public static final String TITLE = "Book a Flight: Mercury Tours";

    public enum AddressType {Billing, Delivery}

    //Summary
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

    private String billedToFirstName;
    private String billedToMiddleName;
    private String billedToLastName;
    private String billedToFullName;
    private String billedToAddress;
    private String billedToCity;
    private String billedToStateProvince;
    private String billedToPostalCode;

    private String deliveryAddress;
    private String deliveryCity;
    private String deliveryStateProvince;
    private String deliveryPostalCode;


    //Summary

    @FindBy(xpath = "((//font[text()='FLIGHT']//parent::*//parent::*//parent::*)[1]/*)[1]//font")
    private List<WebElement> listOfWayDatDepart;
    @FindBy(xpath = "((//font[text()='FLIGHT']//parent::*//parent::*//parent::*)[1]/*)[3]//font")
    private List<WebElement> listOfFlightInfoDepart;

    @FindBy(xpath = "((//font[text()='FLIGHT']//parent::*//parent::*//parent::*)[1]/*)[4]//font")
    private List<WebElement> listOfWayDateReturn;

    @FindBy(xpath = "((//font[text()='FLIGHT']//parent::*//parent::*//parent::*)[1]/*)[6]//font")
    private List<WebElement> listOfFlightInfoReturn;

    @FindBy(xpath = "(((//font[text()='FLIGHT']//parent::*//parent::*//parent::*)[1]/*)[7]//font)[2]")
    private WebElement passengersElem;

    @FindBy(xpath = "(((//font[text()='FLIGHT']//parent::*//parent::*//parent::*)[1]/*)[8]//font)[2]")
    private WebElement taxesElem;

    @FindBy(xpath = "(((//font[text()='FLIGHT']//parent::*//parent::*//parent::*)[1]/*)[9]//font)[2]")
    private WebElement totalPriceElem;

    //Block Passengers

    @FindBy(xpath = "//input[@name = 'passFirst0']")
    private WebElement firstNamePassOneEl;
    public String getWayDepart() {
        return wayDepart;
    }

    public BookAFlightPage(WebDriver driver) {
        super(driver);
        initFields();
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
    public String getBilledToFirstName() {
        return billedToFirstName;
    }
    public String getBilledToMiddleName() {
        return billedToMiddleName;
    }
    public String getBilledToLastName() {
        return billedToLastName;
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

    public void setPassengers(int number, String firstName, String lastName, String meal) {
        number--;
        WebElement inputFirstName = driver.findElement(By.xpath("//input[@name = 'passFirst" + number + "']"));
        WebElement inputLastName = driver.findElement(By.xpath("//input[@name = 'passLast" + number + "']"));
        WebElement selectMealEl = driver.findElement(By.xpath("//select [@name = 'pass." + number + ".meal']"));

        Select selectMeal = new Select(selectMealEl);

        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        selectMeal.selectByVisibleText(meal);

    }

    //Block Credit Card
    public void setCreditCard(String type, String number, String month, String year,
                              String firstName, String middleName, String lastName) {
        WebElement selectTypeEl = driver.findElement(By.xpath("//select [@name = 'creditCard']"));
        WebElement inputNumber = driver.findElement(By.xpath("//input[@name='creditnumber']"));
        WebElement selectMonthEl = driver.findElement(By.xpath("//select [@name = 'cc_exp_dt_mn']"));
        WebElement selectYearEl = driver.findElement(By.xpath("//select [@name = 'cc_exp_dt_yr']"));
        WebElement inputFirstName = driver.findElement(By.xpath("//input[@name='cc_frst_name']"));
        WebElement inputMiddleName = driver.findElement(By.xpath("//input[@name='cc_mid_name']"));
        WebElement inputLastName = driver.findElement(By.xpath("//input[@name='cc_last_name']"));

        Select selectType = new Select(selectTypeEl);
        Select selectMonth = new Select(selectMonthEl);
        Select selectYear = new Select(selectYearEl);

        selectType.selectByVisibleText(type);
        inputNumber.sendKeys(number);
        selectMonth.selectByVisibleText(month);
        selectYear.selectByValue(year);
        inputFirstName.sendKeys(firstName);
        inputMiddleName.sendKeys(middleName);
        inputLastName.sendKeys(lastName);

        this.billedToFirstName = firstName;
        this.billedToMiddleName = middleName;
        this.billedToLastName = lastName;
        this.billedToFullName = firstName+" "+middleName+" "+lastName;
    }

    public void setAddress(boolean checkBox, String address, String city, String stateProvince,
                           String postalCode, String country, AddressType addressType) {

        String type;
        int positionCheckBox;
        switch (addressType) {
            case Billing:
                type = "bill";
                positionCheckBox = 1;
                this.billedToAddress = address;
                this.billedToCity = city;
                this.billedToStateProvince = stateProvince;
                this.billedToPostalCode = postalCode;
                break;
            case Delivery:
                type = "del";
                positionCheckBox = 2;
                this.deliveryAddress = address;
                this.deliveryCity = city;
                this.deliveryStateProvince = stateProvince;
                this.deliveryPostalCode = postalCode;
                break;
                default:
                    throw new IllegalArgumentException("Unknown addressType");
        }
        WebElement inputCheckBox = driver.findElement(By.xpath("(//input[@name = 'ticketLess'])[" + positionCheckBox + "]"));
        WebElement inputAddress = driver.findElement(By.xpath("//input[@name = '" + type + "Address1']"));
        WebElement inputCity = driver.findElement(By.xpath("//input[@name = '" + type + "City']"));
        WebElement inputStateProvince = driver.findElement(By.xpath("//input[@name = '" + type + "State']"));
        WebElement inputPostalCode = driver.findElement(By.xpath("//input[@name = '" + type + "Zip']"));
        WebElement selectContryEl = driver.findElement(By.xpath("//select[@name = '" + type + "Country']"));

        Select selectCountry = new Select(selectContryEl);

        if(checkBox) inputCheckBox.click();

        inputAddress.clear();
        inputCity.clear();
        inputStateProvince.clear();
        inputPostalCode.clear();

        inputAddress.sendKeys(address);
        inputCity.sendKeys(city);
        inputStateProvince.sendKeys(stateProvince);
        inputPostalCode.sendKeys(postalCode);
        selectCountry.selectByVisibleText(country);

    }

    public void clickSecurePurchase() {
        WebElement buttonSecurePurchase = driver.findElement(By.xpath("//input[@name = 'buyFlights']"));
        buttonSecurePurchase.click();
    }

    public FlightConfirmationPage initFlightConfirmationPage() {
        if (FlightConfirmationPage.TITLE.equals(this.driver.getTitle())) {
            return new FlightConfirmationPage(this.driver);
        } else {
            throw new IllegalArgumentException("WebDriver is not correspond page "+ FlightFinderPage.PAGE_NAME);
        }
    }

    private void initFields() {
        this.wayDepart = listOfWayDatDepart.get(0).getText();
        this.dateDepart = listOfWayDatDepart.get(1).getText();
        this.flightDepart = listOfFlightInfoDepart.get(0).getText();
        this.classDepart = listOfFlightInfoDepart.get(1).getText();
        this.priceDepart = Integer.parseInt(listOfFlightInfoDepart.get(2).getText());
        this.wayReturn = listOfWayDateReturn.get(0).getText();
        this.dateReturn = listOfWayDateReturn.get(1).getText();
        this.flightReturn = listOfFlightInfoReturn.get(2).getText();
        this.classReturn = listOfFlightInfoReturn.get(4).getText();
        this.priceReturn = Integer.parseInt(listOfFlightInfoReturn.get(5).getText());
        this.passengers = Integer.parseInt(passengersElem.getText());
        this.taxes = Integer.parseInt(taxesElem.getText().replace("$",""));
        this.totalPrice = Integer.parseInt(totalPriceElem.getText().replace("$",""));
    }


}
