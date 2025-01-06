package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProductDetails {

    WebDriver driver;
    private String text;

    @Given("the user is in the index page")
    public void theUserIsInTheIndexPageProductDetails() {
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
        // Localitzar la barra de cerca i escriure l'article
        WebElement searchBar = driver.findElement(By.id("header-search-input")); // Canvia "search-bar-id" per l'ID correcte
        searchBar.sendKeys(article);
    }

    @When("the user clicks the search button")
    public void theUserClicksTheSearchButton() {
        // Localitzar el botó de cerca i fer clic
        WebElement searchButton = driver.findElement(By.id("headerSearch_button")); // Canvia "search-button-id" per l'ID correcte
        searchButton.click();
    }

    @When("clicks one of the products")
    public void clicksOneOfTheProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Obtenir el text del producte seleccionat
        WebElement productNameElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("productBlock_productName-13103437"))
        );
        text = productNameElement.getText();

        // Fer clic en el producte
        WebElement productLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(".productBlock_imageLinkWrapper"))
        );
        productLink.click();

    }

    @Then("the info of the product appears")
    public void theInfoOfTheProductAppears() {
        // Esperar que aparegui la informació del producte
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement productInfoElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("productName_title"))
        );

        // Obtenir el text del producte visible
        String productInfo = productInfoElement.getText();

        // Comprovar que el text coincideix
        assert productInfo.equals(text) : "El text del producte no coincideix amb el seleccionat!";

        // Tancar el navegador
        driver.quit();
    }

}
