package methods;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BaseMethods {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties locators;
	public static SimpleDateFormat formatter;

	public static void initialize() throws IOException {
		try {
			locators = new Properties();
			FileInputStream selectors = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\locators\\locators.properties");
			locators.load(selectors);
		} catch (Exception e) {
			Reporter.log("[File is in use by another program.]", true);
		}
	}

	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		Reporter.log("[Browser launched successfully.]", true);
	}
	
	public static void closeBrowser() {
		driver.close();
		driver.quit();
		Reporter.log("[Browser closed successfully.]", true);
	}
}
