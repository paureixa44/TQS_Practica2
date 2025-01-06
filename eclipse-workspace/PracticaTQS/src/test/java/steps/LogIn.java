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

public class LogIn {
	
	WebDriver driver;
	
	 @Given("the user is in the index page")
	 public void theUserIsInTheIndexPageLogIn() {
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
	
	@When("the user clics the Cuenta button")
	public void theUserClicksTheAccountButton() {
		driver.findElement(By.className("responsiveAccountHeader_openAccountButton")).click();
	}
	
	@When("the user fills the Log In camps")
	public void theUserFillsTheLogInCamps() {
		driver.findElement(By.id("dirección-de-correo-electrónico-input-element-id-4ce0e262-f5de-4fc1-8019-f28af3b12ef5")).sendKeys("reixaman@gmail.com");
		driver.findElement(By.id("contraseña-input-element-id-c3194de5-4b2d-4755-b6e2-21057dada273")).sendKeys("12345678");
	}
	
	@When("the user clics Log In")
	public void theUserClicksLogIn() {
		driver.findElement(By.className("sc-crXcEl dAZVwN")).click();
	}
	
	@Then("goes to the my account page")
	public void goesToMyAccount(String article) {
		String account = driver.findElement(By.id("b5342d30-3f0a-42a2-8558-119c826ef4db")).getText();
		assert account.equals("Mi cuenta");
	}
}
