package com.kaishengit.exclusive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ExclusiveTest {

	ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	/*
	 * 第一步：部署流程
	 * **/
	
	@Test
	public void deployment(){
		Deployment deploy = engine.getRepositoryService()//获取repositoryservice
			.createDeployment().name("HelloWord")
			.addClasspathResource("diagrams/ExclusiveMoney.bpmn")
			.addClasspathResource("diagrams/ExclusiveMoney.png")//部署流程资源文件BPMN/png
			.deploy();
		System.out.println("name: "+ deploy.getName());
		System.out.println("id: "+ deploy.getId());
	}
	
	/*
	 * 第二步：启动流程
	 * **/
	
	@Test
	public void startProcess(){
		Map<String,Object> maps = new HashMap<>();
		maps.put("money", 600);
		ProcessInstance pi = engine.getRuntimeService()
				.startProcessInstanceByKey("money",maps);
		
		System.out.println("id: " +pi.getId());
		System.out.println("name: " +pi.getName());
		System.out.println("key: " + pi.getProcessDefinitionKey() );
		
		
	}
	
	
	/*
	 * 第三步：查看任务列表
	 * **/
	
	@Test
	public void getTaskList(){
		String assignee = "李四";
				
			List<Task> taskList = engine.getTaskService().createTaskQuery()//创建查询对象
					//设置查询条件
					.taskAssignee(assignee)
//					.processDefinitionId(processDefinitionId)
					.orderByTaskAssignee()
					.desc()
					.list();
			System.out.println("list size:" + taskList.size());
			for(Task task:taskList){
				System.out.println("办理人:" + task.getAssignee());
				System.out.println("name:" + task.getName());
				System.out.println("流程定义ID:" + task.getProcessDefinitionId());
			}
	}
	
	
	/*
	 * 第四步：办理任务
	 * **/
	
	@Test
	public void completeTask(){
		String taskId = "2504";
		engine.getTaskService().complete(taskId);
		System.out.println("完成成功！");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
