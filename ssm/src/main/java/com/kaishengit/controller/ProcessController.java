package com.kaishengit.controller;

import com.kaishengit.shiro.ShiroUtil;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import  com.kaishengit.pojo.Process;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by jimi_jin on 2017-3-9.
 */

@Controller
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    IdentityService identityService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

    @RequestMapping("/apply")
    public String processApply(){
        return "activiti/process/processList";
    }

    @RequestMapping("/task/list")
    public String taskList(Model model){
        String userId = ShiroUtil.getCurrentUserId().toString();
        List<Task> taskList = new ArrayList<>();
        List<Process> processes = new ArrayList<>();
        List<Task> todoList = taskService.createTaskQuery()
                .taskAssignee(userId).orderByTaskCreateTime().asc().list();

        List<Task> unSignedTask = taskService.createTaskQuery()
                .taskCandidateUser(userId).orderByTaskCreateTime().asc().list();

        taskList.addAll(todoList);
        taskList.addAll(unSignedTask);

        for(Task task : taskList){
            String proInstanceId = task.getProcessInstanceId();
            HistoricProcessInstance hisInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(proInstanceId).singleResult();
            User actUser = identityService.createUserQuery().userId(hisInstance.getStartUserId()).singleResult();
            Process process = new Process();
            process.setUserName(actUser.getFirstName());
            process.setTask(task);
            process.setHistoricProcessInstance(hisInstance);
            ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(proInstanceId).singleResult();
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(instance.getProcessDefinitionId()).singleResult();

            process.setProcessDefinitionName(definition.getName());

            processes.add(process);
        }
        model.addAttribute("processes",processes);
        return "activiti/process/task-list";
    }

    @RequestMapping("/claim/task/{taskId}")
    public String cliam(@PathVariable String taskId, RedirectAttributes attributes){
        String userId = ShiroUtil.getCurrentUserId().toString();
        try{
            taskService.claim(taskId,userId);
            attributes.addFlashAttribute("message","签收任务成功");
        }catch(ActivitiException e){
            attributes.addFlashAttribute("message","签收任务失败");
        }
        return "redirect:/process/task/list";
    }
}
