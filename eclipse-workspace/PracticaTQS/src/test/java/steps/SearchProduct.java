package steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchProduct {
	
	WebDriver driver;
	
	 @Given("the user is in the index page")
	 public void theUserIsInTheIndexPageforSearchProduct() {
		 System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
	        driver = new FirefoxDriver();
	        driver.navigate().to("https://myprotein.es/");
	        
	        // Accept cookies if the popup appears
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            WebElement acceptCookiesButton = wait.until(
	                ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler"))
	            );
	            acceptCookiesButton.click();
	            System.out.println("Cookies accepted.");
	        } catch (Exception e) {
	            System.out.println("Cookies popup not found.");
	        }
	     // Cerrar ventana emergente de email si aparece
	     		try {
	     			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     			WebElement emailCloseButton = wait.until(
	     				ExpectedConditions.elementToBeClickable(By.className("emailReengagement_close_button"))
	     			);
	     			emailCloseButton.click();
	     			System.out.println("Ventana emergente de email cerrada");
	     		} catch (Exception e) {
	     			System.out.println("No se encontró la ventana emergente de email o ya fue cerrada.");
	     		}
	    }
	
	@When("the user enters {string} in the search bar")
	public void theUserEntersInTheSearchBar(String article) {
		driver.findElement(By.id("header-search-input")).sendKeys(article);
	}
	
	@When("the user clicks the search button")
	public void theUserClicksTheSearchButton() {
		driver.findElement(By.className("headerSearch_button")).click();
	}
	
	@Then("the {string} list appears")
	public void theListAppears(String article) {
		String title = driver.findElement(By.id("responsive-product-list-title")).getText();
		Assert.assertTrue(title.contains(article), "El título no contiene el artículo esperado.");
	}
}
