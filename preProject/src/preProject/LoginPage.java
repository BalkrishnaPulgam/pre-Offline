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
		System.out.println("Actual Title : "+ act);
		String exp = "JavaByKiran | Log in";
		System.out.println("Expected Title : "+ exp);
		Assert.assertEquals(act, exp);
		
	}
	
	@Test(priority = 2)
	public void VerifyBrowserUrl() {
		String act = driver.getCurrentUrl();
		System.out.println("Actual Url : "+ act);
		String exp = "file:///E:/Selenium/Offline%20Website/Offline%20Website/index.html";
		System.out.println("Expected Url : "+ exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 3)
	public void verifyLogo() {
		Boolean status = driver.findElement(By.tagName("img")).isDisplayed();
		Assert.assertTrue(status);
	}

	@Test(priority = 4)
	public void verifyingHeadingText() {
		
		String act=driver.findElement(By.tagName("b")).getText();
		System.out.println("Actual: "+ act);
		String exp ="Java By Kiran";
		System.out.println("Expected: "+ exp);
		Assert.assertEquals(act, exp);
		
	}
	
	@Test(priority=5)
	public void verifyCoursesText() {
		String act=driver.findElement(By.tagName("h4")).getText();
		System.out.println("Actual: "+ act);
		String exp ="JAVA | SELENIUM | PYTHON";
		System.out.println("Expected: "+ exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=6)
	public void verifyLoginBoxText() {
		String act=driver.findElement(By.tagName("p")).getText();
		System.out.println("Actual: "+ act);
		String exp ="Sign in to start your session";
		System.out.println("Expected: "+ exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=7)
	public void verifyEmailPlaceholderText() {
		String act=driver.findElement(By.id("email")).getAttribute("placeholder");
		System.out.println("Actual Placeholder :" + act);
		String exp ="Email";
		System.out.println("Expected Placeholder :" + exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=8)
	public void verifyPasswordPlaceholderText() {
		String act=driver.findElement(By.id("password")).getAttribute("placeholder");
		System.out.println("Actual Placeholder :" + act);
		String exp ="Password";
		System.out.println("Expected Placeholder :" + exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=9)
	public void verifySuggestionWhenEmailIsBlank() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("email_error")).getText();
		System.out.println("Actual Suggestion :" + act);
		String exp ="Please enter email";
		System.out.println("Expected Suggestion :" + exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=10)
	public void verifySuggestionWhenPasswordIsBlank() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("password_error")).getText();
		System.out.println("Actual Suggestion :" + act);
		String exp ="Please enter password.";
		System.out.println("Expected Suggestion :" + exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=11,description = "verify email suggestion when email is wrong")
	public void verifyEmailSuggestion() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com1");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("email_error")).getText();
		System.out.println("Actual Suggestion :" + act);
		String exp ="Please enter email as kiran@gmail.com";
		System.out.println("Expected Suggestion :" + exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=12)
	public void verifyEmailErrorTextColor() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com1");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("email_error")).getAttribute("style");
		System.out.println("Actual clr = " + act);
		String exp ="color: red;";
		System.out.println("Expected clr = " + exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=13,description = "verify password suggestion when password is wrong")
	public void verifyPasswordSuggestion() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12345");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("password_error")).getText();
		System.out.println("Actual Suggestion :" + act);
		String exp="Please enter password 123456";
		System.out.println("Expected Suggestion :" + exp);
		Assert.assertEquals(act,exp);
	}
	
	@Test(priority=14)
	public void verifyPasswordErrorTextColor() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("12346");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.findElement(By.id("password_error")).getAttribute("style");
		System.out.println("Actual clr = " + act);
		String exp ="color: red;";
		System.out.println("Expected clr = " + exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=15)
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
	
	
	
	@Test(priority = 16)
	public void verifyBrowserUrlAfterValidLogin() {
		String act = driver.getCurrentUrl();
		System.out.println("Actual Browser Url: "+act);
		String exp = "file:///E:/Selenium/Offline%20Website/Offline%20Website/pages/examples/dashboard.html";
		System.out.println("Expected Browser Url: "+exp);
		Assert.assertEquals(act, exp);
	}
	
	
}
