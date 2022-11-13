import org.emredzx.Log;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class BaseTestLog implements TestWatcher {

    Log log = new Log();

    @Override
    public void testSuccessful(ExtensionContext context) {
        String methodName = context.getDisplayName();
        log.info(methodName + " method is " + "PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String methodName = context.getDisplayName();
        log.error(methodName + " method is " + " FAILED with reason: " + cause.getMessage());
    }
}
