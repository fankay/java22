package com.kaishengit.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskListnerImpl implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		
		delegateTask.setAssignee("张三");
		
		
	}

}
