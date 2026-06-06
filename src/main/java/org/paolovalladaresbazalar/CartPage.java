package org.paolovalladaresbazalar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {
    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");
    private By nameField = By.id("name");
    private By purchaseBtn = By.xpath("//button[text()='Purchase']");
    private By countryField = By.id("country");
    private By cityField = By.id("city");
    private By cardField = By.id("card");
    private By monthField = By.id("month");
    private By yearField = By.id("year");
    private String lastSubmittedName;
    private By confirmationTitle = By.xpath("//h2[text()='Thank you for your purchase!']");
    private By purchaseDetails = By.xpath("//p[contains(@class, 'lead')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage fillForm(String name, String country, String city, String card, String month, String year) {
        this.lastSubmittedName = name;
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(cardField).sendKeys(card);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);
        return this;
    }

    public void finish() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationTitle)).getText();
    }

    public String getPurchaseDetailsText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseDetails)).getText();
    }

    public String getLastSubmittedName() {

        return this.lastSubmittedName;
    }

    public int getTotalPriceFromTable() {
        // Espera explícita a que las filas de la tabla carguen
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody[@id='tbodyid']/tr")));

        int total = 0;
        // Iteramos sobre las filas para obtener la celda del precio (la tercera columna, td[3])
        for (WebElement row : rows) {
            String priceText = row.findElement(By.xpath("./td[3]")).getText();
            total += Integer.parseInt(priceText);
        }
        return total;
    }

    public int getDisplayedTotal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));
        wait.until(d -> !totalElement.getText().trim().equals("0") && !totalElement.getText().trim().isEmpty());
        return Integer.parseInt(totalElement.getText().trim());
    }

    public void placeOrder() {

        driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]")).click();
    }

}