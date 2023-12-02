package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        WEB_MENU_BUTTON = "css:label#mw-mf-main-menu-button";
        WEB_LOGIN_BUTTON = "xpath://a[@class=\"toggle-list-item__anchor \" and span[@class=\"toggle-list-item__label\" and text()=\"Log in\"]]";
        WEB_LOGIN_INPUT = "xpath://input[@id=\"wpName1\"]";
        WEB_PASSWORD_INPUT = "xpath://input[@id=\"wpPassword1\"]";
        WEB_AUTHORIZE = "xpath://button[@id='wpLoginAttempt']";
        PASSWORD = "Starcraft2734!";
        LOGIN = "Wikitest12345673481616";
        //START_APP_SKIP_BUTTON = SEARCH_INIT_ELEMENT;
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_INPUT_TEXT_TPL = "xpath://div[contains(@class,'wikipedia-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_BY_TEXT_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        CLOSE_SEARCH_BUTTON = "css:button.cancel";
        PAGE_LIST_ID = "xpath://li[contains(@title, 'Cinnamon')]";
        TITLE_AND_DESCRIPTION_SEARCH_RESULT_TPL = "xpath://XCUIElementTypeOther [XCUIElementTypeStaticText[1][@name=\"{SUBSTRING_TITTLE}\"]][XCUIElementTypeStaticText[2][contains(@name, '{SUBSTRING_DESC}')]]";

    }


    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
