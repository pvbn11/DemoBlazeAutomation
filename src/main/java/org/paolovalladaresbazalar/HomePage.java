package org.paolovalladaresbazalar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Autor: Paolo Neil Valladares Bazalar
 * Repositorio: https://github.com/pvbn11/DemoBlazeAutomation
 * Descripción: Suite de automatización e2e Demo Blaze
 */

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage selectProduct(String productName) {
        String xpath = String.format("//a[text()='%s']", productName);
        clickElement(By.xpath(xpath));
        return new ProductDetailsPage(driver);
    }

    public void goToCart() {
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", cartLink);
    }
}
