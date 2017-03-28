package com.forpast.knight.controller;

import com.forpast.knight.service.JobListenerService;
import com.forpast.knight.pagemodel.JobListenerPm;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务监听
 * Date 2017-03-23
 *
 * @author Medxi
 * @version V1.0
 */
@RestController
@RequestMapping("/job-listener")
public class JobListenerController {

    private final JobListenerService jobListenerService;

    @Autowired
    public JobListenerController(JobListenerService jobListenerService) {
        this.jobListenerService = jobListenerService;
    }

    @GetMapping("all-job-listener")
    public ResponseEntity<List<String>> getAllJobListener() throws SchedulerException {
        return ResponseEntity.ok(jobListenerService.getAllListener());
    }

    @PostMapping("add-job-listener")
    public ResponseEntity<String> addJobListener(@RequestBody JobListenerPm jobListenerPm) throws SchedulerException {
        JobListenerPm fullJobListenerPm = jobListenerPm.makeJobListenerPm();
        if(jobListenerService.getJobListener(fullJobListenerPm.getListenerName()) == null){
            jobListenerService.addListener(fullJobListenerPm);
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.badRequest().body("This JobListener is existed!");
        }
    }

    @DeleteMapping("delete-job-listener/{listenerName}")
    public ResponseEntity<String> deleteJobListener(@PathVariable String listenerName) throws SchedulerException {
        if(jobListenerService.getJobListener(listenerName) != null){
            jobListenerService.removeJobListener(listenerName);
            return ResponseEntity.ok("Success");
        }else{
            return ResponseEntity.badRequest().body("This JobListener is not existed!");
        }
    }
}
