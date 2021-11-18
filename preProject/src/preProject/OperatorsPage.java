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

public class OperatorsPage {

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
		driver.findElement(By.linkText("Operators")).click();
		String act=driver.getTitle();
		String exp = "JavaByKiran | Operators";
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 2)
	public void verifyBrowserUrl() {
		String act=driver.getCurrentUrl();
		String exp = "file:///E:/Selenium/Offline%20Website/Offline%20Website/pages/examples/operators.html";
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 3)
	public void VerifyHeader() {
		String actText = driver.findElement(By.xpath("//h1")).getText();
		String expTest = "Operators";
		Assert.assertEquals(actText, expTest);
	}

	@Test(priority = 4)
	public void VerifyBoxHeader() {
		String actText = driver.findElement(By.xpath("//h3")).getText();
		String expTest = "Operator List";
		Assert.assertEquals(actText, expTest);
	}

	@Test(priority = 4)
	public void VerifyColumnName() {

		ArrayList<String> expList = new ArrayList<String>();
		expList.add("ID");
		expList.add("Person");
		expList.add("For");
		expList.add("Prefered Way to Connect");
		expList.add("Contact");
		expList.add("Timings");
		

		ArrayList<String> actList = new ArrayList<String>();

		List<WebElement> actText = driver.findElements(By.xpath("//tr//th"));
		for (WebElement webElement : actText) {
			String text = webElement.getText();
			actList.add(text);
		}

		Assert.assertEquals(actList, expList);
	}

	@Test(priority = 5,dataProvider = "operatorsData")
	public void VerifyData(String id, String uname, String userFor, String connect, String mobile, String timing) {
		ArrayList<String> expData = new ArrayList<String>();
		expData.add(id);
		expData.add(uname);
		expData.add(userFor);
		expData.add(connect);
		expData.add(mobile);
		expData.add(timing);

		ArrayList<String> actData = new ArrayList<String>();

		for (int i = 1; i <= 6; i++) {
			WebElement data = driver.findElement(By.xpath("//tr[" + row + "]//td[" + i + "]"));
			String text=data.getText();
			actData.add(text);
		}
		row++;
		
		Assert.assertEquals(actData, expData);

	}

	@DataProvider
	public Object[][] operatorsData() {
		return new Object[][] {
				new Object[] { "01", "Kiran", "Urgent Technical Help", "Whats App Only", "9552343698", "07:00 AM to 10:00 PM Monday-Sunday"},
				new Object[] { "02", "Neelam", "Technical Discussion (Errors, Software, Technical Materials)", "Whats App Phone Call SMS eMail", "7066885937", "09:00 AM to 06:00 PM Monday-Saturday"},
				new Object[] { "03", "Seema", "Administration (Fees, ID Card, Certificates, WhatsApp Group, Enquiry)", "Whats App Phone Call SMS eMail", "8888558802", "09:00 AM to 06:00 PM Monday-Saturday"},
				new Object[] { "04", "Varsha", "Enquiry(Course Details, Fees, Enquiry)", "Whats App Phone Call SMS eMail", "8888809416", "09:00 AM to 06:00 PM Monday to Friday and Sunday"},
				new Object[] { "05", "Darshit", "Technical Help", "Whats App Only", "8866888662", "08:30 AM to 02:00 PM Saturday-Sunday"},
		};
	}

}
