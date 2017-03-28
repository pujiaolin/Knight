package com.forpast.knight.pagemodel;

import com.forpast.knight.util.UtilTools;
import org.quartz.Job;

/**
 * 执行的job任务（添加时使用）
 * Date 2017-03-23
 *
 * @author Medxi
 * @version V1.0
 */
public class ExecuteJobPm {

    private String className;

    private String jobGroup;

    private Class<? extends Job> jobClass;

    private String cronExpression;

    private String description;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass == null ? UtilTools.getJobClass(this.className) : jobClass;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
