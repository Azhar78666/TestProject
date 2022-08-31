package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties properties = new Properties();
	public static FileInputStream fis;
	public static String screenshotpath;
	public static String propFilePath = ".\\src\\test\\resources\\Config.properties";
	public static String urlPath = System.getProperty("user.dir") + "/src/test/resources/runner/QE-index.html";

	@BeforeSuite
	public static void setup() {
		try {
			fis = new FileInputStream(propFilePath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			properties.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (properties.getProperty("Browser").equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (properties.getProperty("Browser").equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		driver.get(urlPath);
		driver.manage().window().maximize();
	}

	@AfterSuite
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static void captureScreenshot(String methodName) {

		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		int date = cal.get(Calendar.DATE);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		screenshotpath = "./src/test/resources/screenshot/" + methodName + "_" + year + "_" + (month + 1) + "_" + date
				+ "_" + day + "_" + min + "_" + sec + ".jpeg";
		try {
			FileUtils.copyFile(scrFile, new File(screenshotpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void type(WebElement element, String value) {

		try {

			element.clear();
			element.sendKeys(value);

		} catch (Exception e) {
			Assert.fail("Element is not found!!!");
		}

	}

	
	public static void click(WebElement ele) {

		try {
			ele.click();

		} catch (Exception e) {

			Assert.fail("Element is not found!!!");
		}
	}
	
		public static void waitclick(WebElement locator, int timeOut) throws Exception {
		
			
			  Instant now = Instant.now(); while
			  (Instant.now().isBefore(now.plusSeconds(timeOut))) {
			 	try {
				new WebDriverWait(driver, Duration.ofSeconds(timeOut)).until(ExpectedConditions.elementToBeClickable(locator)).click();

				return;
			} catch (WebDriverException e) {
				// do nothing, loop again
			}
		}
	}

		public static void scrollIntoViewElement(WebElement element)
		{
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			  js.executeScript("arguments[0].scrollIntoView();", element);
			 
		}
	

	// Test 1
	public static By home = By.xpath("//a[@href='#' and @class='nav-link']");
	public static By emailBox = By.id("inputEmail");
	public static By passwordBox = By.id("inputPassword");
	public static By loginBtn = By.xpath("//button[@class='btn btn-lg btn-primary btn-block']");
	// Test 2
	public static By list = By.xpath("//ul[@class='list-group']//li");
	public static By test2value = By.xpath("//ul[@class='list-group']//li[2]");
	// Test 3
	public static By dropdown = By.id("dropdownMenuButton");
	public static By option3 = By.xpath("//a[text()='Option 3']");
	// Test 4
	public static By firstbutton = By.xpath("//div[@id='test-4-div']//button[@class='btn btn-lg btn-primary']");
	public static By secondbutton = By.xpath("//div[@id='test-4-div']//button[@class='btn btn-lg btn-secondary']");
	// Test 5
	public static By button5 = By.xpath("//button[@id='test5-button']");
	public static By message = By.xpath("//div[@id='test5-alert']");
	// Test 6
	public static By row = By.xpath("//table[@class='table table-bordered table-dark']//tr");
	public static By col = By.xpath("//table[@class='table table-bordered table-dark']//tr[2]//td");
	public static By lastcell = By.xpath("//table[@class='table table-bordered table-dark']//tr[3]//td[3]");

}