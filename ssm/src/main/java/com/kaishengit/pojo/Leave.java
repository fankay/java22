package com.kaishengit.pojo;

import lombok.Data;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.Serializable;
import java.util.Map;

@Data
public class Leave extends Process implements Serializable {

    private static final long serialVersionUID = 1L;
    private String startTime;
    private String endTime;
    private String realityStartTime;
    private String realityEndTime;
    private String leaveType;
    private String reason;



}