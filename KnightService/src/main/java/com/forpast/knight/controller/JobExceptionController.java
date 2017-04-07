package com.forpast.knight.controller;

import com.forpast.knight.entity.ExceptionJob;
import com.forpast.knight.service.ExceptionJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常的任务
 * Date 2017-03-31
 *
 * @author Medxi
 * @version V1.0
 */
@RestController
@RequestMapping("/job-exception")
public class JobExceptionController {

    @Autowired
    private ExceptionJobService exceptionJobService;

    /**
     * 查询所有异常任务的信息
     * @param pageable 分页信息
     * @return 任务的异常信息列表
     */
    @GetMapping("find-all")
    public ResponseEntity<Page<ExceptionJob>> findAll(@PageableDefault(sort = "occurredTime", direction = Sort.Direction.DESC)
                                                              Pageable pageable){
        return ResponseEntity.ok(exceptionJobService.findAll(pageable));
    }

    /**
     * 删除异常纪录
     * @param id id
     * @return 删除成功消息
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        exceptionJobService.deleteExceptionJob(id);
        return ResponseEntity.ok("Success.");
    }
}
