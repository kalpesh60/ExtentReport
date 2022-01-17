package DemoA.ExtentReportDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportTest {
	static ExtentReports extent;
	static ExtentSparkReporter extentSparkReporter; //You can use one or more reporters to create different types of reports

	@BeforeClass
	public void BeforeClass() {
		ExtentReports extentReports = new ExtentReports();
		extent = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter("report.html");
//		an instance of ExtentSparkReporter (the Observer) is attached to ExtentReports (the Subject),
//		which notifies ExtentSparkReporter of any changes in state such as creation of tests, adding logs etc.
		extent.attachReporter(extentSparkReporter);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setDocumentTitle("Automation");
	}

	@Test
	public void f() {
		//The createTest method returns a ExtentTest object which can be used to publish logs
		ExtentTest test = extent.createTest("MyTest");
		System.setProperty("webdriver.chrome.driver",
				"/Users/kalpe/eclipse-workspace/SeleniumConcepts/Driver1/chromedriver.exe");
		test.log(Status.INFO, "Launching chrome browser");
		test.log(Status.INFO, "Lunching chrome browser");
		WebDriver driver = new ChromeDriver();
		test.info("Opening URL");
		driver.get("https://en-gb.facebook.com/");
		test.info("Clicking on sign in button");
		WebElement element = driver.findElement(By.name("login"));
		element.click();
		test.pass("Test has passesd");
		driver.close();
		test.info("close the session");
	}

	@Test
	public void f1() {
		ExtentTest test = extent.createTest("MyTest1");
		System.setProperty("webdriver.chrome.driver",
				"/Users/kalpe/eclipse-workspace/SeleniumConcepts/Driver1/chromedriver.exe");
		test.log(Status.INFO, "Launching chrome browser");
		WebDriver driver = new ChromeDriver();
		test.info("Opening URL");
		driver.get("https://en-gb.facebook.com/");
		test.info("Clicking on sign in button");
		try {
			driver.findElement(By.name("login")).click();
		} catch (NoSuchElementException e) {
			test.fail("Test has failed");
		}
		driver.close();
		test.info("close the session");
	}

	@Test
	public void f2() {
		ExtentTest test = extent.createTest("MyTest2");
		System.setProperty("webdriver.chrome.driver",
				"/Users/kalpe/eclipse-workspace/SeleniumConcepts/Driver1/chromedriver.exe");
		test.log(Status.INFO, "Launching chrome browser");
		WebDriver driver = new ChromeDriver();
		test.info("Opening URL");
		driver.get("https://en-gb.facebook.com/");
		String titleString = driver.getTitle();
		System.out.println("title" + titleString);
		if (driver.getTitle().equals("Facebook – log in or sign u")) {
			test.log(Status.PASS, "Navigated to the specified URL");
		} else {
			test.fail("Test Failed");
		}
		driver.close();
		test.info("close the session");
	}

	@AfterClass
	public void AfterClass() {
		extent.flush();     //Flush method is used to erase any previous data on the report and create a new report
	}                       //The line extent.flush() instructs ExtentReports write the test information to a destination
}
