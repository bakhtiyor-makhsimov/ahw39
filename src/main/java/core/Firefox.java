package core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Firefox {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

           Logger logger = Logger.getLogger("");
           logger.setLevel(Level.OFF);

           String driverPath = "";

           String url = "http://facebook.com/";
           String email_address = "publicbmuser@smsstam.net";
           String password = "Buska@2017";

			if (System.getProperty("os.name").toUpperCase().contains("MAC"))
				driverPath = "./resources/webdrivers/mac/geckodriver.sh";
			else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
				driverPath = "./resources/webdrivers/pc/geckodriver.exe";
			else throw new IllegalArgumentException("Unknown OS");

			System.setProperty("webdriver.gecko.driver", driverPath);

			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, 15);
			driver.get(url);

			wait.until(ExpectedConditions.titleIs("Facebook - Log In or Sign Up"));
			
			String title = driver.getTitle();

	String copyright = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span"))).getText();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).clear();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys(email_address);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).clear();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pass"))).sendKeys(password);
	wait.until(ExpectedConditions.elementToBeClickable(By.id("loginbutton"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='u_0_b']/div[1]/div[1]/div/a/span/span"))).click();
	String friends = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/ul/li[3]/a/span[1]"))).getText();
    if (friends.length() == 0){
    	friends = "NO";}
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userNavigationLabel"))).click(); 
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Log Out']"))).click();  

	driver.quit();
	
	System.out.println("Browser is: Firefox");
	System.out.println("Title of the page: " + title);
	System.out.println("Copyright: " + copyright);
	System.out.println("You have " + friends + " friends");
	}
}