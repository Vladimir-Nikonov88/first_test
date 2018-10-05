import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BookAFlightPage;
import pages.FlightConfirmationPage;
import pages.FlightFinderPage;
import pages.SelectFlightPage;
import pages.WelcomePage;
import steps.BookAFlightStep;
import steps.FlightFinderStep;
import steps.SelectFlightStep;
import steps.WelcomeStep;


import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class FirstTest {

    private static Logger log = Logger.getLogger(WelcomeStep.class.getName());
    private static WebDriver driver;
    private static WelcomeStep welcomeStep;
    private static FlightFinderStep flightFinderStep;
    private static SelectFlightStep selectFlightStep;
    private static BookAFlightStep bookAFlightStep;
    private static FlightConfirmationPage flightConfirmationPage;


    @BeforeClass
    public static void setup() {
        System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        welcomeStep = new WelcomeStep(driver);
        flightFinderStep = new FlightFinderStep(driver);
        selectFlightStep = new SelectFlightStep(driver);
        bookAFlightStep = new BookAFlightStep(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //   http://newtours.demoaut.com/
        driver.get("http://newtours.demoaut.com/");
    }

    @Test
    public void generalTest() {
        checkCurrentPage(WelcomePage.PAGE_NAME, WelcomePage.TITLE);
        setWelcomePage();

        checkCurrentPage(FlightFinderPage.PAGE_NAME, FlightFinderPage.TITLE);
        setFlightFinderPage();

        checkCurrentPage(SelectFlightPage.PAGE_NAME, SelectFlightPage.TITLE);
        testSelectFlightPage();

        checkCurrentPage(BookAFlightPage.PAGE_NAME, BookAFlightPage.TITLE);
        testBookAFlightPage();

        checkCurrentPage(FlightConfirmationPage.PAGE_NAME, FlightConfirmationPage.TITLE);
        checkFlightConfirmationPage();

        checkCurrentPage(WelcomePage.PAGE_NAME, WelcomePage.TITLE);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    private void setWelcomePage() {
        welcomeStep.inputLogin("tutorial");
        welcomeStep.inputPassword("tutorial");
        welcomeStep.clickSignInImage();
    }

    private void setFlightFinderPage() {
        flightFinderStep.setType(FlightFinderPage.Type.One_Way);
        flightFinderStep.setPassengers(2);
        flightFinderStep.setDepartingFrom("Paris");
        flightFinderStep.setOn("November", "20");
        flightFinderStep.setArrivingIn("Seattle");
        flightFinderStep.setReturning("December", "19");
        flightFinderStep.setServiceClass(FlightFinderPage.ServiceClass.BusinessClass);
        flightFinderStep.setAirline("Pangea Airlines");
        flightFinderStep.clickContinueButton();
    }

    private void testSelectFlightPage() {
        selectFlightStep.initFields();
        selectFlightStep.setDepartFlight("Unified Airlines 363");
        selectFlightStep.setReturnFlight("Blue Skies Airlines 631");
        checkValueSelectFlightPage();
        selectFlightStep.clickContinueButton();
    }

    private void checkValueSelectFlightPage() {
        checkValuePage(SelectFlightPage.PAGE_NAME, "wayDepart", "Paris to Seattle", selectFlightStep.getWayDepart());
        checkValuePage(SelectFlightPage.PAGE_NAME, "dateDepart", "11/20/2018", selectFlightStep.getDateDepart());
        checkValuePage(SelectFlightPage.PAGE_NAME, "priceDepart", 281, selectFlightStep.getPriceDepart());
        checkValuePage(SelectFlightPage.PAGE_NAME, "wayReturn", "Seattle to Paris", selectFlightStep.getWayReturn());
        checkValuePage(SelectFlightPage.PAGE_NAME, "dateReturn", "12/19/2018", selectFlightStep.getDateReturn());
        checkValuePage(SelectFlightPage.PAGE_NAME, "priceDepart", 273, selectFlightStep.getPriceReturn());
    }

    private void testBookAFlightPage() {
        bookAFlightStep.initFields();
        checkValueBookAFlightPage();
        setValueBookAFlightPage();
        System.out.println("Set values " + BookAFlightPage.PAGE_NAME);
        bookAFlightStep.clickSecurePurchaseButton();
        System.out.println("Click SecurePurchase Button");
    }

    private void checkValueBookAFlightPage() {
        int totalPrice = (bookAFlightStep.getPriceDepart() + bookAFlightStep.getPriceReturn()) *
                bookAFlightStep.getPassengers() + bookAFlightStep.getTaxes();
        checkValuePage(BookAFlightPage.PAGE_NAME, "wayDepart", "Paris to Seattle", bookAFlightStep.getWayDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME, "dateDepart", "11/20/2018", bookAFlightStep.getDateDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME, "flightDepart", "Unified Airlines 363", bookAFlightStep.getFlightDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME, "classDepart", "Business", bookAFlightStep.getClassDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME, "priceDepart", 281, bookAFlightStep.getPriceDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME, "wayReturn", "Seattle to Paris", bookAFlightStep.getWayReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME, "dateReturn", "12/19/2018", bookAFlightStep.getDateReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME, "flightReturn", "Blue Skies Airlines 631", bookAFlightStep.getFlightReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME, "classReturn", "Business", bookAFlightStep.getClassReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME, "priceReturn", 273, bookAFlightStep.getPriceReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME, "totalPrice", 1199, bookAFlightStep.getTotalPrice());
        checkValuePage(BookAFlightPage.PAGE_NAME, "totalPrice", 1199, totalPrice);

    }

    private void setValueBookAFlightPage() {
        bookAFlightStep.setPassengers(1, "Ivanov", "Ivan", "Hindu");
        bookAFlightStep.setPassengers(2, "Ivanova", "Irina", "Bland");

        bookAFlightStep.setCreditCard("Visa", "5479540454132487", "05", "2009",
                "Ivan", "Ivanovich", "Ivanov");

        bookAFlightStep.setAddress(false, "1085 Borregas Ave.", "Albuquerque", "New Mexico",
                "94089", "UNITED STATES", BookAFlightPage.AddressType.Billing);

        bookAFlightStep.setAddress(true, "1225 Borregas Ave.", "Boston", "Massachusetts",
                "91089", "UNITED STATES", BookAFlightPage.AddressType.Delivery);
    }

    private void checkFlightConfirmationPage() {
        checkDepartFlightConfirmationPage();
        checkReturnFlightConfirmationPage();
        checkCountPassengersFlightConfirmationPage();
        checkBilledToFlightConfirmationPage();
        checkDeliveryFlightConfirmationPage();
        checkTotalPriceFlightConfirmationPage();

        flightConfirmationPage.clickBottonBackToHome();
        System.out.println("Click BackToHome Button");
    }

    private void checkDepartFlightConfirmationPage() {
        System.out.println("checkDepart");
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "wayDepart", selectFlightStep.getWayDepart(), flightConfirmationPage.getWayDepart());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "dateDepart", selectFlightStep.getDateDepart(), flightConfirmationPage.getDateDepart());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "flightDepart", selectFlightStep.getFlightDepart(), flightConfirmationPage.getFlightDepart());
    }

    private void checkReturnFlightConfirmationPage() {
        System.out.println("checkReturn");
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "wayReturn", selectFlightStep.getWayReturn(), flightConfirmationPage.getWayReturn());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "dateReturn", selectFlightStep.getDateReturn(), flightConfirmationPage.getDateReturn());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "flightReturn", selectFlightStep.getFlightReturn(), flightConfirmationPage.getFlightReturn());
    }

    private void checkCountPassengersFlightConfirmationPage() {
        System.out.println("checkCountPassengers");
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "countPassengers", flightFinderStep.getCountPassengers(), flightConfirmationPage.getCountPassengers());
    }

    private void checkBilledToFlightConfirmationPage() {
        System.out.println("checkBilledTo");
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToFullName", bookAFlightStep.getBilledToFullName(), flightConfirmationPage.getBilledToFullName());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToAddress", bookAFlightStep.getBilledToAddress(), flightConfirmationPage.getBilledToAddress());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToCity", bookAFlightStep.getBilledToCity(), flightConfirmationPage.getBilledToCity());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToStateProvince", bookAFlightStep.getBilledToStateProvince(), flightConfirmationPage.getBilledToStateProvince());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToStatePostalCode()", bookAFlightStep.getBilledToPostalCode(), flightConfirmationPage.getBilledToPostalCode());
    }

    private void checkDeliveryFlightConfirmationPage() {
        System.out.println("checkDelivery");
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryAddress", bookAFlightStep.getDeliveryAddress(), flightConfirmationPage.getDeliveryAddress());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryCity", bookAFlightStep.getDeliveryCity(), flightConfirmationPage.getDeliveryCity());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryStateProvince", bookAFlightStep.getDeliveryStateProvince(), flightConfirmationPage.getDeliveryStateProvince());
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryStatePostalCode", bookAFlightStep.getDeliveryPostalCode(), flightConfirmationPage.getDeliveryPostalCode());
    }

    private void checkTotalPriceFlightConfirmationPage() {
        System.out.println("checkTotalPrice");
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "totalPrice", bookAFlightStep.getTotalPrice(),
                (flightConfirmationPage.getPriceDepart() + flightConfirmationPage.getPriceReturn()) * flightConfirmationPage.getCountPassengers() +
                        flightConfirmationPage.getTaxes());
    }

    private void checkCurrentPage(final String namePage, final String nameTitle) {
        if (nameTitle.equals(driver.getTitle())) {
            System.out.println("\nNEW PAGE  " + namePage);
        } else {
            System.out.println("FAIL: Current page incorrect");
            Assert.fail();
        }
    }

    private void checkValuePage(final String namePage, final String nameVariable, final Object expected, final Object actual) {
        String mark = "";
        if (expected instanceof String && actual instanceof String) {
            mark = "%s";
        } else if (expected instanceof Integer && actual instanceof Integer) {
            mark = "%d";
        } else {
            throw new IllegalArgumentException();
        }
        if (expected.equals(actual)) {
            log.info(String.format("CORRECT value Page: %s, name: %s \texpected = " + mark + ", actual = " + mark, namePage, nameVariable, expected, actual));
        } else {
            log.info(String.format("INCORRECT!!! value Page: %s, name: %s \texpected = " + mark + ", actual = " + mark, namePage, nameVariable, expected, actual));
            Assert.fail();
        }
    }
}
