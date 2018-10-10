package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Класс для проверки страницы BOOK A FLIGHT.
 */
public class BookAFlightPage extends Page {

    public static final String PAGE_NAME = "BOOK A FLIGHT";
    public static final String TITLE = "Book a Flight: Mercury Tours";
    public static final int INDEX_OF_CLASS_RETURN = 4;
    public static final int INDEX_OF_PRICE_RETURN = 5;

    /**
     * Перечисление возможных типов адресов.
     */
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

    /**
     * Возвращает путь вылета.
     * @return
     */
    public String getWayDepart() {
        return listOfWayDatDepart.get(0).getText();
    }

    /**
     * Возвращает дату вылета.
     * @return
     */
    public String getDateDepart() {
        return listOfWayDatDepart.get(1).getText();
    }

    /**
     * Возвращает Авиакомпанию вылета.
     * @return
     */
    public String getFlightDepart() {
        return listOfFlightInfoDepart.get(0).getText();
    }

    /**
     * Возвращает класс вылета.
     * @return
     */
    public String getClassDepart() {
        return listOfFlightInfoDepart.get(1).getText();
    }

    /**
     * Возвращает цену вылета.
     * @return
     */
    public int getPriceDepart() {
        return Integer.parseInt(listOfFlightInfoDepart.get(2).getText());
    }

    /**
     * Возвращает путь вылета.
     * @return
     */
    public String getWayReturn() {
        return listOfWayDateReturn.get(0).getText();
    }

    /**
     * Возвращает путь возврата.
     * @return
     */
    public String getDateReturn() {
        return listOfWayDateReturn.get(1).getText();
    }

    /**
     * Возвращает Авиакомпанию возврата.
     * @return
     */
    public String getFlightReturn() {
        return listOfFlightInfoReturn.get(2).getText();
    }

    /**
     * Возвращает класс возврата.
     * @return
     */
    public String getClassReturn() {
        return listOfFlightInfoReturn.get(INDEX_OF_CLASS_RETURN).getText();
    }

    /**
     * Возвращает цену возврата.
     * @return
     */
    public int getPriceReturn() {
        return Integer.parseInt(listOfFlightInfoReturn.get(INDEX_OF_PRICE_RETURN).getText());
    }

    /**
     * Возвращает количество пассажиров.
     * @return
     */
    public int getPassengers() {
        return Integer.parseInt(passengersElem.getText());
    }

    /**
     * Возвращает сумму налога.
     * @return
     */
    public int getTaxes() {
        return Integer.parseInt(taxesElem.getText().replace("$", ""));
    }

    /**
     * Возвращает общую сумму.
     * @return
     */
    public int getTotalPrice() {
        return Integer.parseInt(totalPriceElem.getText().replace("$", ""));
    }


    /**
     * Устанавливает количество пассажиров.
     */
    public void setPassengers(final int number, final String firstName, final String lastName, final String meal) {
        int index = number - 1;
        WebElement inputFirstName = getDriver().findElement(By.xpath("//input[@name = 'passFirst" + index + "']"));
        WebElement inputLastName = getDriver().findElement(By.xpath("//input[@name = 'passLast" + index + "']"));
        WebElement selectMealEl = getDriver().findElement(By.xpath("//select [@name = 'pass." + index + ".meal']"));

        Select selectMeal = new Select(selectMealEl);

        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        selectMeal.selectByVisibleText(meal);

    }


    /**
     * Устанавливает информацию о кредитной карте.
     */
    public void setCreditCard(final String type, final String number, final String month, final String year,
                              final String firstName, final String middleName, final String lastName) {
        WebElement selectTypeEl = getDriver().findElement(By.xpath("//select [@name = 'creditCard']"));
        WebElement inputNumber = getDriver().findElement(By.xpath("//input[@name='creditnumber']"));
        WebElement selectMonthEl = getDriver().findElement(By.xpath("//select [@name = 'cc_exp_dt_mn']"));
        WebElement selectYearEl = getDriver().findElement(By.xpath("//select [@name = 'cc_exp_dt_yr']"));
        WebElement inputFirstName = getDriver().findElement(By.xpath("//input[@name='cc_frst_name']"));
        WebElement inputMiddleName = getDriver().findElement(By.xpath("//input[@name='cc_mid_name']"));
        WebElement inputLastName = getDriver().findElement(By.xpath("//input[@name='cc_last_name']"));

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

    /**
     * Устанавливает адрес платильщика или адрес
     * назначения в зависимомти от параметра addressType.
     */
    public void setAddress(final boolean checkBox, final String address, final String city, final String stateProvince,
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
        WebElement inputCheckBox = getDriver().findElement(By.xpath("(//input[@name = 'ticketLess'])["
                + positionCheckBox + "]"));
        WebElement inputAddress = getDriver().findElement(By.xpath("//input[@name = '" + type + "Address1']"));
        WebElement inputCity = getDriver().findElement(By.xpath("//input[@name = '" + type + "City']"));
        WebElement inputStateProvince = getDriver().findElement(By.xpath("//input[@name = '" + type + "State']"));
        WebElement inputPostalCode = getDriver().findElement(By.xpath("//input[@name = '" + type + "Zip']"));
        WebElement selectContryEl = getDriver().findElement(By.xpath("//select[@name = '" + type + "Country']"));

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

    /**
     * Возвращает путь вылета.
     * @return
     */
    public void clickSecurePurchase() {
        WebElement buttonSecurePurchase = getDriver().findElement(By.xpath("//input[@name = 'buyFlights']"));
        buttonSecurePurchase.click();
    }

}
