package herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BasicAuth {
	WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		BasicAuth basicAuth =new BasicAuth();
		basicAuth.initialSetup();
		basicAuth.sendValidDetails();
		Thread.sleep(5000);
		basicAuth.quit();
	}

	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		 
	}

	public void quit() {
		driver.quit();
	}
	
	public void sendValidDetails() {
		// format- username:password@website.com
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		String successMessage = driver.findElement(By.xpath("//div[@id='content']//div//p")).getText();
		Assert.assertEquals(successMessage, "Congratulations! You must have the proper credentials.");
	}

	

}

