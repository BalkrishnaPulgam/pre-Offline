package preProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
		String act = driver.getTitle();
		String exp = "JavaByKiran | User";
		Assert.assertEquals(act, exp);
	}

	@Test(priority = 2)
	public void verifyBrowserUrl() {
		String act = driver.getCurrentUrl();
		String exp = "file:///E:/Selenium/Offline%20Website/Offline%20Website/pages/examples/users.html";
		Assert.assertEquals(act, exp);
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
	
	@Test(priority = 5)
	public void verfyTotalNumberOfUser() throws Exception{
		List<WebElement> userList = driver.findElements(By.xpath("//tr//td[2]"));

		ArrayList<String> expList = new ArrayList<String>();

		DataFormatter df = new DataFormatter();

		FileInputStream input = new FileInputStream("ExcelData/CoursesList.xlsx");
		Workbook wb = WorkbookFactory.create(input);
		Sheet sh = wb.getSheet("AllData");
		int count = sh.getLastRowNum();

		for (int i = 1; i <= count; i++) {
			Cell cell = sh.getRow(i).getCell(1);
			String text = df.formatCellValue(cell);
			expList.add(text);
		}
		
		ArrayList<String> actList = new ArrayList<String>();

		for (int i = 0; i < userList.size(); i++) {
			
				String text = userList.get(i).getText();
				actList.add(text);
			
		}

		Assert.assertEquals(actList, expList);
	}
	

	@Test(priority = 5)
	public void VerifyMaleUsers() {

		List<WebElement> genderList = driver.findElements(By.xpath("//tr//td[6]"));
		List<WebElement> userList = driver.findElements(By.xpath("//tr//td[2]"));

		ArrayList<String> actList = new ArrayList<String>();
		actList.add("Kiran");
		actList.add("Sagar");

		ArrayList<String> expList = new ArrayList<String>();

		for (int i = 0; i < genderList.size(); i++) {
			if (genderList.get(i).getText().equals("Male")) {
				String name = userList.get(i).getText();
				System.out.println(name);
				expList.add(name);
			}
		}

		Assert.assertEquals(actList, expList);

	}

	@Test(priority = 6)
	public void VerifyFemaleUsers() throws Exception {

		List<WebElement> genderList = driver.findElements(By.xpath("//tr//td[6]"));
		List<WebElement> userList = driver.findElements(By.xpath("//tr//td[2]"));

		ArrayList<String> expList = new ArrayList<String>();

		DataFormatter df = new DataFormatter();

		FileInputStream input = new FileInputStream("ExcelData/CoursesList.xlsx");
		Workbook wb = WorkbookFactory.create(input);
		Sheet sh = wb.getSheet("FemaleUsers");
		int count = sh.getLastRowNum();

		for (int i = 0; i <= count; i++) {
			Cell cell = sh.getRow(i).getCell(0);
			String text = df.formatCellValue(cell);
			expList.add(text);
		}

		ArrayList<String> actList = new ArrayList<String>();

		for (int i = 0; i < genderList.size(); i++) {
			if (genderList.get(i).getText().equals("Female")) {
				String text = userList.get(i).getText();
				actList.add(text);
			}
		}

		Assert.assertEquals(actList, expList);

	}

	@Test(priority = 7, description = "verifying that users from Maharashtra")
	public void VerifyStateUsers() throws Exception {

		List<WebElement> state = driver.findElements(By.xpath("//tr//td[7]"));
		List<WebElement> users = driver.findElements(By.xpath("//tr//td[2]"));

		ArrayList<String> expList = new ArrayList<String>();

		DataFormatter df = new DataFormatter();

		FileInputStream fis = new FileInputStream("ExcelData/CoursesList.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("AllData");
		int count = sh.getLastRowNum();

		for (int i = 0; i <= count; i++) {

			Cell cell = sh.getRow(i).getCell(6);
			String text = df.formatCellValue(cell);

			if (text.contains("Maharashtra")) {
				Cell cell1 = sh.getRow(i).getCell(1);
				String text1 = df.formatCellValue(cell1);

				expList.add(text1);
			}
		}

		ArrayList<String> actList = new ArrayList<String>();

		for (int i = 0; i < state.size(); i++) {
			if (state.get(i).getText().equals("Maharashtra")) {
				String text = users.get(i).getText();
				actList.add(text);
			}
		}

		Assert.assertEquals(actList, expList);

	}

	@Test(priority = 8, description = "verifying that Kiran having Java/J2EE course")
	public void VerifyKiranCourse() throws Exception {

		List<WebElement> courses = driver.findElements(By.xpath("//tr//td[5]"));
		List<WebElement> users = driver.findElements(By.xpath("//tr//td[2]"));

		ArrayList<String> actList = new ArrayList<String>();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getText().equals("Kiran")) {
				String text = courses.get(i).getText();
				System.out.println(text);
				actList.add(text);
			}
		}

		ArrayList<String> expList = data("ExcelData/CoursesList.xlsx", "AllData", 1, 4, "Kiran");

		Assert.assertEquals(actList, expList);

	}

	@Test(priority = 9, description = "verifying that Sagar from Punjab")
	public void VerifySagarState() throws Exception {
		List<WebElement> state = driver.findElements(By.xpath("//tr//td[7]"));
		List<WebElement> users = driver.findElements(By.xpath("//tr//td[2]"));

		ArrayList<String> actList = new ArrayList<String>();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getText().equals("Sagar")) {
				String text = state.get(i).getText();
				System.out.println(text);
				actList.add(text);
			}
		}

		ArrayList<String> expList = data("ExcelData/CoursesList.xlsx", "AllData", 1, 6, "Sagar");

		Assert.assertEquals(actList, expList);

	}

	public ArrayList<String> data(String filePath, String SheetName, int refColNumber, int OutputColNumber, String txt)
			throws Exception {
		ArrayList<String> expList = new ArrayList<String>();

		DataFormatter df = new DataFormatter();

		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int count = sh.getLastRowNum();

		for (int i = 0; i <= count; i++) {

			Cell cell = sh.getRow(i).getCell(refColNumber);
			String text = df.formatCellValue(cell);

			if (text.contains(txt)) {
				Cell cell1 = sh.getRow(i).getCell(OutputColNumber);
				String text1 = df.formatCellValue(cell1);
				System.out.println(text1);
				expList.add(text1);
			}
		}
		return expList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*
	 * 
	 * @Test(priority = 5, dataProvider = "userData") public void VerifyData(String
	 * srNo, String uname, String email, String mobile, String course, String
	 * gender, String State, String action) { ArrayList<String> expData = new
	 * ArrayList<String>(); expData.add(srNo); expData.add(uname);
	 * expData.add(email); expData.add(mobile); expData.add(course);
	 * expData.add(gender); expData.add(State); expData.add(action);
	 * 
	 * ArrayList<String> actData = new ArrayList<String>();
	 * 
	 * for (int i = 1; i <= 8; i++) { WebElement data =
	 * driver.findElement(By.xpath("//tr[" + row + "]//td[" + i + "]")); String text
	 * = data.getText(); actData.add(text); } row++;
	 * 
	 * Assert.assertEquals(actData, expData);
	 * 
	 * }
	 * 
	 * @DataProvider public Object[][] userData() { return new Object[][] { new
	 * Object[] { "1", "Kiran", "kiran@gmail.com", "9898989898", "Java/J2EE",
	 * "Male", "Maharashtra", "Delete" }, new Object[] { "2", "Sagar",
	 * "sagar@gmail.com", "999999999", "Selenium", "Male", "Punjab", "Delete" }, new
	 * Object[] { "3", "Monica", "monica@gmail.com", "1111111111", "Python",
	 * "Female", "Maharashtra", "Delete" }, new Object[] { "4", "Kimaya",
	 * "kimaya@gmail.com", "999999999", "PHP", "Female", "Punjab", "Delete" },
	 * 
	 * }; }
	 */
}
