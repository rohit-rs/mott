package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import methods.BusinessMethods;

public class AutomationTest1 extends BaseTest {
	private BusinessMethods method;
	
	@BeforeClass
	public void initializeBusinessMethod() {
		 method = new BusinessMethods();
	}

	@Test
	public void TC_01_Verify_that_user_is_able_to_search_flights() {
		method.openUrl("url").verifyPageTitle("HomePageTitle")
				.selectDropdownValue("Choose your departure city", "Boston")
				.selectDropdownValue("Choose your destination city", "Berlin").clickElementByXpath("Find Flights")
				.verifyPageTitle("ReservePageTitle")
				.verifyWebElementIsDisplayedUsingXpath("Flights from Boston to Berlin")
				.verifyWebElementIsDisplayedUsingXpath("Flight List Table");
	}

	@Test
	public void TC_02_Verify_that_user_is_able_to_select_a_flight_for_booking() {
		BusinessMethods method = new BusinessMethods();
		method.openUrl("url").verifyPageTitle("HomePageTitle")
				.selectDropdownValue("Choose your departure city", "Portland")
				.selectDropdownValue("Choose your destination city", "London").clickElementByXpath("Find Flights")
				.verifyPageTitle("ReservePageTitle").verifyWebElementIsDisplayedUsingXpath("Flight List Table")
				.chooseFlightUsingFlightNumber(9696).verifyPageTitle("PurchasePageTitle")
				.verifyWebElementIsDisplayedUsingXpath("Your flight from TLV to SFO has been reserved")
				.verifyWebElementIsDisplayedUsingXpath("Flight Number");

	}

	@Test
	public void TC_03_Verify_that_user_is_able_to_book_a_flight() {
		BusinessMethods method = new BusinessMethods();
		method.openUrl("url").verifyPageTitle("HomePageTitle")
				.selectDropdownValue("Choose your departure city", "Paris")
				.selectDropdownValue("Choose your destination city", "Rome").clickElementByXpath("Find Flights")
				.verifyPageTitle("ReservePageTitle").chooseFlightUsingFlightNumber(12)
				.verifyPageTitle("PurchasePageTitle").enterDataInTextboxUsingXpath("First Name", "Test")
				.enterDataInTextboxUsingXpath("Address", "Test Address").enterDataInTextboxUsingXpath("City", "Test City")
				.enterDataInTextboxUsingXpath("State", "Test State").enterDataInTextboxUsingXpath("ZIP", "76809")
				.selectDropdownValue("Card Type", "American Express")
				.enterDataInTextboxUsingXpath("Credit Card Number", "8736090910109018").enterDataInTextboxUsingXpath("Month", "06")
				.enterDataInTextboxUsingXpath("Year", "2025").enterDataInTextboxUsingXpath("Name On Card", "Rohit Srivastav")
				.clickElementByXpath("Purchase Flight").verifyPageTitle("ConfirmationPageTitle")
				.verifyWebElementIsDisplayedUsingXpath("Purchase Confirmation Message")
				.verifyWebElementTextIsNotNullUsingXpath("Booking Id")
				.verifyWebElementIsDisplayedUsingXpath("Status PendingCapture");
	}
}
