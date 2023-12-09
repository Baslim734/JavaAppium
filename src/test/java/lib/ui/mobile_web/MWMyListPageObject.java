package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListsPageObject {
    static {
        ARTICLE_IN_LIST = "xpath://*[contains(@title, 'Cinnamon')]";
        FOLDER_LIST_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
        ARTICLE_TITTLE_TPL = "xpath://*[contains(@title, 'Cinnamon')]";
    }


    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
