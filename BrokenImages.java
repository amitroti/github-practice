package herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.restassured.RestAssured;


// https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.1.1

// https://mvnrepository.com/artifact/org.codehaus.groovy/groovy-all

public class BrokenImages {
	
	WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		BrokenImages brokenImages =new BrokenImages();
		brokenImages.initialSetup();
		brokenImages.statusCodeOfImage();
		Thread.sleep(5000);
		brokenImages.quit();
	}

	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/broken_images");
	}
	
	public void quit() {
		driver.quit();
	}
	// Using REST-Assured to check the status
		 
		public void statusCodeOfImage() {
			
			int statuscode;
			String imageURL;

			imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[1]"))
					.getAttribute("src");
			statuscode = RestAssured.get(imageURL).statusCode();
			Assert.assertEquals(statuscode, 404);

			imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[2]"))
					.getAttribute("src");
			statuscode = RestAssured.get(imageURL).statusCode();
			Assert.assertEquals(statuscode, 404);

			imageURL = driver.findElement(By.xpath("//div[@class='large-12 columns']//img[3]"))
					.getAttribute("src");
			statuscode = RestAssured.get(imageURL).statusCode();
			Assert.assertEquals(statuscode, 200);
		}

}
