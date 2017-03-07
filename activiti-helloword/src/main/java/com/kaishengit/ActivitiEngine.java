package com.kaishengit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class ActivitiEngine {
	@Test
	public void initTables(){
		
		
		ProcessEngineConfiguration peg = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		//根据set方法修改连接数据库JDBC;
		peg.setJdbcDriver("com.mysql.jdbc.Driver");
		peg.setJdbcUrl("jdbc:mysql:///db_22_activiti_demo");
		peg.setJdbcUsername("root");
		peg.setJdbcPassword("rootroot");
		
		/** Checks the version of the DB schema against the library when 
		   * the process engine is being created and throws an exception
		   * if the versions don't match. */
		 // public static final String DB_SCHEMA_UPDATE_FALSE = "false";
		  
		  /** Creates the schema when the process engine is being created and 
		   * drops the schema when the process engine is being closed. */
		 // public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";

		  /** Upon building of the process engine, a check is performed and 
		   * an update of the schema is performed if it is necessary. */
		 // public static final String DB_SCHEMA_UPDATE_TRUE = "true";
		
		peg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		ProcessEngine engine = peg.buildProcessEngine();
		
		System.out.println("engine "+engine.getName());
		
		
	}
	
	
	@Test
	public void initTable2(){
		
		
		ProcessEngine engine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

		System.out.println("engine "+engine);
		
	}
	
	
	@Test
	public void getEngine(){
		
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		System.out.println("engine "+engine);
	}
}
