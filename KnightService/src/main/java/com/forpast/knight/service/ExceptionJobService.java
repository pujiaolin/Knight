package com.forpast.knight.service;
import com.forpast.knight.entity.ExceptionJob;
import com.forpast.knight.repository.ExceptionJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * .
 * Date 2017-03-31
 *
 * @author Medxi
 * @version V1.0
 */
@Service
public class ExceptionJobService {

    private final ExceptionJobRepository exceptionJobRepository;

    @Autowired
    public ExceptionJobService(ExceptionJobRepository exceptionJobRepository) {
        this.exceptionJobRepository = exceptionJobRepository;
    }

    /**
     * 保存异常任务纪录
     * @param jobKey 任务的key
     * @param exceptionContent 异常内容
     */
    public void saveExceptionJob(String jobKey,String exceptionContent){
        ExceptionJob exceptionJob = new ExceptionJob();
        exceptionJob.setJobKey(jobKey);
        exceptionJob.setExceptionContent(exceptionContent);
        exceptionJobRepository.save(exceptionJob);
    }

    /**
     * 查询所有异常任务信息
     * @param pageable 分页对象
     * @return 任务的异常信息列表
     */
    public Page<ExceptionJob> findAll(Pageable pageable){
        return exceptionJobRepository.findAll(pageable);
    }

    /**
     * 删除异常纪录
     * @param id id
     */
    public void deleteExceptionJob(Integer id){
        exceptionJobRepository.delete(id);
    }
}
