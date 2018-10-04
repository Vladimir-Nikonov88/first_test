package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectFlightPage extends Page {

    public static final String PAGE_NAME = "SELECT FLIGHT";
    public static final String TITLE = "Select a Flight: Mercury Tours";
    private String wayDepart;
    private String dateDepart;
    private String fligthDepart;
    private int priceDepart;
    private String wayReturn;
    private String dateReturn;
    private String fligthReturn;
    private int priceReturn;

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


    public SelectFlightPage(WebDriver driver) {
        super(driver);
        this.wayDepart = wayDepartFontEl.getText();
        this.dateDepart = dateDepartFontEl.getText();
        this.wayReturn = wayReturnFontEl.getText();
        this.dateReturn = dateReturnFontEl.getText();
    }

    public String getWayDepart() {
        return wayDepart;
    }
    public String getDateDepart() {
        return dateDepart;
    }
    public String getFligthDepart() {
        return fligthDepart;
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
    public String getFligthReturn() {
        return fligthReturn;
    }
    public int getPriceReturn() {
        return priceReturn;
    }

    public void setDepartFligth(String flight){
        int index = flight.trim().lastIndexOf(" ");
        String flightChange = flight.substring(0,index)+'$'+flight.substring(index + 1);
        String xpath = "//input [@name='outFlight' and contains(@value,'"+flightChange+"')]";
        WebElement departFligthUnifiedAirlines = driver.findElement(By.xpath(xpath));
        departFligthUnifiedAirlines.click();
        this.fligthDepart = flight;
        this.priceDepart = getPrice(xpath);
    }

    public void setReturnFligth(String flight){
        int index = flight.trim().lastIndexOf(" ");
        String flightChange = flight.substring(0,index)+'$'+flight.substring(index + 1);
        String xpath = "//input [@name='inFlight' and contains(@value,'"+flightChange+"')]";
        WebElement returnFligthUnifiedAirlines = driver.findElement(By.xpath(xpath));
        returnFligthUnifiedAirlines.click();
        this.fligthReturn = flight;
        this.priceReturn = getPrice(xpath);
    }

    public void clickContinue() {
        buttonContinue.click();
    }

    public BookAFlightPage initBookAFlightPage() {
        if (BookAFlightPage.TITLE.equals(this.driver.getTitle())) {
            return new BookAFlightPage(this.driver);
        } else {
            throw new IllegalArgumentException("WebDriver is not correspond page "+ BookAFlightPage.PAGE_NAME);
        }
    }

    private int getPrice(String xpathFligth){
        WebElement elementAirlanesPrice = driver.findElement(By.xpath(xpathFligth + "/parent::*/parent::*/following-sibling::*//b"));
        String price = elementAirlanesPrice.getText();
        price = price.replace("Price: $", "");
        return Integer.parseInt(price);
    }

}
