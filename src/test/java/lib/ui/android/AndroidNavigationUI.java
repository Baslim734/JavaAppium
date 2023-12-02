package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        BUTTON_BACK = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE = "id:org.wikipedia:id/navigation_bar_item_active_indicator_view";
        NAVIGATION_BAR_SAVED_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";
    }


    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}
