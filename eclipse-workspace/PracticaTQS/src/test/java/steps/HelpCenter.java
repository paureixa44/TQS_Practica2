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

public class HelpCenter {
	
	WebDriver driver;
	
	 @Given("the user is in the index page")
	 public void theUserIsInTheIndexPageHelpCenter() {
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
	
	@When("the user clics Ayuda")
	public void theUserClicksHelp() {
		driver.findElement(By.className("responsiveSubMenu_subMenuLink")).click();
	}
	
	@Then("goes to the help center")
	public void goesToTheHelpCenter() {
		String pageSource = driver.getPageSource();
		assert pageSource.contains("CENTRO DE AYUDA");
	}
}
