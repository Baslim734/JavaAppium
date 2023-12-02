package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static lib.ui.SearchPageObject.SEARCH_INPUT;


abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            RESULT_ARTICLE_TITTLE_ID,
            BUTTON_SAVE_ID,
            BUTTON_ADD_TO_LIST,
            INPUT_LIST_NAME_STRING,
            BUTTON_CREATE_NEW_LIST,
            BUTTON_OK,
            SEARCH_BY_TEXT_TPL,
            BUTTON_BACK,
            BUTTON_SEARCH,
            ARTICLE_TITTLE_ID_TPL;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void expectTheTextInEachResult(String textInElement) {
        List<WebElement> elements = driver.findElements(By.id(RESULT_ARTICLE_TITTLE_ID));
        for (WebElement element : elements) {
            String text = element.getText();
            assertTrue("Expected text in tittle:" + textInElement + "\nBut found: " + text, text.contains(textInElement));
        }
    }

    public void createNewListAndAddArticle(String article_list_name) throws InterruptedException {
        System.out.println("Current platform is:  " + Platform.getInstance().getPlatformVar());
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(BUTTON_SAVE_ID, "Cant find save button");
            this.waitForElementAndClick(BUTTON_ADD_TO_LIST,
                    "Cant find add to list button");
            if (Platform.getInstance().isIOS()) {
                this.waitForElementAndClick(BUTTON_CREATE_NEW_LIST,
                        "Cant find create new list button");
                ;
            }
            this.waitForElementAndSendKeys(
                    INPUT_LIST_NAME_STRING,
                    article_list_name,
                    "Cannot find search input");
            this.waitForElementAndClick(BUTTON_OK
                    , "Cant find OK button");
        } else {
            Thread.sleep(2000);
            this.waitForElementAndClick(BUTTON_SAVE_ID, "Cant find save button");
        }
    }

    public void addArticleToExistingList(String article_list_name) {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(BUTTON_SAVE_ID,
                    "Cant find save button");
            this.waitForElementAndClick(BUTTON_ADD_TO_LIST,
                    "Cant find add to list button");
            String provided_list = getElementByText(article_list_name);
            this.waitForElementAndClick(provided_list,
                    "Cant find list: " + article_list_name);
        } else {
            this.waitForElementAndClick(BUTTON_SAVE_ID,
                    "Cant find save button");
        }
    }

    private static String getElementByText(String substring) {
        return SEARCH_BY_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTittle(String substring) {
        return ARTICLE_TITTLE_ID_TPL.replace("{SUBSTRING}", substring);
    }

    public void closeArticle() {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(BUTTON_BACK,
                    "Cant find back button");
        } else {
            this.waitForElementAndClick(BUTTON_SEARCH,
                    "Cant find search button");
            this.waitForElementAndSendKeys(SEARCH_INPUT, "Cinnamon", "Cannot find and type into search input");
        }
    }

    public void checkTittle(String article_tittle) {
        String provided_tittle = getTittle(article_tittle);
        this.waitForElementPresent(provided_tittle, "ninashel", 5);
        this.assertElementPresent(provided_tittle,
                "Not found element tittle, by PATH: " + provided_tittle);
    }

}
