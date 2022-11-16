import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CoursePage;
import pages.EventsCalendarPage;
import pages.HomePage;
import pages.TestingCategoryPage;
import webdriverFactory.WebDriverFactory;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class OtusTests extends BaseTest {

    private WebDriver driver;
    private final static Logger logger = LogManager.getLogger(OtusTests.class);
    private HomePage homePage;
    private EventsCalendarPage eventsCalendarPage;
    private TestingCategoryPage categoryPage;
    private CoursePage javaQAEngineerBasicCoursePage;

    @BeforeMethod
    public void setUp() {
        // Create the driver object
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getDriver("chrome").getDriver();
        driver.get (baseUrl);
    }

    @AfterMethod
    public void after() throws NullPointerException {
        try {
            driver.close();
        } catch (NullPointerException ignored) { }
    }

    private static final int NUMBER_OF_TESTING_COURSES= 14;
    public static final String JAVA_QA_ENGINEER_BASIC_COURSE_TITLE = "Java QA Engineer. Basic";
    public static final String JAVA_QA_ENGINEER_BASIC_COURSE_DESCRIPTION = "Автоматизация тестирования на Java с нуля";
    public static final String JAVA_QA_ENGINEER_BASIC_COURSE_DURATION = "4 месяца";
    public static final String ONLINE_COURSE_FORMAT = "Online";

    @Test
    public void checkNumberOfTestingCourses() {
        logger.info("Test checkNumberOfTestingCourses start");
        homePage = new HomePage(driver);
        categoryPage = new TestingCategoryPage(driver);
        homePage.navigateToCategory("Тестирование");
        assertEquals(categoryPage.getNumberOfCourses(), NUMBER_OF_TESTING_COURSES);
        logger.info("Test checkNumberOfTestingCourses completed");
    }

    @Test
    public void checkCourseInformation() {
        logger.info("Test checkCourseInformation start");
        homePage = new HomePage(driver);
        categoryPage = new TestingCategoryPage(driver);
        javaQAEngineerBasicCoursePage = new CoursePage(driver);
        homePage.navigateToCategory("Тестирование");
        categoryPage.navigateToCoursePage(JAVA_QA_ENGINEER_BASIC_COURSE_TITLE);
        assertTrue(javaQAEngineerBasicCoursePage.courseHasParameters(
                JAVA_QA_ENGINEER_BASIC_COURSE_TITLE,
                JAVA_QA_ENGINEER_BASIC_COURSE_DESCRIPTION,
                JAVA_QA_ENGINEER_BASIC_COURSE_DURATION,
                ONLINE_COURSE_FORMAT));
        logger.info("Test checkCourseInformation completed");
    }

    @Test
    public void eventsDatesValidation() {
        logger.info("Test eventsDatesValidation start");
        homePage = new HomePage(driver);
        eventsCalendarPage = homePage.navigateToEventsCalendarPage();
        assertTrue(eventsCalendarPage.getNumberOfEvents() > 0);
        assertTrue(eventsCalendarPage.eventsDatesAreInFuture());
        logger.info("Test eventsDatesValidation completed");
    }

    @Test
    public void filterByTypeValidation() {
        logger.info("Test filterByTypeValidation start");
        homePage = new HomePage(driver);
        eventsCalendarPage = homePage.navigateToEventsCalendarPage();
        eventsCalendarPage.filterEventsByType("Открытый вебинар");
        assertTrue(eventsCalendarPage.eventsAreFilteredBy("Открытый вебинар"));
        logger.info("Test filterByTypeValidation completed");
    }
}
