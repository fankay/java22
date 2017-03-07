package com.kaishengit.variable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class VariablesTest {
	ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void startProcess(){
		Map<String,Object> variables = new HashMap<>();
		variables.put("days", 5);
		variables.put("startTime", new Date());
		ProcessInstance pi = engine.getRuntimeService().startProcessInstanceByKey("myProcess",variables);
		
		System.out.println("id: " +pi.getId());
		System.out.println("name: " +pi.getName());
		System.out.println("key: " + pi.getProcessDefinitionKey() );
		
		
	}
	
	@Test
	public void setRuntimeVariables(){
		Map<String,Object> variables = new HashMap<>();
		variables.put("days1", 5);
		variables.put("startTime1", new Date());
		engine.getRuntimeService().setVariables("17501",variables);
		
		
	}
	
	@Test
	public void setTaskVariables(){
		Map<String,Object> variables = new HashMap<>();
		variables.put("days2", 5);
		variables.put("startTime2", new Date());
		engine.getTaskService().setVariables("17506",variables);
		
		
	}
	
	
	@Test
	public void getTaskVariables(){
		/*Map<String,Object> variables = new HashMap<>();
		variables.put("days2", 5);
		variables.put("startTime2", new Date());
		engine.getTaskService().setVariables("17506",variables);*/
		Integer days = (Integer)engine.getRuntimeService().getVariable("17501", "days2");
		System.out.println("days:" + days );
		Integer days2 = (Integer)engine.getTaskService().getVariable("17506", "days2");
		System.out.println("days:" + days2 );
	}
	
	
	/*
	 * 第三步：查看任务列表
	 * **/
	
	@Test
	public void getTaskList(){
		String assignee = "李四";
			
		Integer days = (Integer)engine.getRuntimeService().getVariable("17501", "days");
		Map<String,Object> maps  =  engine.getRuntimeService().getVariables("17501");
		List<String> variableNames = new ArrayList<>();
		variableNames.add("days");
		
		Map<String,Object> variables  =  engine.getRuntimeService().getVariables("17501", variableNames);
		System.out.println("variables:" + variables.size());
		System.out.println("maps:" + maps.size());
		
		System.out.println("days " + days);
		
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
	
}
