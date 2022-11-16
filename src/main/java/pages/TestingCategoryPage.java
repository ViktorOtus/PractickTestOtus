package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestingCategoryPage extends PageObject {

    WebDriver driver;

    public TestingCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By lessonBlock = By.cssSelector("div.lessons");

    private WebElement getLessonBlockElement() {
        return driver.findElement(lessonBlock);
    }
    public List<WebElement> getCourseCardElements() {
        return getLessonBlockElement().findElements(By.cssSelector("a"));
    }

    public int getNumberOfCourses() {
        return getCourseCardElements().size();
    }

    public WebElement getCourseCardElementByName(String courseName) {
        String courseSelector = "//div[text()[contains(.,'%s')]]/../..";
        return getLessonBlockElement()
                .findElement(By.xpath(String.format(courseSelector, courseName)));
    }

    public void navigateToCoursePage(String courseName) {
        getCourseCardElementByName(courseName).click();
    }
}
