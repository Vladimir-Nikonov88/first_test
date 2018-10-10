import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

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
