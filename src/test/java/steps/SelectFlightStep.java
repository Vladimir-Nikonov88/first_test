package steps;

import org.openqa.selenium.WebDriver;
import pages.SelectFlightPage;

import java.util.logging.Logger;

public class SelectFlightStep {

    private static Logger log = Logger.getLogger(SelectFlightStep.class.getName());
    private SelectFlightPage selectFlightPage;

    private String wayDepart;
    private String dateDepart;
    private String flightDepart;
    private int priceDepart;
    private String wayReturn;
    private String dateReturn;
    private String flightReturn;
    private int priceReturn;

    public SelectFlightStep(final WebDriver driver) {
        this.selectFlightPage = new SelectFlightPage(driver);
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

    public int getPriceReturn() {
        return priceReturn;
    }

    public void setDepartFlight(final String flight) {
        log.info("Set Depart Flight: " + flight);
        selectFlightPage.setDepartFlight(flight);
        this.flightDepart = flight;
        this.priceDepart = selectFlightPage.getPriceDepartFlight(flight);
    }

    public void setReturnFlight(final String flight) {
        log.info("Set Return Flight: " + flight);
        selectFlightPage.setReturnFlight(flight);
        this.flightReturn = flight;
        this.priceReturn = selectFlightPage.getPriceReturnFlight(flight);
    }

    public void clickContinueButton() {
        log.info("Click Continue Button");
        selectFlightPage.clickContinue();
    }

    public void initFields() {
        this.wayDepart = selectFlightPage.getWayDepart();
        this.dateDepart = selectFlightPage.getDateDepart();
        this.wayReturn = selectFlightPage.getWayReturn();
        this.dateReturn = selectFlightPage.getDateReturn();
    }

}
