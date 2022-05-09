package com.example.selenium.cases;

import com.example.selenium.common.CommonTest;
import com.example.selenium.common.Context;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import static com.example.selenium.common.Context.*;
import static java.lang.Thread.sleep;

/**
 * @author zhangtao
 * @since 2022/5/8 12:00
 */
public class ProjectColumnTest extends CommonTest {
//    private String projectCode = "20220508221851";
    private String projectCode = CURRENT_TIME;
    private String projectName = "自动化测试"+projectCode;
    @Test
    public void testAdd(){
        loginWait("/manage_platform/project.shtml");

        //进入项目列表
        expandMenu("纵向项目");
        jumpFromMenu("项目-纵向项目-项目列表");

        //进入添加页面
        jumpFromId("projectColumnAdd");

        //填写项目基本信息
        new Select(findElement(By.id("250_project_type_subjectType_zh"))).selectByValue("001");
        findElement(By.id("242_project_name_projectName_zh")).sendKeys(projectName);
        findElement(By.id("243_project_code_projectCode_zh")).sendKeys(projectCode);
        findSelectByIdByIdWait("392_project_Project_Level_zh").selectByValue("1");
        findSelectByIdByIdWait("252_project_Project_Source_zh").selectByValue("7");
        findSelectByIdByIdWait("254_project_Project_Type_zh").selectByValue("132");
        findElement(By.id("393_project_Project_UndertakingUnit_zh")).sendKeys(Context.CURRENT_TIME);
        findSelectById("288_project_UnitRanking_UnitRanking_zh").selectByValue("1");
        findSelectById("290_project_ProjectStatus_ProjectStatus_zh").selectByValue("001");
        findElementById("291_project_SetupDate_SetupDate_zh").sendKeys("2022-01-01");
        findElementById("455_project_project_Year_zh").sendKeys("2023");
        findElementById("PlanDate").sendKeys("2023-01-01");
        ((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]",findElementById("PlanEndDate"), "2023-01-01");
        findElement(By.xpath("//a[@href='#next']")).click();

        //填写项目成员信息
        findElementByIdWait("btn_addCharge").click();
        findSelectById("type").selectByValue("001");
        findElementById("name").sendKeys("系统管理员");
        findElementWait(By.xpath("//strong[text()='系统管理员']")).click();
        findElementById("btn_addSure").click();
        findElement(By.xpath("//a[@href='#next']")).click();

        //填写项目经费
        findElementByIdWait("265_project_projectFunder_ProjectFunder_zh").sendKeys("1");
        findElementWait(By.xpath("//a[@href='#next']")).sendKeys(Keys.ENTER);

        //保存
        findElement(By.xpath("//a[@href='#next']")).sendKeys(Keys.ENTER);
        findElement(By.xpath("//a[@href='#finish']")).sendKeys(Keys.ENTER);
        waitDisappear(By.xpath("//h5[text()='项目添加向导']"));
    }

    @Test
    public void testAudit(){
        loginWait("/manage_platform/project.shtml");
        //进入审核列表
        expandMenu("纵向项目");
        String checkFrameName = jumpFromMenu("项目-纵向项目-立项审核");

        //检索项目
        findElement(By.xpath("//input[@name='project_code']")).sendKeys(projectCode);
       asyncClick(By.id("btn_Search"));

        //进入审核页面并审核通过
        WebElement check = findElementWait(By.xpath("//td[text()='"+projectCode+"']/../td[last()]/a[text()='审核']"));
        while(check != null){
            System.out.println("准备进入审核页面");
            String projectFlag =check.getAttribute("flag");
            check.click();
            switchToFrame("iframe"+projectFlag);
            findElementById("projectCheckPass").click();
            clickById("submitCheck");
            waitDisappear(By.xpath("//h3[text()='"+projectName+"']"));
            switchToFrame(checkFrameName);
            WebElement projectCodeEle = findElement(By.xpath("//input[@name='project_code']"));
            projectCodeEle.sendKeys(Keys.CONTROL, "a");
            projectCodeEle.sendKeys(Keys.CONTROL,Keys.DELETE);
            projectCodeEle.sendKeys(projectCode);
            findElementById("btn_Search").click();
            check = findElement(By.xpath("//td[text()='"+projectCode+"']/../td[last()]/a[text()='审核']"));
        }
    }

    @AfterClass
    public static void afterClass(){
//        Context.driver.close();
    }
}
