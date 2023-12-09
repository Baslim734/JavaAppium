package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    private SearchPageObject SearchPageObject;
    private lib.ui.ArticlePageObject ArticlePageObject;

    public void setUp() throws Exception {
        super.setUp();
        SearchPageObject = SearchPageObjectFactory.get(driver);
    }

    @Test
    @DisplayName("Test search")
    @Description("Search some info and check expected result")
    public void testSearch() throws InterruptedException {
        SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @DisplayName("Search and cancel test")
    @Description("Search some info, close result and expect result gone")
    public void testSearchAndCancel() throws InterruptedException {
        SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Oil");
        SearchPageObject.waitForSearchResult("Viscous water-insoluble liquid");
        SearchPageObject.waitForSearchResult("Programme headed by the United Nations");
        SearchPageObject.waitForSearchResult("Oil platform");
        SearchPageObject.closeSearch();
        SearchPageObject.waitForSearchResultDisappear("Viscous water-insoluble liquid");
        SearchPageObject.waitForSearchResultDisappear("Programme headed by the United Nations");
        SearchPageObject.waitForSearchResultDisappear("Oil platform");
    }

    @Test
    @DisplayName("Test check help text in search field")
    @Description("Check help text in search field")
    public void testCompareInstalledTextInField() throws InterruptedException {
        SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.compareSearchInputedText("Search Wikipedia");

    }

    @Test
    @DisplayName("Compare words in search result")
    @Description("Check results tittle in each search result")
    public void testCompareWordsInSearchResults() throws InterruptedException {
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Oil");
        ArticlePageObject.expectTheTextInEachResult("Oil");
    }

    @Test
    @DisplayName("Check result by tittle and description")
    @Description("Check results tittle and desc in search result")
    public void testCheckResultByTittleAndDescription() throws InterruptedException {

        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Electricity");

        SearchPageObject.findThreeSearchResultByTittleAndDescription("Electricity","electric");

    }
}
