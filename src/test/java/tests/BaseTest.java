package tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import methods.BaseMethods;

public class BaseTest {

	@BeforeClass
	public void beforeClass() throws IOException {
		BaseMethods.initialize();
	}

	@BeforeTest
	public void beforeTest() {
		BaseMethods.launchBrowser();
	}

	@AfterTest
	public void afterTest() {
		BaseMethods.closeBrowser();
	}

}
