package herokuapp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Checkboxes {

	WebDriver driver;
	
	public static void main(String[] args) {
		Checkboxes checkboxes =new Checkboxes();
		checkboxes.initialSetup();
		checkboxes.checkSelectedCheckbox();
		checkboxes.selectCheckbox1();
		checkboxes.unselectAllCheckbox();
		//checkboxes.quit();

	}
	


	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver_win32_109\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/checkboxes");
	}

 
	public void quit() {
		driver.quit();
	}

	// Check which checkbox is selected
 
	public void checkSelectedCheckbox() {

		boolean checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isSelected();
		Assert.assertEquals(checkbox1, false);

		boolean checkbox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).isSelected();
		Assert.assertEquals(checkbox2, true);
	}

	// Select checkbox1
 
	public void selectCheckbox1() {
		WebElement checkbox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
		checkbox1.click();
		boolean elementSelected = checkbox1.isSelected();
		Assert.assertEquals(elementSelected, true);
	}

	// Un-select all checkboxes
 
	public void unselectAllCheckbox() {
		List<WebElement> checkboxes = driver.findElements(By.xpath("//form[@id='checkboxes']//child::input"));

		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}

}
}
