package com.forpast.knight.pagemodel;

import com.forpast.knight.util.UtilTools;
import org.quartz.Job;

/**
 * JobListener页面对象
 * Date 2017-03-22
 *
 * @author Medxi
 * @version V1.0
 */
public class JobListenerPm {

    private String listenerName;

    private String masterJobClassName;

    private String minorJobClassName;

    private Class<? extends Job> masterJobClass;

    private Class<? extends Job> minorJobClass;

    private String masterJobGroup;

    public String getListenerName() {
        return listenerName;
    }

    public void setListenerName(String listenerName) {
        this.listenerName = listenerName;
    }

    public String getMasterJobClassName() {
        return masterJobClassName;
    }

    public void setMasterJobClassName(String masterJobClassName) {
        this.masterJobClassName = masterJobClassName;
    }

    public String getMinorJobClassName() {
        return minorJobClassName;
    }

    public void setMinorJobClassName(String minorJobClassName) {
        this.minorJobClassName = minorJobClassName;
    }

    public Class<? extends Job> getMasterJobClass() {
        return masterJobClass;
    }

    public void setMasterJobClass(Class<? extends Job> masterJobClass) {
        this.masterJobClass = masterJobClass;
    }

    public Class<? extends Job> getMinorJobClass() {
        return minorJobClass;
    }

    public void setMinorJobClass(Class<? extends Job> minorJobClass) {
        this.minorJobClass = minorJobClass;
    }

    public String getMasterJobGroup() {
        return masterJobGroup;
    }

    public void setMasterJobGroup(String masterJobGroup) {
        this.masterJobGroup = masterJobGroup;
    }

    /**
     * @return 整合的Job监听对象
     */
    public JobListenerPm makeJobListenerPm(){
        JobListenerPm jobListenerPm = new JobListenerPm();
        jobListenerPm.setMasterJobClass(UtilTools.getJobClass(this.masterJobClassName));
        jobListenerPm.setMinorJobClass(UtilTools.getJobClass(this.minorJobClassName));
        jobListenerPm.setMasterJobGroup(this.masterJobGroup);
        jobListenerPm.setListenerName(getTheJobListenerName(jobListenerPm));
        return jobListenerPm;
    }

    /**
     * @param jobListenerPm JobListenerPm对象
     * @return JobListenerPm的名字
     */
    private String getTheJobListenerName(JobListenerPm jobListenerPm){
        String simpleMasterJobClassName = jobListenerPm.getMasterJobClass().getSimpleName();
        String simpleMinorJobClassName = jobListenerPm.getMinorJobClass().getSimpleName();
        return simpleMasterJobClassName + "_Then_" + simpleMinorJobClassName;
    }

}
