package herokuapp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddRemoveElements {

	WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		AddRemoveElements addRemoveElements =new AddRemoveElements();
		addRemoveElements.initialSetup();

		addRemoveElements.checkAddButtonAvailability();
		addRemoveElements.clickAddButton();

		addRemoveElements.clickAddButtonUsingMouseAction();
	
		addRemoveElements.clickAddButtonUsingKeyboardAction();
	
		addRemoveElements.isDeleteButtonDisplayed();
		addRemoveElements.countNumberOfDeleteButton();
		addRemoveElements.deleteElement();
		Thread.sleep(5000);
		addRemoveElements.quit();
	
	}

	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ADMIN\\Downloads\\chromedriver_win32_109\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
	}

	public void quit() {
		driver.quit();
	}

	public void checkAddButtonAvailability() {
		boolean addButtonDisplayed = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).isDisplayed();
		
		boolean addButtonEnabled = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).isEnabled();
		Assert.assertEquals((addButtonDisplayed && addButtonEnabled), true);
	}

	// Click on the "Add Element" button

	public void clickAddButton() {
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
	}

	// Click "Add element" button using mouse action

	public void clickAddButtonUsingMouseAction() {
		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(addButton).click().build().perform();
		
		
	}

	// Click "Add element" button using keyboard keys

	public void clickAddButtonUsingKeyboardAction() {
		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));

		addButton.sendKeys(Keys.TAB); // moving focus to the "Add element" button
		addButton.sendKeys(Keys.ENTER);
	}

	// To check availability of "Delete" button

	public void isDeleteButtonDisplayed() {
		boolean deleteButton = driver.findElement(By.xpath("//button[@class='added-manually']")).isDisplayed();
		Assert.assertEquals(deleteButton, true);
	}

	// To count the number of "Delete" button available

	public void countNumberOfDeleteButton() {
		clickAddButton(); // Calling addElement method, so that delete button count will be increased by
							// 1. Total will be 4.
		List<WebElement> listOfDeleteButton = driver.findElements(By.xpath("//button[@class='added-manually']"));
		Assert.assertEquals(listOfDeleteButton.size(), 4);
	}

	// Method to delete the button "Delete"

	public void deleteElement() {
		List<WebElement> listOfDeleteButton = driver.findElements(By.xpath("//button[@class='added-manually']"));

		// Iterating through the list of buttons and deleting it one-by-one
		for (WebElement button : listOfDeleteButton) {
			button.click();
		}
}

}