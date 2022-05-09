package com.examplde.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author zhangtao
 * @since 2022/5/8 13:34
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ProjectColumnTest.class,ProjectBudgetTest.class})
public class ProjectBudgetTestSuite extends CommonSuite{
}
