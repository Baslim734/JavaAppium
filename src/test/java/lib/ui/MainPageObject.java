package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.CoreTestCase;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject extends CoreTestCase {

    protected static RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        element.click();
        return element;
    }

    public WebElement waitForElementWithTextAndClick(String locator, String text, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        String tittle_element = element.getAttribute("text");
        assertEquals(
                error_message,
                text,
                tittle_element
        );
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText(String locator, String textInElement, String error_message) {
        WebElement tittle_element = waitForElementPresent(
                locator, error_message
        );
        String article_tittle;
        if (Platform.getInstance().isIOS()) {
            article_tittle = tittle_element.getAttribute("name");
        } else {
            article_tittle = tittle_element.getAttribute("text");
        }

        assertEquals(
                error_message,
                textInElement,
                article_tittle
        );
        return tittle_element;
    }

    protected void swipeUp(WaitOptions timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((PerformsTouchActions) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);
            action.press(PointOption.point(x, start_y)).waitAction(timeOfSwipe).moveTo(PointOption.point(x, end_y)).release().perform();
        } else {
            System.out.println("Method swipeUp does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick() {
        swipeUp(WaitOptions.waitOptions(Duration.ofSeconds(200)));
    }

    protected void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).isEmpty()) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((PerformsTouchActions) driver);
            action
                    .press(PointOption.point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(150)))
                    .moveTo(PointOption.point(left_x, middle_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    public void waitForElementsAndClick(String locator, int elementIndex, String errorMessage) {
        waitForElementPresent(locator, errorMessage);
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        if (elementIndex < elements.size()) {
            WebElement element = elements.get(elementIndex);
            element.click();
        } else {
            fail("Cannout find element with index =  " + elementIndex);
        }
    }

    public void waitForElementsAndClickWithText(String locator, int elementIndex, String errorMessage) {
        waitForElementPresent(locator, errorMessage);
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        if (elementIndex < elements.size()) {
            WebElement element = elements.get(elementIndex);
            String text = element.getText();
            assertFalse("There is no text in element", element.getText() == null);
            System.out.println("Text in element: " + text);
            element.click();
        } else {
            fail("Cannot find element with index =  " + elementIndex);
        }
    }

    public void assertElementPresent(String locator, String error_message) {
        By by = this.getLocatorByString(locator);
        List element = driver.findElements(by);
        assertFalse(error_message, element.isEmpty());
    }

    public void checkForElementIsEnabled(String locator) {
        By by = this.getLocatorByString(locator);
        WebElement element = driver.findElement(by);
        assertTrue("Element is not enabled", element.isEnabled());
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals(("xpath"))) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of lcator: " + locator_with_type);
        }
    }
}
