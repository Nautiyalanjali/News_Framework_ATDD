package stepDefinitions;

import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GuardianNewsArticlePage;

public class GuardianNewsArticleStepDefinition extends TestBase {

	GuardianNewsArticlePage gnap = new GuardianNewsArticlePage();

	@Given("a news article from https:\\/\\/www.theguardian.com\\/tone\\/news")
	public void a_news_article_from_https_www_theguardian_com_tone_news() {
		gnap.extractFirstNewsArticle();
	}

	@When("I search for the article on Google")
	public void i_search_for_the_article_on_google() {
		gnap.searchGoogleArticle();
	}

	@Then("I should see at least two other news articles with similar information And the Guardian news article should be considered valid")
	public void i_should_see_at_least_two_other_news_articles_with_similar_information_and_the_guardian_news_article_should_be_considered_valid() {
		gnap.GetResultsFromGoogle();

	}

	@Then("I should not see any other news articles with similar information And the Guardian news article should be considered invalid")
	public void i_should_not_see_any_other_news_articles_with_similar_information_and_the_guardian_news_article_should_be_considered_invalid() {
		gnap.getNoResultsFromGoogle();
	}

	@When("I search for the article on https:\\/\\/theprint.in\\/")
	public void i_search_for_the_article_on_https_theprint_in() {
		gnap.navigateToThePrint();
	}

	@Then("I see at least two other news articles with similar information in The Print And the Guardian news article should be considered valid")
	public void i_see_at_least_two_other_news_articles_with_similar_information_in_the_print_and_the_guardian_news_article_should_be_considered_valid() {
		gnap.GetResultsFromPrint();
	}

	@Then("I should not seeany other news articles with similar information in the print And the Guardian news article should be considered invalid")
	public void i_should_not_seeany_other_news_articles_with_similar_information_in_the_print_and_the_guardian_news_article_should_be_considered_invalid() {
		gnap.GetNoResultsFromPrint();
	}

}
