package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        RESULT_ARTICLE_TITTLE_ID = "id:org.wikipedia:id/page_list_item_title";
        BUTTON_SAVE_ID = "id:org.wikipedia:id/page_save";
        BUTTON_ADD_TO_LIST = "xpath://*[contains(@text,'Add to list')]";
        INPUT_LIST_NAME_STRING = "xpath://*[contains(@text,'Name of this list')]";
        BUTTON_OK = "xpath://*[contains(@text,'OK')]";
        SEARCH_BY_TEXT_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
        BUTTON_BACK = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        ARTICLE_TITTLE_ID_TPL = "xpath:/android.view.View[contains(@content-desc,'{SUBSTRING}')]";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
