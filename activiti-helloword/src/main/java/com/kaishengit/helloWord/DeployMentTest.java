package com.kaishengit.helloWord;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class DeployMentTest {

	ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	public void deployZip(){
		
		//获取zip文件的输入流
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("diagrams/HelloWord.zip");
		ZipInputStream zipInput = new ZipInputStream(input);
		
		Deployment deploy = engine.getRepositoryService().createDeployment().name("zipDeploy")
		.addZipInputStream(zipInput).deploy();
		
		System.out.println("流程id：" + deploy.getId());
		System.out.println("流程name" + deploy.getName());
		
	
	}
	
	
	/**
	 * 获取流程定义
	 * 
	 * */
	@Test
	public void getProcessDef(){
		
		
		List<ProcessDefinition> processDefList =  engine.getRepositoryService().createProcessDefinitionQuery()//创建查询query对象
		//封装查询条件
		.processDefinitionKey("myProcess").list();
		
		System.out.println("lsit size :" + processDefList.size());
		
		for(ProcessDefinition def:processDefList){
			
			System.out.println("def id:" + def.getId());
			System.out.println("def name:" + def.getName());
			System.out.println("def version:" + def.getVersion());
		}
		
		
	}
	
	/**
	 * 删除流程定义
	 * 
	 * */
	@Test
	public void delProcessDef(){
		engine.getRepositoryService().deleteDeployment("12501");
		System.out.println("success!");
	
	}
	
	
	/**
	 * 获取流程定义PNG
	 * @throws IOException 
	 * 
	 * */
	@Test
	public void getProcessDefPng() throws IOException{
		String defId = "myProcess:2:2504";
		ProcessDefinition def= engine.getRepositoryService().createProcessDefinitionQuery()
		.processDefinitionId(defId).singleResult();
		String deployId = def.getDeploymentId();
		String resourceName = def.getDiagramResourceName();
		
		System.out.println("id: " + deployId +" name:" +resourceName);
		InputStream input = engine.getRepositoryService().getResourceAsStream(deployId, resourceName);
		
		FileUtils.copyInputStreamToFile(input, new File("D:/HelloWord.png"));
		
	}
}
