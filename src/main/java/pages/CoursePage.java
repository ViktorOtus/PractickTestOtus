package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CoursePage extends PageObject {

    WebDriver driver;

    public CoursePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By courseTitle = By.cssSelector(".course-header2__title");
    private final By courseDescription = By.cssSelector(".course-header2__subtitle");
    private final By courseDuration = By.xpath("//p[text()[contains(., 'Длительность обучения')]]/../../..//p[@class=\"course-header2-bottom__item-text\"]");
    private final By courseFormat = By.xpath("//p[text()[contains(., 'Формат')]]/../../..//p[@class=\"course-header2-bottom__item-text\"]");

    public String getCourseTitle() {
        return driver.findElement(courseTitle).getText().trim();
    }

    public String getCourseDescription() {
        return driver.findElement(courseDescription).getText().trim();
    }

    public String getCourseDuration() {
        return driver.findElement(courseDuration).getText().trim();
    }

    public String getCourseFormat() {
        return driver.findElement(courseFormat).getText().trim();
    }

    public boolean courseHasParameters(String title, String description, String duration, String format) {
        return getCourseTitle().equals(title) &&
                getCourseDescription().equals(description) &&
                getCourseDuration().equals(duration) &&
                getCourseFormat().equals(format);
    }
}
