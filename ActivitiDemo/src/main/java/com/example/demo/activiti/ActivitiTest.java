package com.example.demo.activiti;

import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitiTest {
    private static ProcessEngine processEngine;
    private static RuntimeService runtimeService;
    private static TaskService taskService;
    private static RepositoryService repositoryService;
    private static FormService formService;

    static {
        processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        repositoryService = processEngine.getRepositoryService();
        formService = processEngine.getFormService();
    }

    private static void startProcessInstance(String procdefId) {
        processEngine.getRuntimeService()
                .startProcessInstanceById(procdefId); //这个是查看数据库中act_re_procdef表
    }

    public static void main(String[] args) {
        VacationRequest();

//        myProcess();
    }

    private static void myProcess() {
        repositoryService.createDeployment()
                .addClasspathResource("MyProcess.bpmn")
                .deploy();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");

        Task userTask1 = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).active().singleResult();

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("result", "Y");
        taskService.complete(userTask1.getId(), vars);

        Task userTask2 = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).active().singleResult();

    }

    private static void VacationRequest() {
        repositoryService.createDeployment()
                .addClasspathResource("VacationRequest.bpmn20.xml")
                .deploy();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);

        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();

        Task task = tasks.get(0);

        List<FormProperty> formProperties = formService.getTaskFormData(task.getId()).getFormProperties();
        for (FormProperty formProperty : formProperties) {
            System.out.println(formProperty.getId() + ":" + formProperty.getName() + ":" + formProperty.getType().getName() + ":" + formProperty.getValue());
        }
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);

        Task task2 = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).active().singleResult();

    }

}
