package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Demo1 {
    public static void main(String[] args) {
        String driverPath = System.getProperty("user.dir")+ File.separator+"chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",driverPath);
        System.out.println(driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
        WebElement kw = driver.findElement(By.id("kw"));
        kw.sendKeys("图灵");
        WebElement su = driver.findElement(By.id("su"));
        su.click();
    }
}
