package com.crm.qa.TestCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.BaseClass.TestBase;
import com.crm.qa.Constants.Constants;
import com.crm.qa.Pages.ContactsPage;
import com.crm.qa.Pages.DealsPage;
import com.crm.qa.Pages.HomePage;
import com.crm.qa.Pages.LoginPage;
import com.crm.qa.Utilities.TestUtility;

public class HomePageTest extends TestBase
{
	LoginPage loginPage;
	HomePage homePage;
	TestUtility testUtil;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	
	public HomePageTest()
	{
		super();
	}
	
	@Parameters("Browser")
	@BeforeMethod(alwaysRun=true)
	public void setUp(String Browser)
	{
		initialization(Browser);
		Log.info("Application Launched Successfully");
		
		testUtil = new TestUtility();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		dealsPage = new DealsPage();
		homePage = loginPage.login(property.getProperty("Username"),property.getProperty("Password"));
	}

	@Test(priority=1, enabled=true)
	public void verifyHomePageTitleTest(Method method)
	{
		extentTest = extent.startTest(method.getName());
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, Constants.HOME_PAGE_TITLE, "Home Page Title is not Matched");
		Log.info("Home Page Title Verified");
	}
	
	@Test(priority=2, enabled=true)
	public void verifyUserNameTest(Method method)
	{
		extentTest = extent.startTest(method.getName());
		testUtil.switchToFrame("mainpanel");
		Assert.assertTrue(homePage.verifyCorrectUserName());
		Log.info("UserName Verified");
	}
	
	@Test(priority=3, enabled=true)
	public void verifyContactsLinkTest(Method method)
	{
		extentTest = extent.startTest(method.getName());
		testUtil.switchToFrame("mainpanel");
		contactsPage = homePage.clickOnContactsLink();
		Log.info("Switched into Frame and Clicked on Contacts Link");
	}
	
	@Test(priority=4, enabled=true)
	public void verifyDealsPageLinkTest(Method method)
	{
		extentTest = extent.startTest(method.getName());
		testUtil.switchToFrame("mainpanel");
		dealsPage = homePage.clickOnDealsLink();
		Log.info("Switched into Frame and Clicked on Deals Link");
	}
}
