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

public class CreateAccount {
	
	WebDriver driver;
	
	 @Given("the user is in the index page")
	 public void theUserIsInTheIndexPageCreateAccount() {
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
	
	@When("the user clicks the Registro button")
	public void theUserClicksTheRegisterButton() {
		driver.findElement(By.className("sc-crXcEl dAZVwN sc-cMOycp fDuXki sc-gcFSfr jRPSOD")).click();
	}
	
	@When("the user fills the required camps")
	public void theUserFillsTheCamps() {
		driver.findElement(By.id("nombre-completo-input-element-id-199a5631-0657-4cc5-b2c2-411a3ed95921")).sendKeys("aaaa");
		driver.findElement(By.id("dirección-de-correo-electrónico-input-element-id-71490d51-2234-41f0-9f04-1ca30eb1a855")).sendKeys("reixaman@gmail.com");
		driver.findElement(By.id("confirmar-dirección-de-correo-electrónico-input-element-id-ec2a2845-5d5e-4561-8d7c-b3c3a6433177")).sendKeys("reixaman@gmail.com");
		driver.findElement(By.id("contraseña-input-element-id-bf5a8331-37a4-415f-8b89-205175296ff6")).sendKeys("123456789");
		driver.findElement(By.id("confirmar-contraseña-input-element-id-c4df1908-bfae-4472-90e1-5d5932316c90")).sendKeys("123456789");
		driver.findElement(By.className("sc-dmWEhm eIHwxt")).click();
	}
	
	@When("the user clicks Continuar")
	public void theUserclicksContinuar() {
		driver.findElement(By.className("sc-iqcoie clYpGs")).click();
	}
	
	
	@Then("goes to the my account page")
	public void goesToMyAccount(String article) {
		String account = driver.findElement(By.id("b5342d30-3f0a-42a2-8558-119c826ef4db")).getText();
		assert account.equals("Mi cuenta");
	}
}
