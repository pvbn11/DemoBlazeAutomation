package org.paolovalladaresbazalar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Autor: Paolo Neil Valladares Bazalar
 * Repositorio: https://github.com/pvbn11/ProjectDemoBlaze_Selenium
 * Descripción: Suite de automatización e2e Demo Blaze
 */

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));    }

    protected String visibleElement (By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected void sendKeysElement (By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);

    }

    protected void clickElement (By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void handleAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}
