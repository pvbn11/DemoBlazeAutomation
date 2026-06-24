package org.paolovalladaresbazalar;

/**
 * Autor: Paolo Neil Valladares Bazalar
 * Repositorio: https://github.com/pvbn11/DemoBlazeAutomation
 * Descripción: Suite de automatización e2e Demo Blaze
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage addProductToCart() {
        clickElement(By.linkText("Add to cart"));
        handleAlert();
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
