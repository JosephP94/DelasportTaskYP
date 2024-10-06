package seleniumsetup;

import java.util.ResourceBundle;

public class BaseSetup {
    private static ResourceBundle prop = ResourceBundle.getBundle("dev");
    protected static String loadProperty(String key) {
        return prop.getString(key);
    }
}