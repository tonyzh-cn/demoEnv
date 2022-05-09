package com.examplde.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;

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
