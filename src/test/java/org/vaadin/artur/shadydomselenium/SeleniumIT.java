package org.vaadin.artur.shadydomselenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumIT {

    @Test
    public void accessLightDomElement() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("http://localhost:8080/");

            // Wait for polyfills to be loaded and grid to be initialized
            Thread.sleep(1000);

            // Get a reference to the `<vaadin-grid-column>` element only
            // available in light DOM
            WebElement grid = driver.findElement(By.id("grid"));
            WebElement column = (WebElement) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0]._getColumns()[0];",
                            grid);

            // Try to interact with the element
            System.out.println("Element: " + column);
            System.out.println(
                    "Element.outerHTML: " + column.getAttribute("outerHTML"));
        } finally {
            driver.quit();
        }
    }

}
