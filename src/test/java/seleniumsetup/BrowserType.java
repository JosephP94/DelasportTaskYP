package seleniumsetup;

public enum BrowserType {
    FIREFOX("firefox"),
    CHROME("chrome");

    private final String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }
}