package com.example.selenium.common;

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
public final class Context {
    public static WebDriver driver;
    public static String CURRENT_TIME;
    public static String CONTEXT_PATH = "http://192.168.50.254:8080/hljzyydx-h";

    static {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "chromedriver.exe");
        driver = new ChromeDriver();
        CURRENT_TIME = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
