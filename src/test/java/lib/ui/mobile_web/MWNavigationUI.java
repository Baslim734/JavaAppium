package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        BUTTON_BACK = "xpath://div[@class='header-action']";
        NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE = "css:label#mw-mf-main-menu-button";
        NAVIGATION_BAR_SAVED_BUTTON = "xpath://a[contains(@class,'toggle-list-item__anchor') and contains(@href,'/wiki/Special:Watchlist')]";
    }


    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
