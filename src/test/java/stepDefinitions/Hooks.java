package stepDefinitions;

import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends TestBase {
	
	@Before()
	public void beforeScenario() {
		openBrowser();
		LOGGER.info("Browser is opened ..");
		
	}
	
	@After()
	public void afterScenario() {
		closeBrowser();
		LOGGER.info("Close browser is done ..");
	}


}
