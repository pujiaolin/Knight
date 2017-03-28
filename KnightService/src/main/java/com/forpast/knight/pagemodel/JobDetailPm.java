package com.forpast.knight.pagemodel;

import org.quartz.JobDetail;

/**
 * jobDetail的页面对象
 * Date 2017-03-22
 *
 * @author Medxi
 * @version V1.0
 */
public class JobDetailPm {

    public String jobKey;

    public String jobGroup;

    public String jobClassName;

    public String description;

    public boolean isDurable;

    public boolean isConcurrentExectionDisallowed;

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDurable() {
        return isDurable;
    }

    public void setDurable(boolean durable) {
        isDurable = durable;
    }

    public boolean isConcurrentExectionDisallowed() {
        return isConcurrentExectionDisallowed;
    }

    public void setConcurrentExectionDisallowed(boolean concurrentExectionDisallowed) {
        isConcurrentExectionDisallowed = concurrentExectionDisallowed;
    }

    public static JobDetailPm makeJobDetailPm(JobDetail jobDetail){
        JobDetailPm jobDetailPm = new JobDetailPm();
        jobDetailPm.setConcurrentExectionDisallowed(jobDetail.isConcurrentExectionDisallowed());
        jobDetailPm.setDescription(jobDetail.getDescription());
        jobDetailPm.setDurable(jobDetail.isDurable());
        jobDetailPm.setJobClassName(jobDetail.getJobClass().getName());
        jobDetailPm.setJobKey(jobDetail.getKey().getName());
        jobDetailPm.setJobGroup(jobDetail.getKey().getGroup());
        return jobDetailPm;
    }
}
