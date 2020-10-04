package methods;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class BusinessMethods extends BaseMethods {
	public WebDriver driver = BaseMethods.driver;
	public Properties props = BaseMethods.locators;
	public WebDriverWait wait = BaseMethods.wait;
	public Select select;

	public BusinessMethods openUrl(String url) {
		driver.get(props.getProperty(url));
		Reporter.log("[URL " + url + " open successfully.]", true);
		return this;
	}

	public BusinessMethods verifyPageTitle(String pageTitle) {
		String actualTitle = driver.getTitle();
		String expectedTitle = props.getProperty(pageTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
		Reporter.log("[Expected title: " + expectedTitle + ", Actual title: " + actualTitle + "]", true);
		return this;
	}

	public BusinessMethods clickElementByXpath(String locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty(locator)))).click();
		Reporter.log("[" + locator + " clicked successfully.]", true);
		return this;
	}

	public BusinessMethods selectDropdownValue(String locator, String dropdownvalue) {
		new Select(driver.findElement(By.xpath(props.getProperty(locator)))).selectByVisibleText(dropdownvalue);
		Reporter.log("[" + dropdownvalue + " is selected from " + locator + "dropdown.]", true);
		return this;
	}

	public BusinessMethods chooseFlightUsingFlightNumber(int flightNumber) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("Flight List Table"))));
		List<WebElement> flightNumberList = driver.findElements(By.xpath(props.getProperty("Flight Number Locator")));
		List<WebElement> chooseFlightList = driver.findElements(By.xpath(props.getProperty("Choose This Flight")));
		WebElement flightNumberElement = flightNumberList.stream().filter(e -> e.getText().equals(Integer.toString(flightNumber)))
				.findFirst().orElseThrow(() -> new AssertionError("Flight number not found."));
		chooseFlightList.get(flightNumberList.indexOf(flightNumberElement)).click();
		Reporter.log("[Flight number " + flightNumber + " selected for booking.]", true);	
		return this;
	}

	public BusinessMethods enterDataInTextboxUsingXpath(String textFieldLocator, String data) {
		WebElement textField = driver.findElement(By.xpath(props.getProperty(textFieldLocator)));
		wait.until(ExpectedConditions.visibilityOf(textField)).clear();
		textField.sendKeys(data);
		Reporter.log("[" + "{" + data + "}" + " is enterted in " + "{" + textFieldLocator + "}. ]", true);
		return this;
	}

	public BusinessMethods verifyWebElementIsDisplayedUsingXpath(String locator) {
		Assert.assertEquals(driver.findElement(By.xpath(props.getProperty(locator))).isDisplayed(), true);
		Reporter.log("[" + locator + " is displayed on the page.]", true);
		return this;
	}

	public BusinessMethods verifyWebElementTextIsNotNullUsingXpath(String locator) {
		Assert.assertNotEquals(driver.findElement(By.xpath(props.getProperty(locator))).getText(), "");
		Reporter.log("[" + locator + " is not empty.]", true);
		return this;
	}

}
