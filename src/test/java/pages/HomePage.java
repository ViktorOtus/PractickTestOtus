package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By eventsTab = By.xpath("//header//p[text()[contains(.,'События')]]");
    private final By eventsCalendarDropdownLink = By.cssSelector("header a[title='Календарь мероприятий']");
    private final By testingCourseTabLink = By.cssSelector("#categories_id a[title='Тестирование']");

    public By getTestingCourseTabLink() {
        return testingCourseTabLink;
    }

    public EventsCalendarPage navigateToEventsCalendarPage() {
        driver.findElement(eventsTab).click();
        driver.findElement(eventsCalendarDropdownLink).click();
        return new EventsCalendarPage(driver);
    }
}
