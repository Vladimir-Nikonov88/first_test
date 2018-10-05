package steps;

import org.openqa.selenium.WebDriver;
import pages.SelectFlightPage;

import java.util.logging.Logger;

public class SelectFlightStep {

    private static Logger log = Logger.getLogger(SelectFlightStep.class.getName());
    private SelectFlightPage selectFlightPage;

    private String wayDepart;
    private String dateDepart;
    private String fligthDepart;
    private int priceDepart;
    private String wayReturn;
    private String dateReturn;
    private String fligthReturn;
    private int priceReturn;

    public SelectFlightStep(final WebDriver driver) {
        this.selectFlightPage = new SelectFlightPage(driver);
    }


}
