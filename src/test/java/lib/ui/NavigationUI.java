package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            BUTTON_BACK,
            NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE,
            NAVIGATION_BAR_SAVED_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void backToMainPage() throws InterruptedException {
        if(Platform.getInstance().isMW()){
            Thread.sleep(4000);
            this.waitForElementAndClick(BUTTON_BACK,
                    "Cant find back button");
            this.checkForElementIsEnabled(NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE);
        }else{
        this.waitForElementAndClick(BUTTON_BACK,
                "Cant find back button");
        this.waitForElementAndClick(NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE,"cant find menu button");}
    }

    public void goToSavedList() throws InterruptedException {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
        this.waitForElementAndClick(NAVIGATION_BAR_SAVED_BUTTON,
                "Cant find back button");
        this.checkForElementIsEnabled(NAVIGATION_BAR_SAVED_BUTTON);}
        else {
            this.waitForElementAndClick(NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE,
                    "Cant find menu button");
            Thread.sleep(2000);
            this.waitForElementAndClick(NAVIGATION_BAR_SAVED_BUTTON,
                    "Cant find saved button");
        }
    }

}
