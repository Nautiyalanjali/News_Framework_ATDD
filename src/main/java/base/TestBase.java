package base;

import io.github.bonigarcia.wdm.WebDriverManager;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;




public class TestBase {

	public static final String PROPERTY_WEBDRIVER_BROWSER = "browser.type";
	public static final String PROPERTY_WEB_URL = "web.url";
	public static final String Google_URL = "google.url";
	public static final String Print_URL ="print.url";

	public static WebDriver driver;
	public static Properties prop;
	public static String loginUser;
	public static String filePath = "/ATDD_Project/src/test/resources/config/config.properties";
	protected static final Logger LOGGER = Logger.getLogger(TestBase.class);
	public static WebDriverWait wait;
	
	

	static {
		prop = readProperties("./src/test/resources/config/config.properties");

	}


	public static Properties readProperties(String filePath) {

		try {
			FileInputStream testProperties = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(testProperties);
			LOGGER.info("Properties file reading done: " + filePath);
			return prop;
		} catch (FileNotFoundException e) {
			LOGGER.error("Properties file error: " + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("Properties file error: " + e.getMessage());
		}
		return prop;
	}

	public void openBrowser() {

		//Properties prop = readProperties("C:\\config.properties");
		final String browserType = prop.getProperty(PROPERTY_WEBDRIVER_BROWSER);
		final String url = prop.getProperty(PROPERTY_WEB_URL);

		if (browserType.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			final ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("safebrowsing.enabled", "true");
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--privileged", "--verbose", "--disable-dev-shm-usage", "--no-sandbox",
					"--whitelisted-ips", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();

		} else {
			throw new IllegalArgumentException("Browser " + browserType + " not supported.");
		}

	}
	
	public static void setExplicitWait(int seconds){
		   wait = new WebDriverWait(driver, seconds);
		}


	public void closeBrowser() {

		try {
			LOGGER.info("Close Browser");
			driver.close();
			driver.quit();
		} catch (Exception ex) {
			LOGGER.error("Close browser " + ex.getMessage(), ex);
		}

	}
}
