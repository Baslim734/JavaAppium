package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for welcome screens")
public class GetStartedTest extends CoreTestCase {

    private SearchPageObject SearchPageObject;

    public void setUp() throws Exception {
        super.setUp();
        SearchPageObject = SearchPageObjectFactory.get(driver);
    }

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Pass through welcome test")
    @Description("Pass through all welcome screens to enter the main screen")
    public void testPassThroughWelcome() throws InterruptedException {
        if (Platform.getInstance().isAndroid()) {
            return;
        } else if (Platform.getInstance().isIOS()) {
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
        } else if (Platform.getInstance().isMW()) {
            SearchPageObject.startAppSkipButton();
        }
    }
}
