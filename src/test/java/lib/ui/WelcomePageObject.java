package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            LEARN_MORE_LINK_ID = "id:The free encyclopedia",
            NEXT_BUTTON_ID = "id:Next",
            NEW_WAY_TO_EXPLORE_ID = "id:New ways to explore",
            SEARCH_ON_MANY_LANGUAGES_ID = "id:Search in nearly 300 languages",
            HELP_TO_IMPROVE_APP_ID = "id:Help make the app better",
            START_BUTTON_ID = "id:Get started",
            SKIP_BUTTON = "xpath://XCUIElementTypeButton[@name='Skip']";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(LEARN_MORE_LINK_ID, "Cannot find Learn more about wikipedia link", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BUTTON_ID, "Cannot find  next button");
    }

    public void waitForNewWayToExplore() {
        this.waitForElementPresent(NEW_WAY_TO_EXPLORE_ID, "Cannot find new way to explore link", 10);
    }

    public void waitForSearchOnManyLanguages() {
        this.waitForElementPresent(SEARCH_ON_MANY_LANGUAGES_ID, "Cannot find search on many languages", 10);
    }

    public void waitForHelpToImproveApp() {
        this.waitForElementPresent(HELP_TO_IMPROVE_APP_ID, "Cannot find help to improve app", 10);
    }

    public void clickStartButton() {
        this.waitForElementAndClick(START_BUTTON_ID, "Cannot find  start button");
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find SKIP button");
    }

}
