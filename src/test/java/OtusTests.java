import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.EventsCalendarPage;
import pages.JavaQAEngineerBasicCoursePage;
import pages.TestingCategoryPage;
import pages.HomePage;
import utils.DateTimeUtils;

import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static pages.JavaQAEngineerBasicCoursePage.*;

public class OtusTests extends BaseTest {

    WebDriver driver;
    final static Logger logger = LogManager.getLogger(OtusTests.class);
    HomePage homePage;
    EventsCalendarPage eventsCalendarPage;
    TestingCategoryPage categoryPage;
    JavaQAEngineerBasicCoursePage javaQAEngineerBasicCoursePage;

    @BeforeMethod
    public void setUp() {
        // Set up the WebDriverManager for chrome driver
        WebDriverManager.chromedriver().setup();
        // Create the driver object
        driver = new ChromeDriver();
        driver.get (baseUrl);
    }

    @AfterMethod
    public void after() { driver.close(); }

    private static final int NUMBER_OF_TESTING_COURSES= 14;

    @Test
    public void checkNumberOfTestingCourses() {
        logger.info("Test checkNumberOfTestingCourses start");
        homePage = new HomePage(driver);
        categoryPage = new TestingCategoryPage(driver);
        driver.findElement(homePage.getTestingCourseTabLink()).click();
        assertEquals(categoryPage.getCourseCardElements().size(), NUMBER_OF_TESTING_COURSES);
        logger.info("Test checkNumberOfTestingCourses completed");
    }

    @Test
    public void checkCourseInformation() {
        logger.info("Test checkCourseInformation start");
        homePage = new HomePage(driver);
        categoryPage = new TestingCategoryPage(driver);
        javaQAEngineerBasicCoursePage = new JavaQAEngineerBasicCoursePage(driver);
        driver.findElement(homePage.getTestingCourseTabLink()).click();
        categoryPage.getCourseCardElementByName(JAVA_QA_ENGINEER_BASIC_COURSE_TITLE).click();
        assertEquals(javaQAEngineerBasicCoursePage.getCourseTitle(), JAVA_QA_ENGINEER_BASIC_COURSE_TITLE);
        assertEquals(javaQAEngineerBasicCoursePage.getCourseDescription(), JAVA_QA_ENGINEER_BASIC_COURSE_DESCRIPTION);
        assertEquals(javaQAEngineerBasicCoursePage.getCourseDuration(), JAVA_QA_ENGINEER_BASIC_COURSE_DURATION);
        assertEquals(javaQAEngineerBasicCoursePage.getCourseFormat(), JAVA_QA_ENGINEER_BASIC_COURSE_FORMAT);
        logger.info("Test checkCourseInformation completed");
    }

    @Test
    public void eventsDatesValidation() {
        logger.info("Test eventsDatesValidation start");
        homePage = new HomePage(driver);
        eventsCalendarPage = homePage.navigateToEventsCalendarPage();
        assertTrue(eventsCalendarPage.getEvents().size() > 0);
        eventsCalendarPage.getEventsDates().forEach(date -> {
            assertTrue(dateIsInFuture(date.getText()));
        });
        logger.info("Test eventsDatesValidation completed");
    }

    @Test
    public void filterByTypeValidation() {
        logger.info("Test filterByTypeValidation start");
        homePage = new HomePage(driver);
        eventsCalendarPage = homePage.navigateToEventsCalendarPage();
        eventsCalendarPage.filterEventsByType("Открытый вебинар");
        boolean eventsAreFiltered = eventsCalendarPage.getEventsTypes().stream()
                .allMatch(webElement -> webElement.getText().equals("Открытый вебинар"));
        assertTrue(eventsAreFiltered);
        logger.info("Test filterByTypeValidation completed");
    }

    private boolean dateIsInFuture(String date) {
        return DateTimeUtils.getDateByStringDDMon(date).isAfter(LocalDate.now());
    }
}
