package preProject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DashboardPage {

	WebDriver driver;

	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///E:/Selenium/Offline%20Website/Offline%20Website/index.html");
	}

	@AfterSuite
	public void tearDown() {
		//driver.close();
	}

	@Test(priority = 1)
	public void verifyBrowserTitle() {
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		String act=driver.getTitle();
		String exp = "JavaByKiran | Dashboard";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=2)
	public void VerifyHeader() {
		String actText=driver.findElement(By.xpath("//h1")).getText();
		System.out.println(actText);
		String expTest="Dashboard Courses Offered";
		Assert.assertEquals(actText, expTest);
	}

	@Test(priority = 3)
	public void verifyMenu() {
		ArrayList<String> actlist = new ArrayList<String>();
		actlist.add("Dashboard");
		actlist.add("Users");
		actlist.add("Operators");
		actlist.add("Useful Links");
		actlist.add("Downloads");
		actlist.add("Logout");

		ArrayList<String> expList = new ArrayList<String>();
		List<WebElement> list = driver.findElements(By.xpath("//li//span"));
		for (WebElement webElement : list) {
			String text = webElement.getText();
			expList.add(text);
		}

		Assert.assertEquals(actlist, expList);

	}

	@Test(priority = 4)
	public void verifyCourses() {
		ArrayList<String> actlist = new ArrayList<String>();
		actlist.add("Selenium");
		actlist.add("Java / J2EE");
		actlist.add("Python");
		actlist.add("Php");

		ArrayList<String> expList = new ArrayList<String>();
		List<WebElement> list = driver.findElements(By.xpath("//h3"));
		for (WebElement webElement : list) {
			String text = webElement.getText();
			expList.add(text);
		}

		Assert.assertEquals(actlist, expList);
	}

	@Test(priority = 5)
	public void verifyMoreInfoCount() {
		List<WebElement> list = driver.findElements(By.className("small-box-footer"));
		int count = list.size();
		Assert.assertEquals(count, 4);
	}

	@Test(priority = 6)
	public void verifyCourses1() throws Exception {

		List<String> excelList = new ArrayList<String>();

		DataFormatter df = new DataFormatter();

		FileInputStream fis = new FileInputStream("ExcelData/CoursesList.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int count = sh.getLastRowNum();

		for (int i = 0; i <= count; i++) {
			Cell cell = sh.getRow(i).getCell(0);
			String text = df.formatCellValue(cell);
			System.out.println(text);
			excelList.add(text);
		}

		List<String> actList = new ArrayList<String>();

		List<WebElement> list = driver.findElements(By.xpath("//h3//following::p"));
		for (WebElement webElement : list) {
			String text = webElement.getText();
			actList.add(text);
		}

		Assert.assertEquals(actList, excelList);
	}
	
	
	@Test(priority=7)
	public void checkUsersLink() {
		driver.findElement(By.linkText("Users")).click();
		String act=driver.getTitle();
		String exp = "JavaByKiran | User";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=8)
	public void checkOperatorsLink() {
		driver.findElement(By.linkText("Operators")).click();
		String act=driver.getTitle();
		String exp = "JavaByKiran | Operators";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=9)
	public void checkUsefulLinksLink() {
		driver.findElement(By.linkText("Useful Links")).click();
		String act=driver.getTitle();
		String exp = "JavaByKiran | Useful Links";
		Assert.assertEquals(act, exp);
	}

	@Test(priority=10)
	public void checkDownloadsLink() {
		driver.findElement(By.linkText("Downloads")).click();
		String act=driver.getTitle();
		String exp = "JavaByKiran | Downloads";
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=11)
	public void checkFooterLink() {
		driver.findElement(By.linkText("JavaByKiran")).click();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		String act=driver.getTitle();
		String exp = "Java Classes in Pune | Selenium Training | Python Courses in pune";
		Assert.assertEquals(act, exp);
		
	}
	
	@Test(priority=12)
	public void checkLogoutLink() {
		driver.findElement(By.linkText("Logout")).click();
		String act=driver.getTitle();
		String exp = "JavaByKiran | Log in";
		Assert.assertEquals(act, exp);
	}

}
