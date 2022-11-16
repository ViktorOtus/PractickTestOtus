package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DateTimeUtils;

import java.time.LocalDate;

public abstract class PageObject {

    WebDriver driver;
    private final String tabLocator = "//header//p[text()[contains(.,'%s')]]";
    private final String dropdownLocator = "header a[title='%s']";
    protected final String courseTabLink = "#categories_id a[title='%s']";

    public EventsCalendarPage navigateToTheLinkOfTab(String tabName, String link) {
        driver.findElement(By.xpath(String.format(tabLocator, tabName))).click();
        driver.findElement(By.cssSelector(String.format(dropdownLocator, link))).click();
        return new EventsCalendarPage(driver);
    }

    public By getCategoryCourseTabLink(String tabName) {
        return By.cssSelector(String.format(courseTabLink, tabName));
    }

    public void navigateToCategory(String tabName) {
        driver.findElement(getCategoryCourseTabLink(tabName)).click();
    }

    public boolean dateIsInFuture(String date) {
        return !DateTimeUtils.getDateByStringDDMon(date).isBefore(LocalDate.now());
    }
}
