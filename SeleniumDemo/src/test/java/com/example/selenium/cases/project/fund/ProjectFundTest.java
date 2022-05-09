package com.example.selenium.cases.project.fund;

import com.example.selenium.common.CommonTest;
import com.example.selenium.common.Context;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProjectFundTest extends CommonTest {
//    private final String projectCode = Context.CURRENT_TIME;
    private final String projectCode = "20220509210719";
    private String projectName = "自动化测试"+projectCode;

//    @Test
    public void testAssignFund(){
        loginWait("/manage_platform/project.shtml");

        //进入经费分配列表
        findElementWait(By.xpath("//span[text()='经费分配']")).click();
        String fundListFrameName = jumpFromMenu("项目-经费分配-经费分配列表");

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

    /**
     * 测试添加到账
     */
    @Test
    public void testAdd(){
        loginWait("/manage_platform/mySpace.shtml");

        //进入我的项目列表
        expandMenu("纵向项目");
        jumpFromMenu("我的空间-纵向项目-我的项目");

        //搜索项目
        findElement(By.id("project_code_projectCode")).sendKeys(projectCode);
        asyncClick(By.id("btn_Search"));

        //点击经费管理
        clickByLinkText("更多");
        jumpFromLinkText("经费管理");
        //进入添加经费页面
        jumpFromId("btn_add");
        findElement(By.xpath("//input[@name='fundType' and @value='001']")).click();
        findElementById("value").sendKeys("100");
        waitDisappear(By.cssSelector(".layui-layer-loading2"));
        findElementById("save_funding").click();
        waitDisappear(By.cssSelector(".layui-layer-loading2"));
    }

    @Test
    public void testAudit(){
        loginWait("/manage_platform/project.shtml");
        expandMenu("纵向经费");
        String checkListFrameName = jumpFromMenu("项目-纵向经费-经费审核");
        findElement(By.xpath("//input[@name='projectFunfsCode']")).sendKeys(projectCode);
        asyncClick(By.id("btn_Search"));

        //进入审核页面并审核通过
        WebElement check = findElementWait(By.xpath("//td[text()='"+projectCode+"']/../td[last()]/a[text()='审核']"));
        while(check != null){
            System.out.println("准备进入审核页面");
            String projectFlag =check.getAttribute("flag");
            check.click();
            switchToFrame("iframe"+projectFlag);
            waitDisappear(By.cssSelector(".layui-layer-loading2"));
            findElementById("fundApplyCheckPass").click();
            clickById("submitCheck");
            waitDisappear(By.xpath("//h3[text()='"+projectName+"']"));
            switchToFrame(checkListFrameName);
            WebElement projectCodeEle = findElement(By.xpath("//input[@name='projectFunfsCode']"));
            projectCodeEle.sendKeys(Keys.CONTROL, "a");
            projectCodeEle.sendKeys(Keys.CONTROL,Keys.DELETE);
            projectCodeEle.sendKeys(projectCode);
            asyncClick(By.id("btn_Search"));
            check = findElement(By.xpath("//td[text()='"+projectCode+"']/../td[last()]/a[text()='审核']"));
        }
    }
}
