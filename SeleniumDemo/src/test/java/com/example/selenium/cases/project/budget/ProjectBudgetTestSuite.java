package com.example.selenium.cases.project.budget;

import com.example.selenium.cases.ProjectColumnTest;
import com.example.selenium.cases.project.budget.ProjectBudgetTest;
import com.example.selenium.cases.project.fund.ProjectFundTest;
import com.example.selenium.common.CommonSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author zhangtao
 * @since 2022/5/8 13:34
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ProjectColumnTest.class, ProjectFundTest.class, ProjectBudgetTest.class})
public class ProjectBudgetTestSuite extends CommonSuite {
}
