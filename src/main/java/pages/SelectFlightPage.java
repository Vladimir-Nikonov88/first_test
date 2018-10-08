package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Class.
 */
public class SelectFlightPage extends Page {

    public static final String PAGE_NAME = "SELECT FLIGHT";
    public static final String TITLE = "Select a Flight: Mercury Tours";

    @FindBy(xpath = "(//font [text() = 'DEPART']/ancestor::tbody[1]//td[@align='LEFT'])[2]//font")
    private WebElement wayDepartFontEl;

    @FindBy(xpath = "(//font [text() = 'DEPART']/ancestor::tbody[1]//td[@align='RIGHT'])[2]//font")
    private WebElement dateDepartFontEl;

    @FindBy(xpath = "(//font [text() = 'RETURN']/ancestor::tbody[1]//td[@align='LEFT'])[2]//font")
    private WebElement wayReturnFontEl;

    @FindBy(xpath = "(//font [text() = 'RETURN']/ancestor::tbody[1]//td[@align='RIGHT'])[2]//font")
    private WebElement dateReturnFontEl;

    @FindBy(xpath = "//input [@name='reserveFlights']")
    private WebElement buttonContinue;


    public SelectFlightPage(final WebDriver driver) {
        super(driver);
    }

    /**
     * Возврвщает путь вылета.
     * @return
     */
    public String getWayDepart() {
        return wayDepartFontEl.getText();
    }

    /**
     * Возврвщает дату вылета.
     * @return
     */
    public String getDateDepart() {
        return dateDepartFontEl.getText();
    }

    /**
     * Возврвщает путь возврата.
     * @return
     */
    public String getWayReturn() {
        return wayReturnFontEl.getText();
    }

    /**
     * Возврвщает дату возврата.
     * @return
     */
    public String getDateReturn() {
        return dateReturnFontEl.getText();
    }

    /**
     * Возврвщает Авиакомпанию вылета.
     * @return
     */
    public void setDepartFlight(final String flight) {
        String xpath = getXPathDepartFlight(flight);
        WebElement departFlightUnifiedAirlines = getDriver().findElement(By.xpath(xpath));
        departFlightUnifiedAirlines.click();
    }

    /**
     *
     * @param flight
     */
    public void setReturnFlight(final String flight) {
        String xpath = getXPathReturnFlight(flight);
        WebElement returnFlightUnifiedAirlines = getDriver().findElement(By.xpath(xpath));
        returnFlightUnifiedAirlines.click();
    }

    /**
     *Method.
     */
    public void clickContinue() {
        buttonContinue.click();
    }

    /**
     *Method.
     */
    public int getPriceDepartFlight(final String flight) {
        String xpathFlight = getXPathDepartFlight(flight);
        WebElement elementAirlanesPrice = getDriver().findElement(By.xpath(xpathFlight
                + "/parent::*/parent::*/following-sibling::*//b"));
        String price = elementAirlanesPrice.getText();
        price = price.replace("Price: $", "");
        return Integer.parseInt(price);
    }

    /**
     *Method.
     */
    public int getPriceReturnFlight(final String flight) {
        String xpathFlight = getXPathReturnFlight(flight);
        WebElement elementAirlanesPrice = getDriver().findElement(By.xpath(xpathFlight
                + "/parent::*/parent::*/following-sibling::*//b"));
        String price = elementAirlanesPrice.getText();
        price = price.replace("Price: $", "");
        return Integer.parseInt(price);
    }

    private String getXPathDepartFlight(final String flight) {
        int index = flight.trim().lastIndexOf(" ");
        String flightChange = flight.substring(0, index) + '$' + flight.substring(index + 1);
        String xpath = "//input [@name='outFlight' and contains(@value,'" + flightChange + "')]";
        return xpath;
    }

    private String getXPathReturnFlight(final String flight) {
        int index = flight.trim().lastIndexOf(" ");
        String flightChange = flight.substring(0, index) + '$' + flight.substring(index + 1);
        String xpath = "//input [@name='inFlight' and contains(@value,'" + flightChange + "')]";
        return xpath;
    }

}
