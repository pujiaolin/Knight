package com.forpast.knight.config;

import com.forpast.knight.quartz.Job1;
import com.forpast.knight.quartz.Job2;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.text.ParseException;

/**
 * Scheduler配置类
 * Date 2017-03-13
 *
 * @author PJL
 * @version V1.0
 */
@Configuration
public class SchedulerConfig {

    private final JobExceptionListener jobExceptionListener;
    private final SchedulerManager schedulerManager;

    @Autowired
    public SchedulerConfig(SchedulerManager schedulerManager, JobExceptionListener jobExceptionListener) {
        this.schedulerManager = schedulerManager;
        this.jobExceptionListener = jobExceptionListener;
    }

    private Trigger[] assembleTriggers() throws ParseException {
        SimpleTrigger job1Trigger = this.schedulerManager.makeSimpleTriggerDefault(Job1.class, "我的第一个任务", 5000L);
        SimpleTrigger job2Trigger = this.schedulerManager.makeSimpleTriggerDefault(Job2.class, "second", 5000L);

        return new Trigger[]{job1Trigger,job2Trigger};
    }

    private JobListenerSupport[] assembleJobListeners() {
        //JobChainedJobListener jobChainingJobListener = new JobChainedJobListener("job1Listener");
        //jobChainingJobListener.addJobChainLink(schedulerManager.getJobKeyDefualt(Job1.class), Job2.class);
        return new JobListenerSupport[]{jobExceptionListener};
    }

    @Bean
    public SchedulerFactoryBean schedulerFactory(@Qualifier("dataSource") DataSource dataSource) throws ParseException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setTriggers(assembleTriggers());
        schedulerFactoryBean.setGlobalJobListeners(assembleJobListeners());
        return schedulerFactoryBean;
    }
}
