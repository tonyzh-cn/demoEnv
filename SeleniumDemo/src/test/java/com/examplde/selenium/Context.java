package com.examplde.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * @author zhangtao
 * @since 2022/5/8 12:02
 */
public class Context {
    public static WebDriver driver;
    public static String CURRENT_TIME;
    public static String CONTEXT_PATH = "http://124.193.98.151:8161//hljzyydx-h";

    static {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "chromedriver.exe");
        driver = new ChromeDriver();
        CURRENT_TIME = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static WebElement findElement(By by) {
        try {
            return driver.findElement(by);
        } catch (Exception ex) {
            return null;
        }
    }

    public static WebElement findElementWait(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement findElementById(String id) {
        try {
            return driver.findElement(By.id(id));
        } catch (Exception ex) {
            return null;
        }
    }

    public static WebElement findElementByIdWait(String id) {
        return findElementWait(By.id(id));
    }

    public static Select findSelectByIdByIdWait(String id) {
        try {
            return new Select(findElementByIdWait(id));
        } catch (Exception ex) {
            return null;
        }
    }

    public static Select findSelectById(String id) {
        try {
            return new Select(findElementById(id));
        } catch (Exception ex) {
            return null;
        }
    }
}
