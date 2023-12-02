package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.NavigationUIFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import static lib.ui.NavigationUI.NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            ARTICLE_IN_LIST,
            FOLDER_LIST_TPL,
            ARTICLE_TITTLE_TPL;


    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void waitForArticleToAppearByTittle(String article_tittle){
        String articleXpath= getTittle(article_tittle);
        this.waitForElementPresent(articleXpath,"Cannot find article: " + article_tittle,10);
    }

    public void waitForArticleToDisappearByTittle(String article_tittle){
        String articleXpath= getTittle(article_tittle);
        this.waitForElementPresent(articleXpath,"Found article: " + article_tittle,10);
    }

    private static String getFolderList(String substring) {
        return FOLDER_LIST_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTittle(String substring) {
        return ARTICLE_TITTLE_TPL.replace("{SUBSTRING}", substring);
    }

    public void deleteFirstArticle(String article_list_name) {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
        this.swipeUpQuick();
        String folder_name = getFolderList(article_list_name);
        this.waitForElementAndClick(folder_name,
                "Cant find " + article_list_name + " list");
        this.swipeElementToLeft(ARTICLE_IN_LIST,
                "Not found saved articles");}
        else {
            this.waitForElementAndClick(ARTICLE_IN_LIST,"Cant find first article");
        }
    }

    public void checkArticleTitle(String article_list_name) throws InterruptedException {
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.waitForElementWithTextAndClick(ARTICLE_IN_LIST,
                    article_list_name,
                    "Cant find article with name: " + article_list_name);
            String provided_tittle = getTittle(article_list_name);
            this.waitForElementPresent(provided_tittle,
                    "Title does not match");
        }else {
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.goToSavedList();

            this.waitForElementAndClick(ARTICLE_IN_LIST,
                    "Cant find article list");
            this.waitForElementPresent(ARTICLE_TITTLE_TPL,
                    "Title does not match");
        }
    }
}
