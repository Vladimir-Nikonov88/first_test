import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * Class MyRule.
 */
public class MyRule extends TestWatcher {

    private String path = "src/test/screen/";
    private static Logger log = Logger.getLogger(MyRule.class.getName());

    public MyRule() {
        super();
    }

    /**
     *
     * @param description
     */
    @Override
    protected void succeeded(final Description description) {
        System.out.println("succeeded");
    }

    /**
     *
     * @param description
     */
    @Override
    protected void starting(final Description description) {
        System.out.println("starting");
    }

    /**
     *
     * @param description
     */
    @Override
    protected void failed(final Throwable e, final Description description) {
        System.out.println("failed");
        WebDriver augmentedDriver = new Augmenter().augment(FirstTest.getDriver());
        File screenshot = null;
        if (augmentedDriver instanceof TakesScreenshot) {
            screenshot = ((TakesScreenshot) augmentedDriver).
                    getScreenshotAs(OutputType.FILE);
        } else {
            log.warning("Failed to create fail-screenshot");
        }
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy_MM_dd___HH_mm_ss");
        try {
            FileUtils.copyFile(screenshot, new File(path + formatForDateNow.format(dateNow) + ".png"));
        } catch (IOException ex) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param description
     */
    @Override
    protected void finished(final Description description) {
        System.out.println("finished");
    }
}
