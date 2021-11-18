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
	public void registerMembership() {
		driver.findElement(By.partialLinkText("Register")).click();
		String act = driver.getCurrentUrl();
		String exp = "file:///E:/Selenium/Offline%20Website/Offline%20Website/pages/examples/register.html";
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 2)
	public void verifyLoginBoxText() {
		String text = driver.findElement(By.tagName("p")).getText();
		Assert.assertEquals(text, "Register a new membership");
	}

	@Test(priority = 3)
	public void verifyNamePlaceholderText() {
		String text = driver.findElement(By.id("name")).getAttribute("placeholder");
		Assert.assertEquals(text, "Name");
	}

	@Test(priority = 4)
	public void verifyMobilePlaceholderText() {
		String text = driver.findElement(By.id("mobile")).getAttribute("placeholder");
		Assert.assertEquals(text, "Mobile");
	}

	@Test(priority = 5)
	public void verifyEmailPlaceholderText() {
		String text = driver.findElement(By.id("email")).getAttribute("placeholder");
		Assert.assertEquals(text, "Email");
	}

	@Test(priority = 6)
	public void verifyPasswordPlaceholderText() {
		String text = driver.findElement(By.id("password")).getAttribute("placeholder");
		Assert.assertEquals(text, "Password");
	}

	@Test(priority = 7)
	public void alreadyMembership() {
		driver.findElement(By.partialLinkText("membership")).click();
		String act = driver.getTitle();
		String exp = "JavaByKiran | Log in";
		Assert.assertEquals(act, exp);
	}

}
