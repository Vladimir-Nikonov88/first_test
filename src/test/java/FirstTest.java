
import jdbc.JDBC;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.BookAFlightPage;
import pages.FlightConfirmationPage;
import pages.FlightFinderPage;
import pages.SelectFlightPage;
import pages.WelcomePage;
import steps.BookAFlightStep;
import steps.FlightConfirmationStep;
import steps.FlightFinderStep;
import steps.SelectFlightStep;
import steps.SupportSteps;
import steps.WelcomeStep;


import java.util.logging.Logger;

/**
 * The class was created for test my first test.
 */
@RunWith(SerenityRunner.class)
public class FirstTest {

    private static Logger log = Logger.getLogger(WelcomeStep.class.getName());
    @Steps
    private JDBC jdbc;
    @Rule
    public final MyRule myRule = new MyRule();
    @Managed
    private WebDriver driver;
    @Steps
    private WelcomeStep welcomeStep;
    @Steps
    private FlightFinderStep flightFinderStep;
    @Steps
    private SelectFlightStep selectFlightStep;
    @Steps
    private BookAFlightStep bookAFlightStep;
    @Steps
    private FlightConfirmationStep flightConfirmationStep;
    @Steps
    private SupportSteps supportSteps;

    @Before
    public void setup() {
        System.setProperty("java.util.logging.config.file",
                "src/main/resources/logging.properties");
        //jdbc = new JDBC();
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

    @After
    public void tearDown() {
        driver.quit();
    }

    @Step("Ввод логина и пароля на стартовой странице")
    private void setWelcomePage() {
        welcomeStep.inputLogin(jdbc.executeValue("LOGIN"));
        welcomeStep.inputPassword(jdbc.executeValue("PASSWORD"));
        welcomeStep.clickSignInImage();
    }
    @Step("Ввод значений на странице FlightFinder")
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

    @Step("Тест страницы SelectFlight")
    private void testSelectFlightPage() {
        selectFlightStep.initFields();
        selectFlightStep.setDepartFlight(jdbc.executeValue("flightDepart"));
        selectFlightStep.setReturnFlight(jdbc.executeValue("flightReturn"));
        checkValueSelectFlightPage();
        selectFlightStep.clickContinueButton();
    }

    @Step("Проверка страницы SelectFlight")
    private void checkValueSelectFlightPage() {
        supportSteps.checkValuePage(SelectFlightPage.PAGE_NAME,
                "wayDepart",
                jdbc.executeValue("wayDepart"),
                selectFlightStep.getWayDepart());
        supportSteps.checkValuePage(SelectFlightPage.PAGE_NAME,
                "dateDepart",
                jdbc.executeValue("dateDepart"),
                selectFlightStep.getDateDepart());
        supportSteps.checkValuePage(SelectFlightPage.PAGE_NAME,
                "wayReturn",
                jdbc.executeValue("wayReturn"),
                selectFlightStep.getWayReturn());
        supportSteps.checkValuePage(SelectFlightPage.PAGE_NAME,
                "dateReturn",
                jdbc.executeValue("dateReturn"),
                selectFlightStep.getDateReturn());
    }

    @Step("Тест страницы BookAFlight")
    private void testBookAFlightPage() {
        bookAFlightStep.initFields();
        checkValueBookAFlightPage();
        setValueBookAFlightPage();
        bookAFlightStep.clickSecurePurchaseButton();
    }

    @Step("Проверка страницы BookAFlight")
    private void checkValueBookAFlightPage() {
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "wayDepart",
                jdbc.executeValue("wayDepart"),
                bookAFlightStep.getWayDepart());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "dateDepart",
                jdbc.executeValue("dateDepart1"),
                bookAFlightStep.getDateDepart());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "flightDepart",
                jdbc.executeValue("flightDepart"),
                bookAFlightStep.getFlightDepart());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "classDepart",
                jdbc.executeValue("classDepart"),
                bookAFlightStep.getClassDepart());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceDepart",
                Integer.parseInt(jdbc.executeValue("priceDepart")),
                bookAFlightStep.getPriceDepart());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "wayReturn",
                jdbc.executeValue("wayReturn"),
                bookAFlightStep.getWayReturn());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "dateReturn",
                jdbc.executeValue("dateReturn"),
                bookAFlightStep.getDateReturn());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "flightReturn",
                jdbc.executeValue("flightReturn"),
                bookAFlightStep.getFlightReturn());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "classReturn",
                jdbc.executeValue("classReturn"),
                bookAFlightStep.getClassReturn());
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceReturn",
                Integer.parseInt(jdbc.executeValue("priceReturn")),
                bookAFlightStep.getPriceReturn());
        int totalPrice = (bookAFlightStep.getPriceDepart() + bookAFlightStep.getPriceReturn())
                * bookAFlightStep.getPassengers() + bookAFlightStep.getTaxes();
        supportSteps.checkValuePage(BookAFlightPage.PAGE_NAME,
                "priceTotal",
                Integer.parseInt(jdbc.executeValue("priceTotal")),
                totalPrice);

    }

    @Step("Установка значений на странице BookAFlight")
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

    @Step("Тест страницы FlightConfirmation")
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

    @Step("Проверка блока Depart страницы FlightConfirmation")
    private void checkDepartFlightConfirmationPage() {
        log.info("\nCheckDepart");
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "wayDepart",
                selectFlightStep.getWayDepart(),
                flightConfirmationStep.getWayDepart());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "dateDepart",
                selectFlightStep.getDateDepart(),
                flightConfirmationStep.getDateDepart());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "flightDepart",
                selectFlightStep.getFlightDepart(),
                flightConfirmationStep.getFlightDepart());
    }

    @Step("Проверка блока Return страницы FlightConfirmation")
    private void checkReturnFlightConfirmationPage() {
        log.info("\nCheckReturn");
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "wayReturn",
                selectFlightStep.getWayReturn(),
                flightConfirmationStep.getWayReturn());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "dateReturn",
                selectFlightStep.getDateReturn(),
                flightConfirmationStep.getDateReturn());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "flightReturn",
                selectFlightStep.getFlightReturn(),
                flightConfirmationStep.getFlightReturn());
    }

    @Step("Проверка блока CountPassengers страницы FlightConfirmation")
    private void checkCountPassengersFlightConfirmationPage() {
        log.info("\nCheckCountPassengers");
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "countPassengers",
                flightFinderStep.getCountPassengers(),
                flightConfirmationStep.getCountPassengers());
    }

    @Step("Проверка блока BilledTo страницы FlightConfirmation")
    private void checkBilledToFlightConfirmationPage() {
        log.info("\nCheckBilledTo");
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToFullName",
                bookAFlightStep.getBilledToFullName(),
                flightConfirmationStep.getBilledToFullName());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToAddress",
                bookAFlightStep.getBilledToAddress(),
                flightConfirmationStep.getBilledToAddress());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToCity",
                bookAFlightStep.getBilledToCity(),
                flightConfirmationStep.getBilledToCity());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToStateProvince",
                bookAFlightStep.getBilledToStateProvince(),
                flightConfirmationStep.getBilledToStateProvince());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "billedToStatePostalCode()",
                bookAFlightStep.getBilledToPostalCode(),
                flightConfirmationStep.getBilledToPostalCode());
    }

    @Step("Проверка блока Delivery страницы FlightConfirmation")
    private void checkDeliveryFlightConfirmationPage() {
        log.info("\nCheckDelivery");
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryAddress",
                bookAFlightStep.getDeliveryAddress(),
                flightConfirmationStep.getDeliveryAddress());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryCity",
                bookAFlightStep.getDeliveryCity(),
                flightConfirmationStep.getDeliveryCity());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryStateProvince",
                bookAFlightStep.getDeliveryStateProvince(),
                flightConfirmationStep.getDeliveryStateProvince());
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "deliveryStatePostalCode",
                bookAFlightStep.getDeliveryPostalCode(),
                flightConfirmationStep.getDeliveryPostalCode());
    }

    @Step("Проверка блока TotalPrice страницы FlightConfirmation")
    private void checkTotalPriceFlightConfirmationPage() {
        log.info("\nCheckTotalPrice");
        supportSteps.checkValuePage(FlightConfirmationPage.PAGE_NAME,
                "totalPrice",
                bookAFlightStep.getTotalPrice(),
                (flightConfirmationStep.getPriceDepart() + flightConfirmationStep.getPriceReturn())
                        * flightConfirmationStep.getCountPassengers()
                        + flightConfirmationStep.getTaxes());
    }

    @Step("Проверка перехода на страцицу {namePage}")
    private void checkCurrentPage(final String namePage, final String nameTitle) {
        if (nameTitle.equals(driver.getTitle())) {
            System.out.println("\nNEW PAGE  " + namePage);
            Assert.assertTrue(true);
        } else {
            System.out.println("FAIL: Current page incorrect '"
            + nameTitle + "' != '" + driver.getTitle() + "'");
            Assert.fail();
        }
    }
}
