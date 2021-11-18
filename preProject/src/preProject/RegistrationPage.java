package preProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RegistrationPage {
	WebDriver driver;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///E:/Selenium/Offline%20Website/Offline%20Website/index.html");
	}

	@AfterSuite
	public void tearDown() {
		driver.close();
	}
	
	@Test(priority = 1)
	public void verifyBrowserTitle() {
		driver.findElement(By.partialLinkText("Register")).click();
		String act=driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "JavaByKiran | Registration Page";
		System.out.println("Expected Browser Title: "+exp);
		Assert.assertEquals(act, exp);
		
	}

	@Test(priority = 2)
	public void VerifyBrowserUrl() {
		String act = driver.getCurrentUrl();
		System.out.println("Actual Browser Url: "+act);
		String exp = "file:///E:/Selenium/Offline%20Website/Offline%20Website/pages/examples/register.html";
		System.out.println("Expected Browser Url: "+exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 3)
	public void verifyLoginBoxText() {
		String act = driver.findElement(By.tagName("p")).getText();
		System.out.println("act:"+act);
		String exp="Register a new membership";
		System.out.println("exp:"+exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 4)
	public void verifyNamePlaceholderText() {
		String act = driver.findElement(By.id("name")).getAttribute("placeholder");
		System.out.println("Actual Placeholder :" + act);
		String exp="Name";
		System.out.println("Expected Placeholder :" + exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 5)
	public void verifyMobilePlaceholderText() {
		String act = driver.findElement(By.id("mobile")).getAttribute("placeholder");
		System.out.println("Actual Placeholder :" + act);
		String exp="Mobile";
		System.out.println("Expected Placeholder :" + exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 6)
	public void verifyEmailPlaceholderText() {
		String act = driver.findElement(By.id("email")).getAttribute("placeholder");
		System.out.println("Actual Placeholder :" + act);
		String exp="Email";
		System.out.println("Expected Placeholder :" + exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 7)
	public void verifyPasswordPlaceholderText() {
		String act = driver.findElement(By.id("password")).getAttribute("placeholder");
		System.out.println("Actual Placeholder :" + act);
		String exp="Password";
		System.out.println("Expected Placeholder :" + exp);
		Assert.assertEquals(act, exp);
		
	}

	@Test(priority = 8)
	public void alreadyMembership() {
		driver.findElement(By.partialLinkText("membership")).click();
		String act = driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "JavaByKiran | Log in";
		System.out.println("Expected Browser Title: "+exp);
		Assert.assertEquals(act, exp);
	}

}
