package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaQAEngineerBasicCoursePage {

    public static final String JAVA_QA_ENGINEER_BASIC_COURSE_TITLE = "Java QA Engineer. Basic";
    public static final String JAVA_QA_ENGINEER_BASIC_COURSE_DESCRIPTION = "Автоматизация тестирования на Java с нуля";
    public static final String JAVA_QA_ENGINEER_BASIC_COURSE_DURATION = "4 месяца";
    public static final String JAVA_QA_ENGINEER_BASIC_COURSE_FORMAT = "Online";

    WebDriver driver;

    public JavaQAEngineerBasicCoursePage(WebDriver driver) {
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
}
