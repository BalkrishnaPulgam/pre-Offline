package preProject;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserPage {

	WebDriver driver;
	int row = 2;

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
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.linkText("Users")).click();
		Assert.assertEquals(driver.getTitle(), "JavaByKiran | User");
	}

	@Test(priority = 2)
	public void verifyCurrentTitle() {
		String expUrl = "file:///E:/Selenium/Offline%20Website/Offline%20Website/pages/examples/users.html";
		Assert.assertEquals(driver.getCurrentUrl(), expUrl);
	}

	@Test(priority = 3)
	public void VerifyHeader() {
		String actText = driver.findElement(By.xpath("//h1")).getText();
		String expTest = "Users";
		Assert.assertEquals(actText, expTest);
	}

	@Test(priority = 4)
	public void VerifyBoxHeader() {
		String actText = driver.findElement(By.xpath("//h3")).getText();
		String expTest = "User List";
		Assert.assertEquals(actText, expTest);
	}

	@Test(priority = 4)
	public void VerifyColumnName() {

		ArrayList<String> expList = new ArrayList<String>();
		expList.add("#");
		expList.add("Username");
		expList.add("Email");
		expList.add("Mobile");
		expList.add("Course");
		expList.add("Gender");
		expList.add("State");
		expList.add("Action");

		ArrayList<String> actList = new ArrayList<String>();

		List<WebElement> actText = driver.findElements(By.xpath("//tr//th"));
		for (WebElement webElement : actText) {
			String text = webElement.getText();
			actList.add(text);
		}

		Assert.assertEquals(actList, expList);
	}

	@Test(priority = 5,dataProvider = "userData")
	public void VerifyData(String srNo, String uname, String email, String mobile, String course, String gender,
			String State, String action) {
		ArrayList<String> expData = new ArrayList<String>();
		expData.add(srNo);
		expData.add(uname);
		expData.add(email);
		expData.add(mobile);
		expData.add(course);
		expData.add(gender);
		expData.add(State);
		expData.add(action);

		ArrayList<String> actData = new ArrayList<String>();

	
		for (int i = 1; i <= 8; i++) {
			WebElement data = driver.findElement(By.xpath("//tr[" + row + "]//td[" + i + "]"));
			String text=data.getText();
			actData.add(text);
		}
		row++;
		
		Assert.assertEquals(actData, expData);

	}

	@DataProvider
	public Object[][] userData() {
		return new Object[][] {
				new Object[] { "1", "Kiran", "kiran@gmail.com", "9898989898", "Java/J2EE", "Male", "Maharashtra",
						"Delete" },
				new Object[] { "2", "Sagar", "sagar@gmail.com", "999999999", "Selenium", "Male", "Punjab", "Delete" },
				new Object[] { "3", "Monica", "monica@gmail.com", "1111111111", "Python", "Female", "Maharashtra",
						"Delete" },
				new Object[] { "4", "Kimaya", "kimaya@gmail.com", "999999999", "PHP", "Female", "Punjab", "Delete" },

		};
	}

}
