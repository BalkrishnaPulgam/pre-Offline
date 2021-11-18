package preProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginPage {

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
		String act=driver.getTitle();
		String exp = "JavaByKiran | Log in";
		Assert.assertEquals(act, exp);
		
	}

	@Test(priority = 2)
	public void verifyLogo() {
		Boolean status = driver.findElement(By.tagName("img")).isDisplayed();
		Assert.assertTrue(status);
	}

	@Test(priority = 3)
	public void verifyingHeadingText() {
		
		String act=driver.findElement(By.tagName("b")).getText();
		String exp ="Java By Kiran";
		Assert.assertEquals(act, exp);
		
	}
	
	@Test(priority=4)
	public void verifyCoursesText() {
		String act=driver.findElement(By.tagName("h4")).getText();
		String exp ="JAVA | SELENIUM | PYTHON";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=5)
	public void verifyLoginBoxText() {
		String act=driver.findElement(By.tagName("p")).getText();
		String exp ="Sign in to start your session";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=6)
	public void verifyEmailPlaceholderText() {
		String act=driver.findElement(By.id("email")).getAttribute("placeholder");
		String exp ="Email";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=7)
	public void verifyPasswordPlaceholderText() {
		String act=driver.findElement(By.id("password")).getAttribute("placeholder");
		String exp ="Password";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=8)
	public void verifyTextWhenEmailIsBlank() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("email_error")).getText();
		String exp ="Please enter email";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=9)
	public void verifyTextWhenPasswordIsBlank() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("password_error")).getText();
		String exp ="Please enter password.";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=10)
	public void verifyEmailSuggestion() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com1");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("email_error")).getText();
		String exp ="Please enter email as kiran@gmail.com";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=11)
	public void verifyEmailErrorTextColor() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com1");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("email_error")).getAttribute("style");
		String exp ="color: red;";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=12)
	public void verifyPasswordSuggestion() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.xpath("//button")).click();
		String actText=driver.findElement(By.id("password_error")).getText();
		String expText="Please enter password 123456";
		Assert.assertEquals(actText,expText);
	}
	
	@Test(priority=13)
	public void verifyPasswordErrorTextColor() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12346");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("password_error")).getAttribute("style");
		String exp ="color: red;";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=14)
	public void validLogin() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		
		String act=driver.getTitle();
		String exp = "JavaByKiran | Dashboard";
		Assert.assertEquals(act, exp);
	}
	
	
}
