package org.vaadin.artur.shadydomselenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumIT {

    @Test
    public void accessLightDomElement() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        try {
            driver.get("http://localhost:8080/");
            WebElement grid = driver.findElement(By.id("grid"));

            // Wait for polyfills to be loaded and grid to be initialized
            new WebDriverWait(driver, 10).until(d -> {
                Boolean gridLoaded = (Boolean) js.executeScript(
                        "return typeof arguments[0]._getColumns != 'undefined'",
                        grid);
                return gridLoaded;
            });

            // Get a reference to the `<vaadin-grid-column>` element only
            // available in light DOM
            WebElement column = (WebElement) js.executeScript(
                    "return arguments[0]._getColumns()[0];", grid);

            // Try to interact with the element
            System.out.println("Element: " + column);
            WebElement parent = column.findElement(By.xpath(".."));
            System.out.println("Element parent: " + parent);
            System.out.println(
                    "Parent outerHTML: " + parent.getAttribute("outerHTML"));
            System.out.println(
                    "Element outerHTML: " + column.getAttribute("outerHTML"));
        } finally {
            driver.quit();
        }
    }

}
