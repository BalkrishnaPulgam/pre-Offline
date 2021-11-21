package preProject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
		driver.get("file:///E:/Selenium/Offline%20Website/Offline%20Website/pages/examples/dashboard.html");
	}

	@AfterSuite
	public void tearDown() {
		//driver.close();
	}

	
	@Test(priority=1)
	public void VerifyHeader() {
		String act=driver.findElement(By.xpath("//h1")).getText();
		System.out.println("Actual: "+ act);
		String exp="Dashboard Courses Offered";
		System.out.println("Expected: "+ exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 2)
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

	@Test(priority = 3)
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

	@Test(priority = 4)
	public void verifyMoreInfoCount() {
		List<WebElement> list = driver.findElements(By.className("small-box-footer"));
		int count = list.size();
		Assert.assertEquals(count, 4);
	}
	
	@Test(priority = 5)
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
	
	
	@Test(priority=6)
	public void checkUsersLink() {
		driver.findElement(By.linkText("Users")).click();
		String act=driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "JavaByKiran | User";
		System.out.println("Expected Browser Title: "+exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=7)
	public void checkOperatorsLink() {
		driver.findElement(By.linkText("Operators")).click();
		String act=driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "JavaByKiran | Operators";
		System.out.println("Expected Browser Title: "+exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=8)
	public void checkUsefulLinksLink() {
		driver.findElement(By.linkText("Useful Links")).click();
		String act=driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "JavaByKiran | Useful Links";
		System.out.println("Expected Browser Title: "+exp);
		Assert.assertEquals(act, exp);
	}

	@Test(priority=9)
	public void checkDownloadsLink() {
		driver.findElement(By.linkText("Downloads")).click();
		String act=driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "JavaByKiran | Downloads";
		System.out.println("Expected Browser Title: "+exp);
		Assert.assertEquals(act, exp);
	}
	
	@Test(priority=10)
	public void checkFooterLink() throws Exception {
		String mainWin = driver.getWindowHandle();
		driver.findElement(By.linkText("JavaByKiran")).click();
		Set<String> allWins=	driver.getWindowHandles();
		for (String window : allWins) {
			if(!mainWin.equals(window))
				driver.switchTo().window(window);
		}
	       		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Thread.sleep(6000);
		String act=driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "Java Classes in Pune | Selenium Training | Python Courses in pune";
		System.out.println("Expected Browser Title: "+exp);
		Assert.assertEquals(act, exp);
		
	}
	
	@Test(priority=11)
	public void checkLogoutLink() {
		String mainWin = driver.getWindowHandle();
		Set<String> allWins=	driver.getWindowHandles();
		for (String window : allWins) {
			if(!mainWin.equals(window))
				driver.switchTo().window(window);
		}
		driver.findElement(By.linkText("Logout")).click();
		String act=driver.getTitle();
		System.out.println("Actual Browser Title: "+act);
		String exp = "JavaByKiran | Log in";
		System.out.println("Expected Browser Title "+exp);
		Assert.assertEquals(act, exp);
	}

}
