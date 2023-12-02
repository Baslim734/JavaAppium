package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        BUTTON_BACK = "id:Search";
        NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE = "id:Explore";
        NAVIGATION_BAR_SAVED_BUTTON = "id:Saved";
    }


    public iOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

}
