package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        RESULT_ARTICLE_TITTLE_ID = "id:org.wikipedia:id/page_list_item_title";
        BUTTON_SAVE_ID = "id:Save for later";
        BUTTON_ADD_TO_LIST = "id:Add “Cinnamon” to a reading list?";
        BUTTON_CREATE_NEW_LIST = "xpath://XCUIElementTypeStaticText[@name=\"Create a new list\"]";
        INPUT_LIST_NAME_STRING = "xpath://XCUIElementTypeTextField[@value=\"reading list title\"]";
        BUTTON_OK = "xpath://XCUIElementTypeButton[@name=\"Create reading list\"]";
        SEARCH_BY_TEXT_TPL = "xpath://XCUIElementTypeStaticText[@name=\"{SUBSTRING}\"]";
        BUTTON_BACK = "id:Search";
        ARTICLE_TITTLE_ID_TPL = "xpath://XCUIElementTypeOther[contains(@name, \"Cinnamon\")]";

    }

    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
