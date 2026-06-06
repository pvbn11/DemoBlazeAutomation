package org.paolovalladaresbazalar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    // Asegúrate de que este método exista AQUÍ
    public ProductDetailsPage addProductToCart() {
        // Implementación: click al botón y manejar alert si es necesario
        clickElement(By.linkText("Add to cart"));
        handleAlert();
        // Aquí deberías añadir la lógica para aceptar el alert de JS si lo requiere Demoblaze
        return this; // Retorna la misma página
    }

    public HomePage goBackHome() {
        By homeLink = By.cssSelector("a.nav-link[href='index.html']");
        wait.until(ExpectedConditions.elementToBeClickable(homeLink)).click();
        return new HomePage(driver);
    }

    public CartPage goToCart() {
        clickElement(By.id("cartur"));
        return new CartPage(driver);
    }
}
