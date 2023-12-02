package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name=\"Search Wikipedia\"]";
        START_APP_SKIP_BUTTON = SEARCH_INIT_ELEMENT;
        SEARCH_INPUT = SEARCH_INIT_ELEMENT;
        SEARCH_INPUT_TEXT_TPL = "xpath://XCUIElementTypeSearchField[@name=\"{SUBSTRING}\"]";
        SEARCH_BY_TEXT_TPL = "xpath://XCUIElementTypeStaticText[@name=\"{SUBSTRING}\"]";
        CLOSE_SEARCH_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Cancel\"]";
        PAGE_LIST_ID = "xpath://XCUIElementTypeStaticText[contains(@name, \"Cinnamon\")][{SUBSTRING}]";
        TITLE_AND_DESCRIPTION_SEARCH_RESULT_TPL = "xpath://XCUIElementTypeOther [XCUIElementTypeStaticText[1][@name=\"{SUBSTRING_TITTLE}\"]][XCUIElementTypeStaticText[2][contains(@name, '{SUBSTRING_DESC}')]]";

    }


    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
