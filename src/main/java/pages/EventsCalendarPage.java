package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DateTimeUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class EventsCalendarPage extends PageObject {

    WebDriver driver;
    WebDriverWait wait;
    final static Logger logger = LogManager.getLogger(EventsCalendarPage.class);

    public EventsCalendarPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(500L));
    }

    private final By allEventsTypesDropdownLink = By.cssSelector("div.dod_new-events__header-left div.dod_new-events-dropdown__input");
    private final By eventsDropdownType = By.cssSelector("[class*='opened'] [class*='list-item']");
    private final By events = By.cssSelector(".dod_new-events__list a");
    private final By eventDate = By.cssSelector("span.dod_new-event__time-item:first-child");
    private final By eventType = By.cssSelector(".dod_new-event-content .dod_new-type__text");

    public List<WebElement> getEventsDates() {
        return driver.findElements(eventDate);
    }

    public List<WebElement> getEventsTypes() {
        return driver.findElements(eventType);
    }

    public List<WebElement> getEvents() {
        return driver.findElements(events);
    }

    public int getNumberOfEvents() {
        return getEvents().size();
    }

    public void filterEventsByType(String type) {
        logger.info("Waiting for event types dropdown to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(allEventsTypesDropdownLink));
        driver.findElement(allEventsTypesDropdownLink).click();
        driver.findElements(eventsDropdownType).stream()
                .filter(dropdownType -> dropdownType.getText().equals(type))
                .findFirst().ifPresent(WebElement::click);
    }

    public boolean eventsDatesAreInFuture() {
        return getEventsDates().stream().allMatch(date -> dateIsInFuture(date.getText()));
    }

    public boolean eventsAreFilteredBy(String eventType) {
        return getEventsTypes().stream().allMatch(webElement -> webElement.getText().equals(eventType));
    }
}
