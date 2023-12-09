package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;


abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            START_APP_SKIP_BUTTON,
            SEARCH_INPUT,
            SEARCH_INPUT_TEXT_TPL,
            SEARCH_BY_TEXT_TPL,
            CLOSE_SEARCH_BUTTON,
            PAGE_LIST_ID,
            TITLE_AND_DESCRIPTION_SEARCH_RESULT_TPL,
            WEB_MENU_BUTTON,
            WEB_LOGIN_BUTTON,
            WEB_LOGIN_INPUT,
            WEB_PASSWORD_INPUT,
            LOGIN,
            PASSWORD,
            WEB_AUTHORIZE;


    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_BY_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getInputSearchText(String substring) {
        return SEARCH_INPUT_TEXT_TPL.replace("{SUBSTRING}", substring);
    }


    private static String getLocatorByTittleAndDescription(String tittle, String description) {
        String newXpath = TITLE_AND_DESCRIPTION_SEARCH_RESULT_TPL.replace("{SUBSTRING_TITTLE}", tittle);
        return newXpath.replace("{SUBSTRING_DESC}", description);
    }

    @Step("Initiating search input")
    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Input element not on the screen");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find 'Search Wikipedia' input");
    }

    @Step("Undo search input")
    public void closeSearch() {
        this.waitForElementPresent(CLOSE_SEARCH_BUTTON, "Close button not on the screen");
        this.waitForElementAndClick(CLOSE_SEARCH_BUTTON, "Cannot find close button");
    }

    @Step("Skipping starting screen")
    public void startAppSkipButton() throws InterruptedException {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementPresent(START_APP_SKIP_BUTTON, "Skip button not on the screen");
            this.waitForElementAndClick(START_APP_SKIP_BUTTON, "Cannot find skip button");
        } else if (Platform.getInstance().isMW()) {
            System.out.println("Using authorization for mobile_web instead skip start screen");
            this.waitForElementAndClick(WEB_MENU_BUTTON, "Cannot find menu button");
            Thread.sleep(4000);
            this.waitForElementAndClick(WEB_LOGIN_BUTTON, "Cannot find login button");
            this.waitForElementAndSendKeys(WEB_LOGIN_INPUT, LOGIN, "Cannot find login input");
            this.waitForElementAndSendKeys(WEB_PASSWORD_INPUT, PASSWORD, "Cannot find password input");
            this.waitForElementAndClick(WEB_AUTHORIZE, "Cannot find authorize button");

        }
    }

    @Step("Typing in search line: '{search_line}'")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input");
    }

    @Step("Wait for search result")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find result with substring: " + substring);
    }

    private static String getNextResultById(String ID) {
        return PAGE_LIST_ID.replace("{SUBSTRING}", ID);
    }

    @Step("Opening found article")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot click result with substring: " + substring);
    }

    @Step("Opening result witch an ordinal number: '{sequenceNumber}'")
    public void clickBySearchResult(Integer sequenceNumber) {
        if (Platform.getInstance().isIOS()) {
            String nextResult = getNextResultById(sequenceNumber + "");
            this.waitForElementAndClick(nextResult, "Cant get element with index " + sequenceNumber);
        } else {
            screenshot(this.takeScreenshot("article_tittle"));
            this.waitForElementsAndClickWithText(PAGE_LIST_ID, sequenceNumber, "There is no result with index: " + sequenceNumber);
        }
    }

    @Step("Waiting for disappear search result")
    public void waitForSearchResultDisappear(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(search_result_xpath, "Found result on the screen with substring: " + substring, 10);
    }

    @Step("Comparing search result")
    public void compareSearchInputedText(String substring) {
        String search_input_xpath = getInputSearchText(substring);
        this.assertElementHasText(search_input_xpath, substring, "Other text in searchline inputed, must be:" + substring);
    }

    @Step("Try to find result with provided tittle and description")
    public void waitForElementByTittleAndDescription(String tittle, String description) {
        String newXpath = getLocatorByTittleAndDescription(tittle, description);
        this.waitForElementPresent(newXpath, "Not found result with title: " + tittle + " and description: " + description, 10);
    }

    @Step("Try to find three search results by tittle and description")
    public void findThreeSearchResultByTittleAndDescription(String tittle, String description) {
        waitForElementByTittleAndDescription(tittle, description);
        String newXpath = getLocatorByTittleAndDescription(tittle, description);
        List<WebElement> elements = driver.findElements(By.xpath(newXpath));
        Assert.assertTrue("Found three or more search result:", elements.size() >= 3);
        Assert.assertFalse("Found least then three search result: " + elements.size(), elements.size() < 3);
    }

}

