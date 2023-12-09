package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    private SearchPageObject SearchPageObject;
    private ArticlePageObject ArticlePageObject;
    private NavigationUI NavigationUI;
    private MyListsPageObject MylistsPageObject;

    public void setUp() throws Exception {
        super.setUp();
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Saving two articles")
    @Description("Saving two articles, delete one and check expected to stay")
    @Step("Starting test testSavingTwoArticles")
    public void testSavingTwoArticles() throws InterruptedException {
        SearchPageObject  = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI = NavigationUIFactory.get(driver);
        MylistsPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();

        ArticlePageObject.takeScreenshot("main_page");

        String article_tittle = "Cinnamon";
        String article_list_name = article_tittle + " list";
        String second_article_tittle = "Cinnamon (desktop environment)";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(article_tittle);
        SearchPageObject.clickBySearchResult(1);
        ArticlePageObject.createNewListAndAddArticle(article_list_name);
        ArticlePageObject.closeArticle();
        SearchPageObject.clickBySearchResult(2);
        ArticlePageObject.addArticleToExistingList(article_list_name);
        ArticlePageObject.closeArticle();
        NavigationUI.backToMainPage();
        NavigationUI.goToSavedList();
        MylistsPageObject.deleteFirstArticle(article_list_name);
        MylistsPageObject.checkArticleTitle(second_article_tittle);

    }

    @Test
    @DisplayName("Check tittle with expected one")
    @Description("In search results we check tittle with search query")
    public void testCheckTittle() throws InterruptedException {
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_tittle = "Cinnamon";
        String second_article_tittle = "Cinnamon (desktop environment)";

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(article_tittle);
        SearchPageObject.clickBySearchResult(0);
        ArticlePageObject.checkTittle(second_article_tittle);

    }

}
