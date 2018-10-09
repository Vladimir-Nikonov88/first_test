import jdbc.JDBC;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
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
    public static final int TIMEOUT = 10;

    @Rule
    public final MyRule myRule = new MyRule();
    private static Logger log = Logger.getLogger(WelcomeStep.class.getName());
    private static JDBC jdbc;
    private static WebDriver driver;
    private static WelcomeStep welcomeStep;
    private static FlightFinderStep flightFinderStep;
    private static SelectFlightStep selectFlightStep;
    private static BookAFlightStep bookAFlightStep;
    private static FlightConfirmationStep flightConfirmationStep;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        jdbc = new JDBC();
        driver = new ChromeDriver();
        welcomeStep = new WelcomeStep(driver);
        flightFinderStep = new FlightFinderStep(driver);
        selectFlightStep = new SelectFlightStep(driver);
        bookAFlightStep = new BookAFlightStep(driver);
        flightConfirmationStep = new FlightConfirmationStep(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
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
        welcomeStep.inputLogin(jdbc.executeValue("LOGIN"));
        welcomeStep.inputPassword(jdbc.executeValue("PASSWORD1"));
        welcomeStep.clickSignInImage();
    }

    private void setFlightFinderPage() {
        flightFinderStep.setType(FlightFinderPage.Type.One_Way);
        flightFinderStep.setPassengers(Integer.parseInt(jdbc.executeValue("countPassengers")));
        flightFinderStep.setDepartingFrom(jdbc.executeValue("DepartingFrom"));
        flightFinderStep.setOn(jdbc.executeValue("onMonth"),
                jdbc.executeValue("onDay"));
        flightFinderStep.setArrivingIn(jdbc.executeValue("arrivingIn"));
        flightFinderStep.setReturning(jdbc.executeValue("returningMonth"),
                jdbc.executeValue("returningDay"));
        flightFinderStep.setServiceClass(FlightFinderPage.ServiceClass.BusinessClass);
        flightFinderStep.setAirline(jdbc.executeValue("desiredAirline"));
        flightFinderStep.clickContinueButton();
    }

    private void testSelectFlightPage() {
        selectFlightStep.initFields();
        selectFlightStep.setDepartFlight(jdbc.executeValue("flightDepart"));
        selectFlightStep.setReturnFlight(jdbc.executeValue("flightReturn"));
        checkValueSelectFlightPage();
        selectFlightStep.clickContinueButton();
    }

    private void checkValueSelectFlightPage() {
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "wayDepart",
                jdbc.executeValue("wayDepart"),
                selectFlightStep.getWayDepart());
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "dateDepart",
                jdbc.executeValue("dateDepart"),
                selectFlightStep.getDateDepart());
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "wayReturn",
                jdbc.executeValue("wayReturn"),
                selectFlightStep.getWayReturn());
        checkValuePage(SelectFlightPage.PAGE_NAME,
                "dateReturn",
                jdbc.executeValue("dateReturn"),
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
                jdbc.executeValue("wayDepart"),
                bookAFlightStep.getWayDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "dateDepart",
                jdbc.executeValue("dateDepart"),
                bookAFlightStep.getDateDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "flightDepart",
                jdbc.executeValue("flightDepart"),
                bookAFlightStep.getFlightDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "classDepart",
                jdbc.executeValue("classDepart"),
                bookAFlightStep.getClassDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceDepart",
                Integer.parseInt(jdbc.executeValue("priceDepart")),
                bookAFlightStep.getPriceDepart());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "wayReturn",
                jdbc.executeValue("wayReturn"),
                bookAFlightStep.getWayReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "dateReturn",
                jdbc.executeValue("dateReturn"),
                bookAFlightStep.getDateReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "flightReturn",
                jdbc.executeValue("flightReturn"),
                bookAFlightStep.getFlightReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "classReturn",
                jdbc.executeValue("classReturn"),
                bookAFlightStep.getClassReturn());
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceReturn",
                Integer.parseInt(jdbc.executeValue("priceReturn")),
                bookAFlightStep.getPriceReturn());
        int totalPrice = (bookAFlightStep.getPriceDepart() + bookAFlightStep.getPriceReturn())
                * bookAFlightStep.getPassengers() + bookAFlightStep.getTaxes();
        checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceTotal",
                Integer.parseInt(jdbc.executeValue("priceTotal")),
                totalPrice);

    }
    private void setValueBookAFlightPage() {
        log.info("\nSet Value BookAFlightPage");
        bookAFlightStep.setPassengers(1,
                jdbc.executeValue("firstNamePass1"),
                jdbc.executeValue("lastNamePass1"),
                jdbc.executeValue("mealPass1"));
        bookAFlightStep.setPassengers(2,
                jdbc.executeValue("firstNamePass2"),
                jdbc.executeValue("lastNamePass2"),
                jdbc.executeValue("mealPass2"));

        bookAFlightStep.setCreditCard(jdbc.executeValue("creditCardType"),
                jdbc.executeValue("creditCardNumber"),
                jdbc.executeValue("creditCardMonth"),
                jdbc.executeValue("creditCardYear"),
                jdbc.executeValue("creditCardFirstName"),
                jdbc.executeValue("creditCardMiddleName"),
                jdbc.executeValue("creditCardLastName"));

        bookAFlightStep.setAddress(false,
                jdbc.executeValue("BillingAddress"),
                jdbc.executeValue("BillingCity"),
                jdbc.executeValue("BillingStateProvince"),
                jdbc.executeValue("BillingPostalCode"),
                jdbc.executeValue("BillingCountry"),
                BookAFlightPage.AddressType.Billing);

        bookAFlightStep.setAddress(true,
                jdbc.executeValue("DeliveryAddress"),
                jdbc.executeValue("DeliveryCity"),
                jdbc.executeValue("DeliveryStateProvince"),
                jdbc.executeValue("DeliveryPostalCode"),
                jdbc.executeValue("DeliveryCountry"),
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
