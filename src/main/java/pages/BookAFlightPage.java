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

    public enum AddressType { Billing, Delivery }

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

    public BookAFlightPage(final WebDriver driver) {
        super(driver);
    }

    public String getWayDepart() {
        return listOfWayDatDepart.get(0).getText();
    }
    public String getDateDepart() {
        return listOfWayDatDepart.get(1).getText();
    }
    public String getFlightDepart() {
        return listOfFlightInfoDepart.get(0).getText();
    }
    public String getClassDepart() {
        return listOfFlightInfoDepart.get(1).getText();
    }
    public int getPriceDepart() {
        return Integer.parseInt(listOfFlightInfoDepart.get(2).getText());
    }
    public String getWayReturn() {
        return listOfWayDateReturn.get(0).getText();
    }
    public String getDateReturn() {
        return listOfWayDateReturn.get(1).getText();
    }
    public String getFlightReturn() {
        return listOfFlightInfoReturn.get(2).getText();
    }
    public String getClassReturn() {
        return listOfFlightInfoReturn.get(4).getText();
    }
    public int getPriceReturn() {
        return Integer.parseInt(listOfFlightInfoReturn.get(5).getText());
    }
    public int getPassengers() {
        return Integer.parseInt(passengersElem.getText());
    }
    public int getTaxes() {
        return Integer.parseInt(taxesElem.getText().replace("$",""));
    }
    public int getTotalPrice() {
        return Integer.parseInt(totalPriceElem.getText().replace("$",""));
    }


    public void setPassengers(int number, final String firstName, final String lastName, final String meal) {
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
    public void setCreditCard(final String type, final String number, final String month, final String year,
                              final String firstName, final String middleName, final String lastName) {
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
    }

    public void setAddress(boolean checkBox, final String address, final String city, final String stateProvince,
                           final String postalCode, final String country, final AddressType addressType) {

        String type;
        int positionCheckBox;
        switch (addressType) {
            case Billing:
                type = "bill";
                positionCheckBox = 1;
                break;
            case Delivery:
                type = "del";
                positionCheckBox = 2;
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

        if (checkBox) {
            inputCheckBox.click();
        }

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

}
