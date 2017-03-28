package com.forpast.knight.service;

import com.forpast.knight.pagemodel.JobDetailPm;
import com.forpast.knight.pagemodel.ExecuteJobPm;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 任务调度服务
 * Date 2017-03-16
 *
 * @author PJL
 * @version V1.0
 */
@Service
public class SchedulerService {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 删除Job
     * @param jobKey JobKey
     * @throws SchedulerException 异常
     */
    public boolean deleteJob(JobKey jobKey) throws SchedulerException {
        return scheduler.deleteJob(jobKey);
    }

    /**
     * 获取所有 JobDetail
     * @return 所有分组的JobDetail
     * @throws SchedulerException 异常
     */
    public Map<String, List<JobDetailPm>> findAllJob() throws SchedulerException {
        return scheduler.getJobGroupNames().stream().collect(Collectors.toMap(group -> group,
                group -> getJobDetailList(getJobKeySet(group))));
    }

    /**
     * 获取jobKeySet
     * @param group 分组名
     * @return 所有jobKey
     */
    private Set<JobKey> getJobKeySet(String group){
        try {
            return scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取job列表
     * @param jobKeySet 所有jobKey
     * @return job列表
     */
    private List<JobDetailPm> getJobDetailList(Set<JobKey> jobKeySet) {
        return jobKeySet.stream().map(this::getJobDetail).collect(Collectors.toList());
    }

    /**
     * 获取job
     * @param jobKey jobKey
     * @return JobDetail
     */
    private JobDetailPm getJobDetail(JobKey jobKey){
        try {
            return JobDetailPm.makeJobDetailPm(scheduler.getJobDetail(jobKey));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加执行任务，cronTrigger
     * @param executeJobPm 执行的任务信息
     * @return 日期
     * @throws SchedulerException 异常
     */
    public Date addExecuteJobTrigger(ExecuteJobPm executeJobPm) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(executeJobPm.getJobClass())
                .withDescription(executeJobPm.getDescription())
                .withIdentity(executeJobPm.getClassName(),executeJobPm.getJobGroup()).build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withDescription(executeJobPm.getDescription())
                .withIdentity(executeJobPm.getClassName(),executeJobPm.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(executeJobPm.getCronExpression())).build();
       return scheduler.scheduleJob(job,trigger);

    }

    /**
     * 检查job是否存在
     * @param jobKey jobKey
     * @return 返回boolean
     * @throws SchedulerException 异常
     */
    public boolean checkExistJobKey(JobKey jobKey) throws SchedulerException {
        return scheduler.checkExists(jobKey);
    }
}
