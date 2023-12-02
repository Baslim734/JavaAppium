package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;


public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome() throws InterruptedException {
        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);
        Thread.sleep(5000);
        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExplore();
        WelcomePage.clickNextButton();

        WelcomePage.waitForSearchOnManyLanguages();
        WelcomePage.clickNextButton();

        WelcomePage.waitForHelpToImproveApp();
        WelcomePage.clickStartButton();
    }
}
