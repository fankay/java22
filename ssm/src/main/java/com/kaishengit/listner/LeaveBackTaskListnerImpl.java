package com.kaishengit.listner;

import com.kaishengit.pojo.Leave;
import com.kaishengit.service.impl.LeaveManager;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by jimi_jin on 2017-3-9.
 */
@Transactional
@Component
public class LeaveBackTaskListnerImpl implements TaskListener {

    private static final long serialVersionUID = 1L;
    @Autowired
    private LeaveManager leaveManager;

    @Override
    public void notify(DelegateTask delegateTask) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        //入库真实结束开始时间
        //通过delegateTask.getExecution()获取流程实例
        String leaveId = delegateTask.getExecution().getProcessBusinessKey();
        Leave leave = new Leave();

        Object startTime = delegateTask.getVariable("realityStartTime");
        Object endTime = delegateTask.getVariable("realityEndTime");

//      Object satrtTime = runTimeService.getVariable(delegateTask.getExecutionId(),"");
//      Object endTime = runTimeService.getVariable(delegateTask.getExecutionId(),"realityEndTime");

        leave.setRealityStartTime(String.valueOf(startTime));
        leave.setRealityEndTime(String.valueOf(endTime));
        leave.setId(Long.parseLong(leaveId));
        //手动加载springBean
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        leaveManager = (LeaveManager)wac.getBean("leaveManager");
        leaveManager.update(leave);

    }
}
