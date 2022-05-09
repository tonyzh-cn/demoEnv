package com.examplde.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.examplde.selenium.Context.findElement;

public class CommonSuite {
    @BeforeClass
    public static void beforeClass(){
        Context.driver.manage().window().maximize();
    }

    @AfterClass
    public static void afterClass(){
        Context.driver.close();
    }
}
