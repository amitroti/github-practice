package herokuapp;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ABTesting {

	WebDriver driver;

	public static void main(String[] args) {

		ABTesting aBTesting = new ABTesting();

		aBTesting.initialSetup();
		aBTesting.WithCookieAfterVisitingPage();
		aBTesting.WithOptOutUrl();
		aBTesting.quit();

	}

	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/abtest");
	}
 
	public void quit() {
		driver.quit();
	}

	public void WithCookieAfterVisitingPage() {

		String headingText; // Store header text

		// asserting the header test before adding cookie
		headingText = driver.findElement(By.tagName("h3")).getText();
		assertTrue(headingText.contains("A/B Test"));

		driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
		driver.navigate().refresh();

		// asserting the header test before adding cookie
		headingText = driver.findElement(By.cssSelector("h3")).getText();
		assertTrue(headingText.contains("No A/B Test"));
	}

	// Setting opt out in URL query params

	public void WithOptOutUrl() {
		driver.get("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true");
		driver.switchTo().alert().dismiss();
		assertTrue(driver.findElement(By.cssSelector("h3")).getText().contains("No A/B Test"));
	}

}
