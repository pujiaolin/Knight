package com.forpast.knight.config;

import com.forpast.knight.service.ExceptionJobService;
import com.forpast.knight.util.Constant;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * .
 * Date 2017-04-07
 *
 * @author Medxi
 * @version V1.0
 */
@Component
public class JobExceptionListener extends JobListenerSupport {

    @Autowired
    private ExceptionJobService exceptionJobService;

    private String name;

    public JobExceptionListener(String name){
        this.name = name;
    }

    public JobExceptionListener(){
        this(Constant.EXCEPTION_LISTENER);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        if (jobException != null) {
            exceptionJobService.saveExceptionJob(context.getJobDetail().getKey().toString(), jobException.getMessage());
        }
    }
}