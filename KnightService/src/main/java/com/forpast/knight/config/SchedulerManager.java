package com.forpast.knight.config;

import com.forpast.knight.pagemodel.JobListenerPm;
import com.forpast.knight.util.Constant;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * 定时任务工具类
 * Date 2017-02-23
 *
 * @author PJL
 * @version V1.0
 */
@Component
public class SchedulerManager {

    /**
     * JobDetail工厂对象生成JobDetail， 默认分组
     *
     * @param jobClass    job类
     * @param description 任务描述
     * @return JobDetail
     */
    private JobDetail makeJobDetailDefult(Class<? extends Job> jobClass, String description) {
        return makeJobDetail(jobClass, description, Constant.DEFAULT_GROUP);
    }


    /**
     * JobDetail工厂对象生成JobDetail
     *
     * @param jobClass    job类
     * @param description 任务描述
     * @param groupName   分组名
     * @return JobDetail
     */
    private JobDetail makeJobDetail(Class<? extends Job> jobClass, String description, String groupName) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(jobClass);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(jobClass.getName());
        jobDetailFactoryBean.setGroup(groupName);
        jobDetailFactoryBean.setDescription(description);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }

    /**
     * SimpleTrigger的生成器
     *
     * @param jobClass       定时任务类
     * @param description    任务描述
     * @param repeatInterval 间隔时间
     * @param groupName      分组名
     * @return SimpleTrigger
     */
    public SimpleTrigger makeSimpleTrigger(Class<? extends Job> jobClass, String description, long repeatInterval, String groupName) {
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(makeJobDetail(jobClass, description, groupName));
        simpleTriggerFactoryBean.setRepeatInterval(repeatInterval);
        simpleTriggerFactoryBean.setStartDelay(1000L);
        simpleTriggerFactoryBean.setName(jobClass.getName());
        simpleTriggerFactoryBean.setGroup(groupName);
        simpleTriggerFactoryBean.afterPropertiesSet();
        return simpleTriggerFactoryBean.getObject();
    }

    /**
     * SimpleTrigger的生成器 , 默认分组
     *
     * @param jobClass       定时任务类
     * @param description    任务描述
     * @param repeatInterval 间隔时间
     * @return SimpleTrigger
     */
    public SimpleTrigger makeSimpleTriggerDefault(Class<? extends Job> jobClass, String description, long repeatInterval) {
        return makeSimpleTrigger(jobClass, description, repeatInterval, Constant.DEFAULT_GROUP);
    }

    /**
     * CronTrigger生成器
     *
     * @param jobClass       定时任务类
     * @param description    任务描述
     * @param cronExpression cron表达式（执行时间）
     * @param groupName      分组名
     * @return CronTrigger
     */
    public CronTrigger makeCronTrigger(Class<? extends Job> jobClass, String description, String cronExpression, String groupName) throws ParseException {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(makeJobDetail(jobClass, description, groupName));
        cronTriggerFactoryBean.setCronExpression(cronExpression);
        cronTriggerFactoryBean.setName(jobClass.getName());
        cronTriggerFactoryBean.setGroup(groupName);
        cronTriggerFactoryBean.afterPropertiesSet();
        return cronTriggerFactoryBean.getObject();
    }

    /**
     * CronTrigger生成器 , 默认分组
     *
     * @param jobClass       定时任务类
     * @param description    任务描述
     * @param cronExpression cron表达式（执行时间）
     * @return CronTrigger
     */
    public CronTrigger makeCronTriggerDefualt(Class<? extends Job> jobClass, String description, String cronExpression) throws ParseException {
        return makeCronTrigger(jobClass, description, cronExpression, Constant.DEFAULT_GROUP);
    }

    /**
     * 获取job的jobKey,默认分组
     *
     * @param jobClass 定时任务类
     * @return jobKey
     */
    public JobKey getJobKeyDefualt(Class<? extends Job> jobClass) {
        return getJobKey(jobClass, Constant.DEFAULT_GROUP);
    }

    /**
     * 获取job的jobKey
     *
     * @param jobClass  定时任务类
     * @param groupName 分组名
     * @return jobKey
     */
    public JobKey getJobKey(Class<? extends Job> jobClass, String groupName) {
        return new JobKey(jobClass.getName(), groupName);
    }

    /**
     * @param jobListenerPm jobListenerPm 对象
     * @return 关联任务的监听类
     */
    public JobChainedJobListener makeChainedJobListener(JobListenerPm jobListenerPm) {
        JobChainedJobListener jobChainingJobListener = new JobChainedJobListener(jobListenerPm.getListenerName());
        jobChainingJobListener.addJobChainLink(getJobKey(jobListenerPm.getMasterJobClass(), jobListenerPm.getMasterJobGroup()), jobListenerPm.getMinorJobClass());
        return jobChainingJobListener;
    }
}
