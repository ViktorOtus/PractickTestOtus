package webdriverFactory;

public class WebDriverFactory {

    public Driver getDriver(String driverType) {
        Driver driver = null;
        switch (driverType) {
            case "chrome":
                driver = new ChromeWebDriver();
                break;
            default:
                driver = new ChromeWebDriver();
        }
        return driver;
    }
}
