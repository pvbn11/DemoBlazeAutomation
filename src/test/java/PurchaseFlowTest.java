import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.paolovalladaresbazalar.CartPage;
import org.paolovalladaresbazalar.HomePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Autor: Paolo Neil Valladares Bazalar
 * Repositorio: https://github.com/pvbn11/ProjectDemoBlaze_Selenium
 * Descripción: Suite de automatización e2e Demo Blaze
 */

@ExtendWith(BaseTest.class)
public class PurchaseFlowTest extends BaseTest {

    @Test
    public void testCompletePurchase() {

        String name = "Paolo";
        String country = "Peru";
        String city = "Lima";
        String card = "123456789";
        String month = "06";
        String year = "2026";
        CartPage cartPage = new CartPage(driver);
        new HomePage(driver)
                .selectProduct("Samsung galaxy s6")
                .addProductToCart()
                .goBackHome()
                .selectProduct("Nokia lumia 1520")
                .addProductToCart()
                .goToCart();

        int calculatedTotal = cartPage.getTotalPriceFromTable();
        int displayedTotal = cartPage.getDisplayedTotal();

        cartPage.fillForm(name, country, city, card, month, year)
                .finish();

        assertEquals(1180, displayedTotal, "El total visualizado no coincide con 1180.");
        assertEquals(calculatedTotal, displayedTotal, "La suma de productos no coincide con el total mostrado.");

        String expectedMessage = "Thank you for your purchase!";
        assertEquals(expectedMessage, cartPage.getConfirmationMessage(), "El mensaje de confirmación no es correcto.");

        String details = cartPage.getPurchaseDetailsText();
        assertTrue(details.contains("Name: " + cartPage.getLastSubmittedName()), "El nombre en la confirmación no coincide con el ingresado.");

        assertTrue(details.contains("Card Number: " + card), "La tarjeta en la confirmación no coincide.");
    }

    @Test
    public void testPurchaseWithEmptyFields() {
        new HomePage(driver).goToCart();
        CartPage cart = new CartPage(driver);
        cart.placeOrder();
        cart.finish();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        String actualAlertText = alert.getText();
        assertEquals("Please fill out Name and Creditcard.", actualAlertText, "El mensaje no coincide.");
        alert.accept();
        takeScreenshot("Error_Formulario_Vacio");
    }

    @Test
    public void testPurchaseWithInvalidData() {
        String invalidName = "Paolo@123!"; // Alfanumérico con caracteres especiales
        String invalidCard = "ABC$$D-EFGH-IJKL"; // Letras en lugar de números

        new HomePage(driver).goToCart();
        CartPage cart = new CartPage(driver);

        cart.fillForm(invalidName, "", "", invalidCard, "", "")
                .finish();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            takeScreenshot("Bug_Formulario_Invalido_Aceptado");
            org.junit.jupiter.api.Assertions.fail("El sistema permitió culminar el Flujo sin haber seleccionado productos y habiendo ingresado datos inválidos: Nombre="
                    + invalidName + ", Tarjeta=" + invalidCard);
        }
    }
}
