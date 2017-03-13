package com.kaishengit.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.shiro.ShiroUtil;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import  com.kaishengit.pojo.Process;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

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

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Logger logger = LoggerFactory.getLogger(ProcessController.class);
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


    @GetMapping("/myRunning/list")
    public String myRunningProcess(Model model){
        com.kaishengit.pojo.User user = ShiroUtil.getCurrentUser();
        /**
         * 1.通过当前登录的userId去查询historyProcessInstance
         * */

        List<HistoricProcessInstance> hisInstanceList = historyService.createHistoricProcessInstanceQuery()
                .startedBy(user.getId().toString()).list();
        List<Process> processList = new ArrayList<>();
        for(HistoricProcessInstance instance:hisInstanceList){
            /* 2.根据endtime==null 筛选正在运行的流程
            * */
            if(instance.getEndTime() != null ){
                continue;
            }
            Process process = new Process();
            process.setProcessInstanceId(instance.getId());
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(instance.getProcessDefinitionId()).singleResult();
            //设置流程名称
            process.setProcessDefinitionName(definition.getName());
            process.setApplyTime(format.format(instance.getStartTime()));
            process.setUserName(user.getUserName());
            //查询任务信息
            Task task = taskService.createTaskQuery().executionId(instance.getId()).singleResult();

            process.setTask(task);
            processList.add(process);
        }
        model.addAttribute("processList",processList);
        return "activiti/process/myRunning-process";
    }

    //我的发起历史流程和我参与的历史流程
    @GetMapping("/history/list")
    public String historyProcess(Model model){

        List<Process> processList = new ArrayList<>();
        List<Process> processTaskList = new ArrayList<>();


        //1.我的发起历史流程
        com.kaishengit.pojo.User user = ShiroUtil.getCurrentUser();
        List<HistoricProcessInstance> hisProcessList = historyService.createHistoricProcessInstanceQuery()
                .startedBy(user.getId().toString()).finished().list();

        for(HistoricProcessInstance hisProcess:hisProcessList){
            Process process = new Process();
            process.setHistoricProcessInstance(hisProcess);
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(hisProcess.getProcessDefinitionId()).singleResult();
            process.setProcessDefinitionName(definition.getName());
            process.setUserName(user.getUserName());
            processList.add(process);
        }

        //2.我参与的历史流程
        List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(user.getId().toString()).finished().list();

        for (HistoricTaskInstance hisTaskInstance:hisTaskList){
            Process process = new Process();
            //1.获取参与的历史流程，根据hisTaskInstance的processInstanceId;
            HistoricProcessInstance hisInstacne = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(hisTaskInstance.getProcessInstanceId()).singleResult();
            process.setHistoricProcessInstance(hisInstacne);

           //2.根据流程定义id获得流程定义的name
            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(hisInstacne.getProcessDefinitionId()).singleResult();
            process.setProcessDefinitionName(definition.getName());

            //3.根据发起者的userId获得userName
            User actUser = identityService.createUserQuery()
                    .userId(hisInstacne.getStartUserId()).singleResult();
            process.setUserName(actUser.getFirstName());
            processTaskList.add(process);

        }
        model.addAttribute("processTaskList",processTaskList);
        model.addAttribute("processList",processList);
        return "activiti/process/finished-process";
    }

    @GetMapping("/viewDetail/{processInstanceId}")
    public String instanceDetail(@PathVariable String processInstanceId, Model model ){

        Process process = new Process();
        com.kaishengit.pojo.User user = ShiroUtil.getCurrentUser();
        HistoricProcessInstance hisProcess = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        process.setHistoricProcessInstance(hisProcess);
        //2.根据流程定义id获得流程定义的name
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(hisProcess.getProcessDefinitionId()).singleResult();
        process.setProcessDefinition(definition);
        List<HistoricActivityInstance> actList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();
        actList = Lists.newArrayList(
                Collections2.filter(actList, new Predicate<HistoricActivityInstance>() {
                    @Override
                    public boolean apply(HistoricActivityInstance instance) {
                        return StringUtils.isNotEmpty(instance.getAssignee());
                    }
                })
        );
        process.setActList(actList);

        model.addAttribute("process",process);
        return "activiti/process/view-process";
    }

    @GetMapping("/resource/{depId}")
    public void getResouce(@PathVariable("depId") String depId
        , @RequestParam("resourceName") String resName, HttpServletResponse response){

        try {
            // 通过接口读取
            InputStream resourceAsStream = repositoryService.getResourceAsStream
                    (depId, resName);
            // 输出资源内容到相应对象
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        }catch (IOException e){
            logger.error("png图片读取异常！");
        }
    }


}
