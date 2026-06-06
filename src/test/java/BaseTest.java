import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Autor: Paolo Neil Valladares Bazalar
 * Repositorio: https://github.com/pvbn11/ProjectDemoBlaze_Selenium
 * Descripción: Suite de automatización e2e Demo Blaze
 */

public class BaseTest implements TestWatcher {

    protected static WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.switchTo().alert().dismiss();
        } catch (Exception e) {
        }
        driver.get("https://demoblaze.com/index.html");
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (driver != null) {
            try {
                takeScreenshot(testInfo.getDisplayName());
            } catch (Exception e) {
                System.err.println("No se pudo capturar: " + e.getMessage());
            }

            try {
                driver.switchTo().alert().dismiss();
            } catch (Exception e) {}

            driver.quit();
        }
    }

    protected void takeScreenshot(String testName) {
        try {
            new File("screenshots").mkdirs();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String timestamp = LocalDateTime.now().format(dtf);
            String fileName = "screenshots/" + testName + "_" + timestamp + ".png";

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(fileName));
            System.out.println("Captura guardada en: " + fileName);
        } catch (Exception e) {
            System.err.println("No se pudo tomar la captura: " + e.getMessage());
        }
    }
}
