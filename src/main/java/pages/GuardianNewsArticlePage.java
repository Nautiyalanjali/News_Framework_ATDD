package pages;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Locators;
import base.TestBase;

public class GuardianNewsArticlePage extends TestBase {

	String articleTitle;

	public void extractFirstNewsArticle() {

		String AcceptButtonGuardian = Locators.AcceptButtonGuardian;
		String NewsMenu = Locators.NewsMenu;
		String FirstArticle = Locators.FirstArticle;

		driver.get(prop.getProperty(PROPERTY_WEB_URL));

		// new WebDriverWait(driver,
		// 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("__tcfapiLocator")));

		driver.switchTo().frame("sp_message_iframe_780010");

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("scrollBy(0, 3000)");

		driver.findElement(By.xpath(AcceptButtonGuardian)).click();

		driver.switchTo().defaultContent();

		driver.findElement(By.xpath(NewsMenu)).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0, 849)");

		WebElement firstArticle = driver.findElement(By.xpath(FirstArticle));

		articleTitle = firstArticle.getText();

		System.out.println(articleTitle);

	}

	public void searchGoogleArticle() {

		String GoogleAcceptCookies = Locators.GoogleAcceptCookies;

		driver.get(prop.getProperty(Google_URL));

		driver.findElement(By.xpath(GoogleAcceptCookies)).click();

		driver.findElement(By.name("q")).sendKeys(articleTitle + Keys.ENTER);

	}

	public static void removeStopWords(String str) {

		String[] words = str.split(" ");
		ArrayList<String> wordsList = new ArrayList<String>();
		Set<String> stopWordsSet = new HashSet<String>();
		stopWordsSet.add("I");
		stopWordsSet.add("THIS");
		stopWordsSet.add("AND");
		stopWordsSet.add("THERE'S");
		stopWordsSet.add("AM");
		stopWordsSet.add("FOR");
		stopWordsSet.add("ON");
		stopWordsSet.add("OVER");
		stopWordsSet.add("'");
		stopWordsSet.add(",");
		stopWordsSet.add("’");
		stopWordsSet.add("‘");

		for (String word : words) {
			String wordCompare = word.toUpperCase();
			if (!stopWordsSet.contains(wordCompare)) {
				wordsList.add(word);
			}
		}

		for (String str1 : wordsList) {
			// System.out.print(str1 + " ");
		}

	}

	public void GetResultsFromGoogle() {

		removeStopWords(articleTitle);
		String articleStringWords[] = articleTitle.split(" ");

		List<WebElement> LIST = driver.findElements(By.tagName("h3"));
		System.out.println("Total number of News Aricles in Google - " + LIST.size());

		int count = 0;
		int validCount = 0;
		boolean flag = false;
		for (int i = 1; i < LIST.size(); i++) {
			// System.out.println(LIST.get(i).getText());
			String temp[] = LIST.get(i).getText().split(" ");
			for (int j = 0; j < articleStringWords.length; j++) {
				for (int k = 0; k < temp.length; k++) {
					if (articleStringWords[j].equals(temp[k])) {
						count++;

					}

				}

			}
			if (count >= articleStringWords.length - 2 && LIST.size() > 2) {
				System.out.println("Valid News - " + LIST.get(i).getText());
				count = 0;
				validCount++;
			}

		}
		if (validCount >= 2)
			flag = true;

		Assert.assertEquals(flag, true, "News Article is Valid");
		LOGGER.info("News Article Verification matched");

	}

	public void getNoResultsFromGoogle() {

		removeStopWords(articleTitle);
		String articleStringWords[] = articleTitle.split(" ");

		List<WebElement> LIST = driver.findElements(By.tagName("h3"));
		System.out.println("Total number of News Aricles in Google - " + LIST.size());

		int count = 0;
		int validCount = 0;
		boolean flag = false;
		for (int i = 1; i < LIST.size(); i++) {
			// System.out.println(LIST.get(i).getText());
			String temp[] = LIST.get(i).getText().split(" ");
			for (int j = 0; j < articleStringWords.length; j++) {
				for (int k = 0; k < temp.length; k++) {
					if (articleStringWords[j].equals(temp[k])) {
						count++;

					}

				}

			}
			if (count >= articleStringWords.length - 2 && LIST.size() > 2) {
				System.out.println("Valid News - " + LIST.get(i).getText());
				count = 0;
				validCount++;
			}

		}
		if (validCount <= 2)
			flag = true;

		Assert.assertEquals(flag, true, "News Article is Invalid");
		LOGGER.info("News Article Invalid verification done ");
	}

	public void navigateToThePrint() {

		String ThePrintSearch = Locators.ThePrintSearch;
		String ThePrintSearchBox = Locators.ThePrintSearchBox;
		String ThePrintSearchBoxClick = Locators.ThePrintSearchBoxClick;
		String ThePrintSearchPageTitle = Locators.ThePrintSearchPageTitle;
		String ThePrintSearchPageSearchbox = Locators.ThePrintSearchPageSearchbox;
		String ThePrintSearchPageSearchboxClick = Locators.ThePrintSearchPageSearchbox;

		driver.get(prop.getProperty(Print_URL));

		// new WebDriverWait(driver,
		// 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("__tcfapiLocator")));

		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(ThePrintSearch)));
		driver.findElement(By.xpath(ThePrintSearch)).click();

		driver.findElement(By.xpath(ThePrintSearchBoxClick)).click();
		new WebDriverWait(driver, 20)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ThePrintSearchPageTitle)));

		driver.findElement(By.xpath(ThePrintSearchPageSearchbox)).sendKeys(articleTitle);

		driver.findElement(By.xpath(ThePrintSearchPageSearchbox)).click();

	}

	public void GetResultsFromPrint() {

		String ThePrintSearchResultsTitle = Locators.ThePrintSearchResultsTitle;

		removeStopWords(articleTitle);
		String articleStringWords[] = articleTitle.split(" ");

		List<WebElement> LIST = driver.findElements(By.xpath(ThePrintSearchResultsTitle));
		System.out.println("Total number of News Aricles in The Print - " + LIST.size());

		int count = 0;
		int validCount = 0;
		boolean flag = false;
		for (int i = 1; i < LIST.size(); i++) {
			// System.out.println(LIST.get(i).getText());
			String temp[] = LIST.get(i).getText().split(" ");
			for (int j = 0; j < articleStringWords.length; j++) {
				for (int k = 0; k < temp.length; k++) {
					if (articleStringWords[j].equals(temp[k])) {
						count++;

					}

				}

			}
			if (count >= articleStringWords.length - 4 && LIST.size() > 2) {
				System.out.println("Valid News - " + LIST.get(i).getText());
				count = 0;
				validCount++;
			}

		}
		if (validCount >= 2)
			flag = true;

		Assert.assertEquals(flag, true, "News Article is Valid");
		LOGGER.info("News Article Verification matched with The Print");

	}

	public void GetNoResultsFromPrint() {

		String ThePrintNoResultFound = Locators.ThePrintNoResultFound;
		WebElement element = driver.findElement(By.xpath(ThePrintNoResultFound));

		String text = element.getText();
		Assert.assertEquals(text, prop.getProperty("No_Result_Found"));

	}
}