//package commons;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.joda.time.DateTime;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.opera.OperaDriver;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.annotations.BeforeSuite;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class BaseTest{
//	private WebDriver driver;
//	protected final Log log;
//	
//	@BeforeSuite
//	public void initBeforeSuite() {
//		DeleteAllureReport();
//	}
//	
//	protected BaseTest() {
//		log = LogFactory.getLog(getClass());
//	}
//	
//	public WebDriver getBrowserDriver(String browserName) {
//		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
//		
//		if (browserList == BrowserList.FIREFOX) {
//			WebDriverManager.firefoxdriver().setup();
//			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
//			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\browserLogs\\FirefoxLog.log");
//			driver = new FirefoxDriver();
//			
//		} else if (browserList == BrowserList.H_FIREFOX) {
//			WebDriverManager.firefoxdriver().setup();
//			FirefoxOptions options = new FirefoxOptions();
//			options.addArguments("--headless");
//			options.addArguments("window-size = 1920x1080");
//			driver = new FirefoxDriver(options);
//			
//		} else if (browserList == BrowserList.CHROME) {
//			WebDriverManager.chromedriver().setup();
//			System.setProperty("webdriver.chrome.args", "--disable-logging");
//			System.setProperty("webdriver.chrome.silentOutput", "true");
//			
//			ChromeOptions options = new ChromeOptions();
//			options.setExperimentalOption("useAutomationExtension", false);
//			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//			driver = new ChromeDriver();
//			
//		} else if (browserList == BrowserList.H_CHROME) {
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless");
//			options.addArguments("window-size = 1920x1080");
//			driver = new ChromeDriver(options);
//			
//		} else if (browserList == BrowserList.EDGE) {
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//			
//		} else if (browserList == BrowserList.IE) {
//			WebDriverManager.iedriver().arch32().setup();;
//			driver = new InternetExplorerDriver();
//			
//		} else if (browserList == BrowserList.OPERA) {
//			WebDriverManager.operadriver().setup();
//			driver = new OperaDriver();
//			
//		} else if (browserList == BrowserList.COCCOC) {
//			WebDriverManager.chromedriver().driverVersion("100.0.4896.60").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
//			driver = new ChromeDriver(options);
//			
//		} else if (browserList == BrowserList.BRAVE) {
//			WebDriverManager.chromedriver().driverVersion("101.0.4951.41").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
//			driver = new ChromeDriver(options);
//			
//		} else {
//			throw new RuntimeException("Browser Name Invalid");
//		}
//		
//		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
//		driver.get(GlobalConstants.PORTAL_PAGE_URL);
//		return driver;
//	}
//	
//	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
//		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
//		
//		if (browserList == BrowserList.FIREFOX) {
//			WebDriverManager.firefoxdriver().setup();
//			FirefoxOptions options = new FirefoxOptions();
//			options.setAcceptInsecureCerts(true);
//			driver = new FirefoxDriver(options);
//			
//		} else if (browserList == BrowserList.H_FIREFOX) {
//			WebDriverManager.firefoxdriver().setup();
//			FirefoxOptions options = new FirefoxOptions();
//			options.addArguments("--headless");
//			options.addArguments("window-size = 1920x1080");
//			driver = new FirefoxDriver(options);
//			
//		} else if (browserList == BrowserList.CHROME) {
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setAcceptInsecureCerts(true);
//			driver = new ChromeDriver(options);
//			
//		} else if (browserList == BrowserList.H_CHROME) {
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless");
//			options.addArguments("window-size = 1920x1080");
//			driver = new ChromeDriver(options);
//			
//		} else if (browserList == BrowserList.EDGE) {
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//			
//		} else if (browserList == BrowserList.IE) {
//			WebDriverManager.iedriver().arch32().setup();;
//			driver = new InternetExplorerDriver();
//			
//		} else if (browserList == BrowserList.OPERA) {
//			WebDriverManager.operadriver().setup();
//			driver = new OperaDriver();	
//			
//		} else if (browserList == BrowserList.COCCOC) {
//			WebDriverManager.chromedriver().driverVersion("100.0.4896.60").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
//			driver = new ChromeDriver(options);
//			
//		} else if (browserList == BrowserList.BRAVE) {
//			WebDriverManager.chromedriver().driverVersion("101.0.4951.41").setup();
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
//			driver = new ChromeDriver(options);
//			
//		} else {
//			throw new RuntimeException("Browser Name Invalid");
//		}
//		
//		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
//		driver.get(appUrl);
//		return driver;
//	}
//	
//	public String getEnvironment(String environmentName) {
//		String envUrl = null;
//		EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
//		switch (environment) {
//		case DEV:
//			envUrl = "https://demo.nopcommerce.com/";
//			break;
//		case TESTING:
//			envUrl = "";
//			break;
//		case STAGING:
//			envUrl = "";
//			break;
//		case PRE_PROD:
//			envUrl = "";
//			break;
//		case PROD:
//			envUrl = "";
//			break;
//		default:
//			envUrl = null;
//			break;
//		}
//		return envUrl;
//	}
//	
//	public WebDriver getDriver() {
//		return this.driver;
//	}
//	
//	public int getRandomNumber() {
//		Random random = new Random();
//		return random.nextInt(9999);
//	}
//
//	private boolean checkTrue(boolean condition) {
//		boolean pass = true;
//		try {
//			if (condition == true) {
//				log.info(" -------------------------- PASSED -------------------------- ");
//			} else {
//				log.info(" -------------------------- FAILED -------------------------- ");
//			}
//			Assert.assertTrue(condition);
//		} catch (Throwable e) {
//			pass = false;
//
//			// Add lỗi vào ReportNG
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
//		}
//		return pass;
//	}
//
//	protected boolean verifyTrue(boolean condition) {
//		return checkTrue(condition);
//	}
//
//	private boolean checkFailed(boolean condition) {
//		boolean pass = true;
//		try {
//			if (condition == false) {
//				log.info(" -------------------------- PASSED -------------------------- ");
//			} else {
//				log.info(" -------------------------- FAILED -------------------------- ");
//			}
//			Assert.assertFalse(condition);
//		} catch (Throwable e) {
//			pass = false;
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
//		}
//		return pass;
//	}
//
//	protected boolean verifyFalse(boolean condition) {
//		return checkFailed(condition);
//	}
//
//	private boolean checkEquals(Object actual, Object expected) {
//		boolean pass = true;
//		try {
//			Assert.assertEquals(actual, expected);
//			log.info(" -------------------------- PASSED -------------------------- ");
//		} catch (Throwable e) {
//			pass = false;
//			log.info(" -------------------------- FAILED -------------------------- ");
//			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			Reporter.getCurrentTestResult().setThrowable(e);
//		}
//		return pass;
//	}
//
//	protected boolean verifyEquals(Object actual, Object expected) {
//		return checkEquals(actual, expected);
//	}
//	
//	public void DeleteAllureReport() {
//		try {
//			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
//			File file = new File(pathFolderDownload);
//			File[] listOfFiles = file.listFiles();
//			for (int i=0 ; i<listOfFiles.length; i++) {
//				if(listOfFiles[i].isFile()) {
//					new File(listOfFiles.toString()).delete();
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	protected void closeBrowserAndDriver() {
//		String cmd = "";
//		try {
//			String osName = System.getProperty("os.name").toLowerCase();
//			log.info("OS name = " + osName);
//
//			String driverInstanceName = driver.toString().toLowerCase();
//			log.info("Driver instance name = " + driverInstanceName);
//
//			if (driverInstanceName.contains("chrome")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
//				} else {
//					cmd = "pkill chromedriver";
//				}
//			} else if (driverInstanceName.contains("internetexplorer")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
//				}
//			} else if (driverInstanceName.contains("firefox")) {
//				if (osName.contains("windows")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
//				} else {
//					cmd = "pkill geckodriver";
//				}
//			} else if (driverInstanceName.contains("edge")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
//				} else {
//					cmd = "pkill msedgedriver";
//				}
//			} else if (driverInstanceName.contains("opera")) {
//				if (osName.contains("window")) {
//					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
//				} else {
//					cmd = "pkill operadriver";
//				}
//			} else if (driverInstanceName.contains("safari")) {
//				if (osName.contains("mac")) {
//					cmd = "pkill safaridriver";
//				}
//			}
//
//			if (driver != null) {
//				// IE: lưu lại các phiên đăng nhập trước đó nên cần xóa cookies đi
//				driver.manage().deleteAllCookies();
//				driver.quit();
//			}
//		} catch (Exception e) {
//			log.info(e.getMessage());
//		} finally {
//			try {
//				Process process = Runtime.getRuntime().exec(cmd);
//				process.waitFor();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	protected String getCurrentDay() {
//		DateTime nowUTC = new DateTime();
//		int day = nowUTC.getDayOfMonth();
//		if (day < 10) {
//			String dayValue = "0" + day;
//			return dayValue;
//		}
//		return String.valueOf(day);
//	}
//
//	protected String getCurrentMonth() {
//		DateTime now = new DateTime();
//		int monthNumber = now.getMonthOfYear();
//		String monthText = null;
////		if (month < 10) {
////			String monthValue = "0" + month;
////			return monthValue;
////		}
////		return month + "";
//		
//		switch(monthNumber) {
//		case 1:
//			monthText = "January";
//			break;
//		case 2:
//			monthText = "February";
//			break;
//		case 3:
//			monthText = "March";
//			break;
//		case 4:
//			monthText = "April";
//			break;
//		case 5:
//			monthText = "May";
//			break;
//		case 6:
//			monthText = "June";
//			break;
//		case 7:
//			monthText = "July";
//			break;
//		case 8:
//			monthText = "August";
//			break;
//		case 9:
//			monthText = "September";
//			break;
//		case 10:
//			monthText = "October";
//			break;
//		case 11:
//			monthText = "November";
//			break;
//		case 12:
//			monthText = "December";
//			break;
//		}
//		return monthText;
//	}
//
//	protected String getCurrentYear() {
//		DateTime now = new DateTime();
//		return String.valueOf(now.getYear());
//	}
//
//	protected String getToday() {
//		return getCurrentMonth() + " " + getCurrentDay() + ", " + getCurrentYear();
//	}
//
//}
