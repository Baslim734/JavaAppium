package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.skipWelcomeScreenIOSApp();
        this.openWikiPageForMobileWeb();
    }

    protected void rotateScreenPortait(){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortait does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }
    protected void tearDrown() throws Exception {
        rotateScreenPortait();
        driver.quit();
        super.tearDown();
    }

    protected void openWikiPageForMobileWeb(){
        if(Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiPageForMobileWeb does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomeScreenIOSApp(){
        if (Platform.getInstance().isIOS()){
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
