import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    private static String login = "tutorial";
    private static String password = "tutorial";
    private static WebDriver driver;
    private static WelcomePage welcomePage;
    private static FlightFinderPage flightFinderPage;
    private static SelectFlightPage selectFlightPage;
    private static BookAFlightPage bookAFlightPage;
    private static FlightConfirmationPage flightConfirmationPage;


    @BeforeClass
    public static void setub() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        welcomePage = new WelcomePage(driver);
        flightFinderPage = new FlightFinderPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //   http://newtours.demoaut.com/
        driver.get("http://newtours.demoaut.com/");
    }


//    @AfterClass
//    public static void tearDown() {
//        driver.quit();
//    }

    @Test
    public void generalTest() {
        checkCurrentPage(WelcomePage.PAGE_NAME, WelcomePage.TITLE);
        setWelcomePage();

        checkCurrentPage(FlightFinderPage.PAGE_NAME, FlightFinderPage.TITLE);
        setFlightFinderPage();

        checkCurrentPage(SelectFlightPage.PAGE_NAME, SelectFlightPage.TITLE);
        setSelectFlightPage();

        checkCurrentPage(BookAFlightPage.PAGE_NAME, BookAFlightPage.TITLE);
        testBookAFlightPage();

        checkCurrentPage(FlightConfirmationPage.PAGE_NAME, FlightConfirmationPage.TITLE);
        checkFlightConfirmationPage();

        checkCurrentPage(WelcomePage.PAGE_NAME, WelcomePage.TITLE);

        Assert.assertTrue(true);
    }

    private void setWelcomePage() {
        welcomePage.singIn(login, password);
        System.out.printf("Input LOGIN :%s PASSWORD: %s%n",login, password);
        System.out.println("Click SingIn Button");
        flightFinderPage = welcomePage.initFlightFinderPage();
    }

    private void setFlightFinderPage() {
        flightFinderPage.setType(FlightFinderPage.Type.One_Way);
        flightFinderPage.setPassengers(2);
        flightFinderPage.setDepartingFrom("Paris");
        flightFinderPage.setOn("November", "20");
        flightFinderPage.setArrivingIn("Seattle");
        flightFinderPage.setReturning("December", "19");
        flightFinderPage.setServiceClass(FlightFinderPage.ServiceClass.BusinessClass);
        flightFinderPage.setAirline("Pangea Airlines");
        System.out.println("Set values " + FlightFinderPage.PAGE_NAME);
        flightFinderPage.clickContinueImage();
        System.out.println("Click Continue Button");
        selectFlightPage = flightFinderPage.initSelectFlightPage();
    }

    private void setSelectFlightPage() {
        setAirlinesSelectFlightPage();
        System.out.println("Set values " + FlightFinderPage.PAGE_NAME);
        checkValueSelectFlightPage();
        selectFlightPage.clickContinue();
        System.out.println("Click Continue Button");
        bookAFlightPage = selectFlightPage.initBookAFlightPage();
    }

    private void checkValueSelectFlightPage() {
            if (!(checkValuePage(SelectFlightPage.PAGE_NAME, "wayDepart", "Paris to Seattle", selectFlightPage.getWayDepart())&
                    checkValuePage(SelectFlightPage.PAGE_NAME, "dateDepart", "11/20/2018", selectFlightPage.getDateDepart())&
                    checkValuePage(SelectFlightPage.PAGE_NAME, "priceDepart", 281, selectFlightPage.getPriceDepart())&
                    checkValuePage(SelectFlightPage.PAGE_NAME, "wayReturn", "Seattle to Paris", selectFlightPage.getWayReturn())&
                    checkValuePage(SelectFlightPage.PAGE_NAME, "dateReturn", "12/19/2018", selectFlightPage.getDateReturn())&
                    checkValuePage(SelectFlightPage.PAGE_NAME, "priceDepart", 273, selectFlightPage.getPriceReturn()))){
            Assert.fail();
        }
    }

    private void setAirlinesSelectFlightPage() {
        selectFlightPage.setDepartFligth("Unified Airlines 363");
        selectFlightPage.setReturnFligth("Blue Skies Airlines 631");
    }

    private void testBookAFlightPage() {
        checkValueBookAFlightPage();
        setValueBookAFlightPage();
        System.out.println("Set values " + BookAFlightPage.PAGE_NAME);
        bookAFlightPage.clickSecurePurchase();
        System.out.println("Click SecurePurchase Button");
        flightConfirmationPage = bookAFlightPage.initFlightConfirmationPage();
    }

    private void checkValueBookAFlightPage() {
        int totalPrice = (bookAFlightPage.getPriceDepart()+bookAFlightPage.getPriceReturn())*
                bookAFlightPage.getPassengers() + bookAFlightPage.getTaxes();
        if (!(checkValuePage(BookAFlightPage.PAGE_NAME, "wayDepart", "Paris to Seattle", bookAFlightPage.getWayDepart())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "dateDepart", "11/20/2018", bookAFlightPage.getDateDepart())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "flightDepart", "Unified Airlines 363", bookAFlightPage.getFlightDepart())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "classDepart", "Business", bookAFlightPage.getClassDepart())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "priceDepart", 281, bookAFlightPage.getPriceDepart())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "wayReturn", "Seattle to Paris", bookAFlightPage.getWayReturn())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "dateReturn", "12/19/2018", bookAFlightPage.getDateReturn())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "flightReturn", "Blue Skies Airlines 631", bookAFlightPage.getFlightReturn())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "classReturn", "Business", bookAFlightPage.getClassReturn())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "priceReturn", 273, bookAFlightPage.getPriceReturn())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "totalPrice", 1199, bookAFlightPage.getTotalPrice())&
        checkValuePage(BookAFlightPage.PAGE_NAME, "totalPrice", 1199, totalPrice))){
            Assert.fail();
        }
    }

    private void setValueBookAFlightPage() {
        bookAFlightPage.setPassengers(1, "Ivanov", "Ivan", "Hindu");
        bookAFlightPage.setPassengers(2, "Ivanova", "Irina", "Bland");

        bookAFlightPage.setCreditCard("Visa", "5479540454132487", "05", "2009",
                                        "Ivan", "Ivanovich", "Ivanov");

        bookAFlightPage.setAddress(false, "1085 Borregas Ave.", "Albuquerque", "New Mexico",
                                    "94089", "UNITED STATES", BookAFlightPage.AddressType.Billing);

        bookAFlightPage.setAddress(true,"1225 Borregas Ave.", "Boston", "Massachusetts",
                                    "91089", "UNITED STATES", BookAFlightPage.AddressType.Delivery);
    }

    private void checkFlightConfirmationPage(){
        if (!(checkDepartFlightConfirmationPage()&&
        checkReturnFlightConfirmationPage()&&
        checkCountPassengersFlightConfirmationPage()&&
        checkBilledToFlightConfirmationPage()&&
        checkDeliveryFlightConfirmationPage()&&
        checkTotalPriceFlightConfirmationPage())) {
            Assert.fail();
        }
        flightConfirmationPage.clickBottonBackToHome();
        System.out.println("Click BackToHome Button");
    }

    private boolean checkDepartFlightConfirmationPage() {
        System.out.println("checkDepart");
        if (checkValuePage(FlightConfirmationPage.PAGE_NAME, "wayDepart", selectFlightPage.getWayDepart(), flightConfirmationPage.getWayDepart())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "dateDepart", selectFlightPage.getDateDepart(), flightConfirmationPage.getDateDepart())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "flightDepart", selectFlightPage.getFligthDepart(), flightConfirmationPage.getFlightDepart())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkReturnFlightConfirmationPage() {
        System.out.println("checkReturn");
        if (checkValuePage(FlightConfirmationPage.PAGE_NAME, "wayReturn", selectFlightPage.getWayReturn(), flightConfirmationPage.getWayReturn())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "dateReturn", selectFlightPage.getDateReturn(), flightConfirmationPage.getDateReturn())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "flightReturn", selectFlightPage.getFligthReturn(), flightConfirmationPage.getFlightReturn())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCountPassengersFlightConfirmationPage() {
        System.out.println("checkCountPassengers");
        if (checkValuePage(FlightConfirmationPage.PAGE_NAME, "countPassengers", flightFinderPage.getCountPassengers(), flightConfirmationPage.getCountPassengers())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBilledToFlightConfirmationPage() {
        System.out.println("checkBilledTo");
        if (checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToFullName", bookAFlightPage.getBilledToFullName(), flightConfirmationPage.getBilledToFullName())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToAddress", bookAFlightPage.getBilledToAddress(), flightConfirmationPage.getBilledToAddress())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToCity", bookAFlightPage.getBilledToCity(), flightConfirmationPage.getBilledToCity())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToStateProvince", bookAFlightPage.getBilledToStateProvince(), flightConfirmationPage.getBilledToStateProvince())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "billedToStatePostalCode()", bookAFlightPage.getBilledToPostalCode(), flightConfirmationPage.getBilledToPostalCode())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDeliveryFlightConfirmationPage() {
        System.out.println("checkDelivery");
        if (checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryAddress", bookAFlightPage.getDeliveryAddress(), flightConfirmationPage.getDeliveryAddress())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryCity", bookAFlightPage.getDeliveryCity(), flightConfirmationPage.getDeliveryCity())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryStateProvince", bookAFlightPage.getDeliveryStateProvince(), flightConfirmationPage.getDeliveryStateProvince())&&
        checkValuePage(FlightConfirmationPage.PAGE_NAME, "deliveryStatePostalCode", bookAFlightPage.getDeliveryPostalCode(), flightConfirmationPage.getDeliveryPostalCode())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkTotalPriceFlightConfirmationPage() {
        System.out.println("checkTotalPrice");
        if (checkValuePage(FlightConfirmationPage.PAGE_NAME, "totalPrice", bookAFlightPage.getTotalPrice(),
                (flightConfirmationPage.getPriceDepart() + flightConfirmationPage.getPriceReturn()) * flightConfirmationPage.getCountPassengers() +
                        flightConfirmationPage.getTaxes())) {
            return true;
        } else {
            return false;
        }
    }

    private void checkCurrentPage(String namePage, String nameTitle){
        if (nameTitle.equals(driver.getTitle())){
            System.out.println("\nNEW PAGE  " + namePage);
        } else {
            System.out.println("FAIL: Current page incorrect");
            Assert.fail();
        }
    }

    private boolean checkValuePage(String namePage, String nameVariable, Object value, Object field) {
        String mark = "";
        if (value instanceof String && field instanceof String) {
            mark = "%s";
        } else if (value instanceof Integer && field instanceof Integer) {
            mark = "%d";
        } else {
            throw new IllegalArgumentException();
        }
        if (value.equals(field)) {
            System.out.printf("CORRECT Check value Page: %s, name: %s \tvalue = "+mark+", field = "+mark+"\n", namePage, nameVariable, value, field);
            return true;
        } else {
            System.out.printf("INCORRECT! Check value Page: %s, name: %s \tvalue = "+mark+", field = "+mark+"\n", namePage, nameVariable, value, field);
            return false;
        }
    }
}
