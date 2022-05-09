package com.example.selenium.cases.project.budget;

import com.example.selenium.common.CommonTest;
import com.example.selenium.common.Context;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author zhangtao
 * @since 2022/5/6 18:34
 */
public class ProjectBudgetTest extends CommonTest {
    private final String projectCode = Context.CURRENT_TIME;

    @Test
    public void testAdd(){
        loginWait("/manage_platform/project.shtml");

        //打开纵向项目列表，并搜索
        expandMenu("纵向项目");
        jumpFromMenu("项目-纵向项目-项目列表");
        findElement(By.id("project_code_projectCode")).sendKeys(projectCode);
        asyncClick(By.id("btn_Search"));

        //点击编制预算
        findElement(By.xpath("(//a[text()='更多'])[1]")).click();
        jumpFromLinkText("编制预算");

        //填写预算
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
//        Context.driver.close();
    }
}
