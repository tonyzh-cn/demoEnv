package com.example.selenium.common;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author zhangtao
 * @since 2022/5/8 12:11
 */
public class CommonTest {
    protected static WebDriver driver = Context.driver;

    @BeforeClass
    public static void beforeClass(){
        driver.manage().window().maximize();
    }

    protected void defaultContent(){
        driver.switchTo().defaultContent();
    }

    protected void getUrl(String url){
        driver.get(Context.CONTEXT_PATH+url);
    }

    protected void getUrlWait(String url){
        new WebDriverWait(driver, Duration.ofSeconds(50),Duration.ofMillis(1)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver text) {
                getUrl(url);
                return driver.getCurrentUrl().contains(url);
            }
        });
    }

    protected void loginWait(String url){
        new WebDriverWait(driver, Duration.ofSeconds(50),Duration.ofMillis(1)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver text) {
                login(url);
                return driver.getCurrentUrl().contains(url);
            }
        });
    }

    protected void login(String url){
        getUrl(url);
        WebElement username = findElement(By.id("username"));
        WebElement psw = findElement(By.id("password"));
        if(username !=null && psw !=null){
            username.sendKeys("admin");
            psw.sendKeys("pms123");
            findElement(By.xpath("//input[@type='submit']")).click();
        }
    }

    protected void waitDisappear(By by){
        new WebDriverWait(driver, Duration.ofSeconds(50),Duration.ofSeconds(1)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver text) {
                WebElement tmp = findElement(by);
                return (tmp == null || !tmp.isDisplayed());
            }
        });
    }

    protected void switchToFrame(String nameOrId){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(nameOrId);
    }

    protected void expandMenu(String text){
        findElementWait(By.xpath("//span[text()='"+text+"']")).click();
    }

    protected String jumpFromMenu(String text){
        return jump2Frame(By.xpath("//a[@title='"+text+"']"),"data-index");
    }

    protected String jumpFromLinkText(String text){
        return jump2Frame(By.xpath("//a[text()='"+text+"']"),"flag");
    }

    protected String jumpFromId(String id){
        return jump2Frame(By.id(id),"flag");
    }

    protected String jump2Frame(By by,String flag){
        WebElement menuEle=findElementWait(by);
        menuEle.click();
        String frameName = "iframe"+menuEle.getAttribute(flag);
        switchToFrame(frameName);
        return frameName;
    }
    protected void clickById(String id){
        ((JavascriptExecutor) driver).executeScript("document.getElementById('"+id+"').click();");
    }

    protected void clickByLinkText(String text){
        findElementWait(By.xpath("//a[text()='"+text+"']")).click();
    }

    protected void asyncClick(By by){
        findElement(by).click();
        waitDisappear(By.cssSelector(".layui-layer-loading2"));
    }

    protected WebElement findElement(By by) {
        try {
            return driver.findElement(by);
        } catch (Exception ex) {
            return null;
        }
    }

    protected WebElement findElementWait(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(by));
    }

    protected WebElement findElementById(String id) {
        try {
            return driver.findElement(By.id(id));
        } catch (Exception ex) {
            return null;
        }
    }

    protected WebElement findElementByIdWait(String id) {
        return findElementWait(By.id(id));
    }

    protected Select findSelectByIdByIdWait(String id) {
        try {
            return new Select(findElementByIdWait(id));
        } catch (Exception ex) {
            return null;
        }
    }

    protected Select findSelectById(String id) {
        try {
            return new Select(findElementById(id));
        } catch (Exception ex) {
            return null;
        }
    }
}
