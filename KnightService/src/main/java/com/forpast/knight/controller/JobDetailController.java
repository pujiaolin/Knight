package com.forpast.knight.controller;

import com.forpast.knight.pagemodel.JobDetailPm;
import com.forpast.knight.pagemodel.ExecuteJobPm;
import com.forpast.knight.service.SchedulerService;
import org.quartz.JobKey;
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
import java.util.Map;

/**
 * 调度任务
 * Date 2017-03-17
 *
 * @author Medxi
 * @version V1.0
 */
@RestController
@RequestMapping("/job")
public class JobDetailController {

    private final SchedulerService schedulerService;

    @Autowired
    public JobDetailController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("all-job")
    public ResponseEntity<Map<String, List<JobDetailPm>>> getAllJob() throws SchedulerException {
        return ResponseEntity.ok(schedulerService.findAllJob());
    }

    @PostMapping("add-execute-job-trigger")
    public ResponseEntity<Object> addExecuteJobTrigger(@RequestBody ExecuteJobPm executeJobPm) throws SchedulerException {
        JobKey jobKey = new JobKey(executeJobPm.getClassName(),executeJobPm.getJobGroup());
        if(!schedulerService.checkExistJobKey(jobKey)){
            return ResponseEntity.ok(schedulerService.addExecuteJobTrigger(executeJobPm));
        }else {
            return ResponseEntity.badRequest().body("This job is existed!");
        }
    }

    @DeleteMapping("delete-job/{jobClassName:.+}/{jobGroup}")
    public ResponseEntity<Object> deleteJob(@PathVariable String jobClassName,@PathVariable String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobClassName,jobGroup);
        if(schedulerService.checkExistJobKey(jobKey)){
            return ResponseEntity.ok(schedulerService.deleteJob(jobKey));
        }else {
            return ResponseEntity.badRequest().body("This job is not existed!");
        }

    }
}
