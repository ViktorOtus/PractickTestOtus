package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageObject {

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public EventsCalendarPage navigateToEventsCalendarPage() {
        return navigateToTheLinkOfTab("События", "Календарь мероприятий");
    }
}
