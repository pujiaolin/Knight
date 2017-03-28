package com.forpast.knight.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * .
 * Date 2017-03-24
 *
 * @author Medxi
 * @version V1.0
 */
public class NewJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("this is for add a new job.....");
    }
}
