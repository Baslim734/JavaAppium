package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_IN_LIST = "id:org.wikipedia:id/page_list_item_title";
        FOLDER_LIST_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
        ARTICLE_TITTLE_TPL = "xpath://h1[contains(@class, 'firstHeading') and contains(span, 'Java')]";
    }


    public iOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
