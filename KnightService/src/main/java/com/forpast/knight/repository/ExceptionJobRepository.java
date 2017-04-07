package com.forpast.knight.repository;


import com.forpast.knight.entity.ExceptionJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * .
 * Date 2017-03-31
 *
 * @author Medxi
 * @version V1.0
 */
@Repository
public interface ExceptionJobRepository extends JpaRepository<ExceptionJob,Integer> {
}
