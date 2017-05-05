package org.vaadin.artur.shadydomselenium;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumIT {

    @Test
    public void printFoo() {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.get("http://localhost:8080/");
            System.out.println(((JavascriptExecutor) driver)
                    .executeScript("return 'foo';"));
        } finally {
            driver.quit();
        }
    }

}
