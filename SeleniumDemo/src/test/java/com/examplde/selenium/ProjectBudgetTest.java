package com.examplde.selenium;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.examplde.selenium.Context.*;
import static java.lang.Thread.sleep;

/**
 * @author zhangtao
 * @since 2022/5/6 18:34
 */
public class ProjectBudgetTest extends CommonTest{
    private final String projectCode = "20220509173535";
    private final String budgetFrameName = "iframebudget";
    @Test
    public void testAssignFund(){
        loginWait("/manage_platform/project.shtml");

        //进入经费分配列表
        findElementWait(By.xpath("//span[text()='经费分配']")).click();
        String fundListFrameName = clickMenu("//a[@title='项目-经费分配-经费分配列表']");

        //打开导入弹框，上传文件，然后导入
        findElementById("btn_Import").click();
        findElementById("instSelFile").sendKeys("D:/Tmp/到款导入模板.xlsm");
        findElement(By.cssSelector("#instBatchImportDialog #btn_Import")).click();
        waitDisappear(By.xpath("//h5[text()='经费分配明细']"));

        //进入经费明细
        switchToFrame(fundListFrameName);
        WebElement fundDeteilEle = findElement(By.xpath("//tr/td[last()]/a[text()='经费分配']"));
        String fundPoolId = fundDeteilEle.getAttribute("flag");
        fundDeteilEle.sendKeys(Keys.ENTER);
        switchToFrame("iframe"+fundPoolId);

        //分配经费
        findElementById("addProject").click();
        findElementByIdWait("project_code_projectCode").sendKeys(projectCode);
        findElementById("btn_projectSearch").click();
        waitDisappear(By.cssSelector(".layui-layer-loading2"));
        findElementWait(By.xpath("//td[text()='"+projectCode+"']/../td[last()]/a[text()='选择']")).click();
        findElement(By.xpath("//input[@name='value']")).sendKeys("100");
        WebElement actualMoneyEle=findElement(By.xpath("//input[@name='actualMoney']"));
        WebElement managementFeeEle=findElement(By.xpath("//input[@name='managementFee']"));
        new WebDriverWait(driver, Duration.ofSeconds(50),Duration.ofSeconds(1)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver text) {
                return !"0".equals(actualMoneyEle.getAttribute("value")) || !"0".equals(managementFeeEle.getAttribute("value"));
            }
        });
        findElementByIdWait("btn_Save").click();
    }
    @Test
    public void testAdd(){
        loginWait("/manage_platform/project.shtml");

        //打开纵向项目列表，并搜索
        findElementWait(By.xpath("//span[text()='纵向项目']")).click();
        findElementWait(By.xpath("//a[@title='项目-纵向项目-项目列表']")).click();
        switchToFrame("iframe4");
        findElement(By.id("project_code_projectCode")).sendKeys(projectCode);
        findElement(By.id("btn_Search")).click();
        waitDisappear(By.cssSelector(".layui-layer-loading2"));

        //点击编制预算
        findElement(By.xpath("(//a[text()='更多'])[1]")).click();
        findElement(By.xpath("(//a[text()='编制预算'])[1]")).click();

        //填写预算
        driver.switchTo().frame(budgetFrameName);

        WebElement btnChangeSum = findElement(By.id("btn-change-summary-budget"));
        if(btnChangeSum != null){
            btnChangeSum.click();
        }

        WebElement withdraw = findElement(By.className("withdraw"));
        if(withdraw!=null){
            withdraw.click();
            driver.findElement(By.className("layui-layer-btn0")).click();
        }
        List<WebElement> trs = driver.findElement(By.id("summary-budget-form")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for(WebElement tr : trs){
            for(WebElement td : tr.findElements(By.xpath("//td[@valType='1']"))){
                WebElement input = td.findElement(By.tagName("input"));
                input.sendKeys(Keys.CONTROL, "a");
                input.sendKeys(Keys.CONTROL,Keys.DELETE);
                input.sendKeys("1");
            }
        }
        driver.findElement(By.id("btn-save-summary-budget")).click();
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public static void afterClass(){
        Context.driver.close();
    }
}
