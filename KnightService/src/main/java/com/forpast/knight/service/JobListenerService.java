package com.forpast.knight.service;

import com.forpast.knight.config.SchedulerManager;
import com.forpast.knight.pagemodel.JobListenerPm;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JobListenerService
 * Date 2017-03-22
 *
 * @author Medxi
 * @version V1.0
 */
@Service
public class JobListenerService {

    private final Scheduler scheduler;

    private final SchedulerManager schedulerManager;

    @Autowired
    public JobListenerService(Scheduler scheduler, SchedulerManager schedulerManager) {
        this.scheduler = scheduler;
        this.schedulerManager = schedulerManager;
    }

    /**
     * 添加job监听
     * @param jobListenerPm job监听对象
     * @throws SchedulerException 异常
     */
    public void addListener(JobListenerPm jobListenerPm) throws SchedulerException {
        scheduler.getListenerManager().addJobListener(schedulerManager.makeChainedJobListener(jobListenerPm));
    }

    /**
     *
     * @return 返回所有监听事件
     * @throws SchedulerException 异常
     */
    public List<String> getAllListener() throws SchedulerException {
        return scheduler.getListenerManager().getJobListeners().stream().map(JobListener::getName).collect(Collectors.toList());
    }

    /**
     * 删除监听
     * @param listenerName 监听名称
     * @throws SchedulerException 异常
     */
    public void removeJobListener(String listenerName) throws SchedulerException {
        scheduler.getListenerManager().removeJobListener(listenerName);
    }

    /**
     * 获取监听对象
     * @param listenerName 监听名
     * @return 监听对象
     * @throws SchedulerException 异常
     */
    public JobListener getJobListener(String listenerName) throws SchedulerException {
        return scheduler.getListenerManager().getJobListener(listenerName);
    }
}
