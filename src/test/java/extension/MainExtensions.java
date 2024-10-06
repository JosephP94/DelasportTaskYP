package extension;

import com.google.gson.Gson;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainExtensions {

    private static final Gson GSON = new Gson();
    /**
     * Converts a JSON string into an object of the specified class type.
     *
     * @param json the JSON string to convert
     * @param clazz the class type to convert to
     * @param <T> the type of the class
     * @return the converted object of type T
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static void waitForJSToLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                // Check if jQuery is loaded
                JavascriptExecutor jsExecutor = (JavascriptExecutor) d;
                Boolean jQueryActive = (Boolean) jsExecutor.executeScript("return window.jQuery !== undefined && jQuery.active === 0;");

                // Check if JavaScript is loaded (page is ready)
                Boolean jsReady = (Boolean) jsExecutor.executeScript("return document.readyState === 'complete';");

                return jQueryActive && jsReady;
            }
        });
    }
}
