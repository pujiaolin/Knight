package com.forpast.knight.config;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.listeners.JobListenerSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * 链接的任务调度
 * Date 2017-03-13
 *
 * @author Medxi
 * @version V1.0
 */

public class JobChainedJobListener extends JobListenerSupport {


    private String name;
    private Map<JobKey, Class<? extends Job>> chainLinks;


    /**
     * Construct an instance with the jobClass.
     *
     * @param name the Listener name
     */
    public JobChainedJobListener(String name) {

        if(name == null) {
            throw new IllegalArgumentException("Listener name cannot be null!");
        }
        this.name = name;
        chainLinks = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Add a chain mapping - when the Job identified by the first key completes
     * the job identified by the second key will be triggered.
     *
     * @param firstJob a JobKey with the name and group of the first job
     * @param secondJobClass 第二个job class类
     */
    void addJobChainLink(JobKey firstJob, Class<? extends Job> secondJobClass) {
        chainLinks.put(firstJob, secondJobClass);
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

        Class<? extends Job> secondJobClass = chainLinks.get(context.getJobDetail().getKey());

        if(secondJobClass == null) {
            return;
        }

        JobDetail job = JobBuilder.newJob(secondJobClass).withIdentity(secondJobClass.getName()).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(secondJobClass.getName()).startNow().build();
        try {
            context.getScheduler().scheduleJob(job,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
