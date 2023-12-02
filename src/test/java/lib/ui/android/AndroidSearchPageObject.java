package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        START_APP_SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_BY_TEXT_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
        CLOSE_SEARCH_BUTTON = "id:org.wikipedia:id/search_close_btn";
        PAGE_LIST_ID = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_INPUT_TEXT_TPL = "xpath://android.widget.ImageView[@content-desc=\"{SUBSTRING}\"]";
        TITLE_AND_DESCRIPTION_SEARCH_RESULT_TPL = "xpath://android.view.ViewGroup[.//android.widget.TextView[contains(@text, '{SUBSTRING_TITTLE}')] and .//android.widget.TextView[contains(@text, '{SUBSTRING_DESC}')]]";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
