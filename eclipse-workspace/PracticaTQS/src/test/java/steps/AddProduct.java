package steps;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class AddProduct{
	
	WebDriver driver;
	
    @Given("the user is in the index page")
    public void theUserIsInTheIndexPageAddProduct() {
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

    @When("the user searches for {string}")
    public void theUserSearchesFor(String product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.id("header-search-input")));
        searchBar.sendKeys(product);
        
        WebElement searchButton = driver.findElement(By.className("headerSearch_button"));
        searchButton.click();
    }
    
    @When("the user selects a product from the results")
    public void theUserSelectsAProductFromTheResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement productLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(".productBlock_imageLinkWrapper")));
        productLink.click();
    }

    @When("the user clicks the Comprar button")
    public void theUserClicksTheAddToCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Aumentamos el tiempo de espera a 10 segundos
        
        // Aseguramos que el selector esté correcto y el elemento sea visible
        WebElement addToCartButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("athenaProductPage_productAddToBasket"))
        );
        
        // Desplazamos el botón a la vista
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top);", addToCartButton);

        System.out.println("Scroll realizado hacia el botón.");
        // Hacemos clic en el botón
        addToCartButton.click();
    }

    @Then("the product is added to the cart")
    public void theProductIsAddedToTheCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("responsiveFlyoutBasket_basketButton")));
        cartButton.click();
        
        WebElement cartItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".athenaBasket_basketItemRow athenaBasket_basketItemRow_productDetails")));
        Assert.assertNotNull(cartItem, "The product was not added to the cart.");
        System.out.println("Product successfully added to the cart.");
    }
}
