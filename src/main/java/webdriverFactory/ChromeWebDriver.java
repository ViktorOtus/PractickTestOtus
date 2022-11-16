package webdriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver implements Driver {

    public WebDriver getDriver() {
        // Set up the WebDriverManager for chrome driver
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
