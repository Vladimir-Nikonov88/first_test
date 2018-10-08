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
import steps.FlightConfirmationStep;
import steps.FlightFinderStep;
import steps.SelectFlightStep;
import steps.WelcomeStep;


import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * The class was created for test my first test.
 */
public class FirstTest {

    public static final String LOGIN = "tutorial";
    public static final String PASSWORD = "tutorial";
    public static final int TIMEOUT = 10;
    public static final int PRICE_DEPART = 281;
    public static final int PRICE_RETURN = 273;
    public static final int TOTAL_PRICE = 1199;
    private static Logger log = Logger.getLogger(WelcomeStep.class.getName());
    private static WebDriver driver;
    private static WelcomeStep welcomeStep;
    private static FlightFinderStep flightFinderStep;
    private static SelectFlightStep selectFlightStep;
    private static BookAFlightStep bookAFlightStep;
    private static FlightConfirmationStep flightConfirmationStep;


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
        flightConfirmationStep = new FlightConfirmationStep(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
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
        testFlightConfirmationPage();

        checkCurrentPage(WelcomePage.PAGE_NAME, WelcomePage.TITLE);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    private void setWelcomePage() {
        welcomeStep.inputLogin(LOGIN);
        welcomeStep.inputPassword(PASSWORD);
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
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "wayDepart",
                "Paris to Seattle",
                selectFlightStep.getWayDepart());
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "dateDepart",
                "11/20/2018",
                selectFlightStep.getDateDepart());
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "wayReturn",
                "Seattle to Paris",
                selectFlightStep.getWayReturn());
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "dateReturn",
                "12/19/2018",
                selectFlightStep.getDateReturn());
    }

    private void testBookAFlightPage() {
        bookAFlightStep.initFields();
        checkValueBookAFlightPage();
        setValueBookAFlightPage();
        bookAFlightStep.clickSecurePurchaseButton();
    }

    private void checkValueBookAFlightPage() {
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "wayDepart",
                "Paris to Seattle",
                bookAFlightStep.getWayDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "dateDepart",
                "11/20/2018",
                bookAFlightStep.getDateDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "flightDepart",
                "Unified Airlines 363",
                bookAFlightStep.getFlightDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "classDepart",
                "Business",
                bookAFlightStep.getClassDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceDepart",
                PRICE_DEPART,
                bookAFlightStep.getPriceDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "wayReturn",
                "Seattle to Paris",
                bookAFlightStep.getWayReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "dateReturn",
                "12/19/2018",
                bookAFlightStep.getDateReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "flightReturn",
                "Blue Skies Airlines 631",
                bookAFlightStep.getFlightReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "classReturn",
                "Business",
                bookAFlightStep.getClassReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceReturn",
                PRICE_RETURN,
                bookAFlightStep.getPriceReturn());
        int totalPrice = (bookAFlightStep.getPriceDepart() + bookAFlightStep.getPriceReturn())
                * bookAFlightStep.getPassengers() + bookAFlightStep.getTaxes();
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "totalPrice",
                TOTAL_PRICE,
                totalPrice);

    }
    private void setValueBookAFlightPage() {
        log.info("\nSet Value BookAFlightPage");
        bookAFlightStep.setPassengers(1,
                "Ivanov",
                "Ivan",
                "Hindu");
        bookAFlightStep.setPassengers(2,
                "Ivanova",
                "Irina",
                "Bland");

        bookAFlightStep.setCreditCard("Visa",
                "5479540454132487",
                "05",
                "2009",
                "Ivan",
                "Ivanovich",
                "Ivanov");

        bookAFlightStep.setAddress(false,
                "1085 Borregas Ave.",
                "Albuquerque",
                "New Mexico",
                "94089",
                "UNITED STATES",
                BookAFlightPage.AddressType.Billing);

        bookAFlightStep.setAddress(true,
                "1225 Borregas Ave.",
                "Boston",
                "Massachusetts",
                "91089",
                "UNITED STATES",
                BookAFlightPage.AddressType.Delivery);
    }

    private void testFlightConfirmationPage() {
        flightConfirmationStep.initFields();
        checkDepartFlightConfirmationPage();
        checkReturnFlightConfirmationPage();
        checkCountPassengersFlightConfirmationPage();
        checkBilledToFlightConfirmationPage();
        checkDeliveryFlightConfirmationPage();
        checkTotalPriceFlightConfirmationPage();
        flightConfirmationStep.clickButtonBackToHome();
    }

    private void checkDepartFlightConfirmationPage() {
        log.info("\nCheckDepart");
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "wayDepart",
                selectFlightStep.getWayDepart(),
                flightConfirmationStep.getWayDepart());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "dateDepart",
                selectFlightStep.getDateDepart(),
                flightConfirmationStep.getDateDepart());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "flightDepart",
                selectFlightStep.getFlightDepart(),
                flightConfirmationStep.getFlightDepart());
    }

    private void checkReturnFlightConfirmationPage() {
        log.info("\nCheckReturn");
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "wayReturn",
                selectFlightStep.getWayReturn(),
                flightConfirmationStep.getWayReturn());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "dateReturn",
                selectFlightStep.getDateReturn(),
                flightConfirmationStep.getDateReturn());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "flightReturn",
                selectFlightStep.getFlightReturn(),
                flightConfirmationStep.getFlightReturn());
    }

    private void checkCountPassengersFlightConfirmationPage() {
        log.info("\nCheckCountPassengers");
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "countPassengers",
                flightFinderStep.getCountPassengers(),
                flightConfirmationStep.getCountPassengers());
    }

    private void checkBilledToFlightConfirmationPage() {
        log.info("\nCheckBilledTo");
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToFullName",
                bookAFlightStep.getBilledToFullName(),
                flightConfirmationStep.getBilledToFullName());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToAddress",
                bookAFlightStep.getBilledToAddress(),
                flightConfirmationStep.getBilledToAddress());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToCity",
                bookAFlightStep.getBilledToCity(),
                flightConfirmationStep.getBilledToCity());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToStateProvince",
                bookAFlightStep.getBilledToStateProvince(),
                flightConfirmationStep.getBilledToStateProvince());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToStatePostalCode()",
                bookAFlightStep.getBilledToPostalCode(),
                flightConfirmationStep.getBilledToPostalCode());
    }

    private void checkDeliveryFlightConfirmationPage() {
        log.info("\nCheckDelivery");
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryAddress",
                bookAFlightStep.getDeliveryAddress(),
                flightConfirmationStep.getDeliveryAddress());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryCity",
                bookAFlightStep.getDeliveryCity(),
                flightConfirmationStep.getDeliveryCity());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryStateProvince",
                bookAFlightStep.getDeliveryStateProvince(),
                flightConfirmationStep.getDeliveryStateProvince());
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryStatePostalCode",
                bookAFlightStep.getDeliveryPostalCode(),
                flightConfirmationStep.getDeliveryPostalCode());
    }

    private void checkTotalPriceFlightConfirmationPage() {
        log.info("\nCheckTotalPrice");
        checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "totalPrice",
                bookAFlightStep.getTotalPrice(),
                (flightConfirmationStep.getPriceDepart() + flightConfirmationStep.getPriceReturn())
                        * flightConfirmationStep.getCountPassengers()
                        + flightConfirmationStep.getTaxes());
    }

    private void checkCurrentPage(final String namePage, final String nameTitle) {
        if (nameTitle.equals(driver.getTitle())) {
            System.out.println("\nNEW PAGE  " + namePage);
            Assert.assertTrue(true);
        } else {
            System.out.println("FAIL: Current page incorrect");
            Assert.fail();
        }
    }

    private void checkValuePage(final String namePage,
                                final String nameVariable,
                                final Object expected,
                                final Object actual) {
        String mark = "";
        if (expected instanceof String && actual instanceof String) {
            mark = "%s";
        } else if (expected instanceof Integer && actual instanceof Integer) {
            mark = "%d";
        } else {
            throw new IllegalArgumentException();
        }
        if (expected.equals(actual)) {
            log.info(String.format("CORRECT value Page: %s, name: %s \texpected = " + mark + ", actual = "
                    + mark, namePage, nameVariable, expected, actual));
        } else {
            log.info(String.format("INCORRECT!!! value Page: %s, name: %s \texpected = " + mark + ", actual = "
                    + mark, namePage, nameVariable, expected, actual));
            Assert.fail();
        }
    }
}
