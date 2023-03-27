package cucumberOptions;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/featureFiles/GuardianNewsArticle.feature", 
glue = "stepDefinitions", 
    plugin ={"pretty",
    		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
    		"json:target/cucumber-report/cucumber.json",
            "html:target/cucumber-report/cucumber.html",
            "junit:tartget/cucumber-reports/cucumber.xml",
            "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}

//plugin = {"json:target/cucumber.json"}

		)
		



public class TestNgTestRunner extends AbstractTestNGCucumberTests {
	
	

}
