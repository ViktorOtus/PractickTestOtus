package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestingCategoryPage {

    WebDriver driver;

    public TestingCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By lessonBlock = By.cssSelector("div.lessons");
    private String courseSelector = "//div[text()[contains(.,'%s')]]/../..";

    private WebElement getLessonBlockElement() {
        return driver.findElement(lessonBlock);
    }
    public List<WebElement> getCourseCardElements() {
        return getLessonBlockElement().findElements(By.cssSelector("a"));
    }

    public WebElement getCourseCardElementByName(String courseName) {
        return getLessonBlockElement()
                .findElement(By.xpath(String.format(courseSelector, courseName)));
    }
}
