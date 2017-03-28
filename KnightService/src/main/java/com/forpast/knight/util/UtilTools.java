package com.forpast.knight.util;

import org.quartz.Job;

/**
 * 类操作工具
 * Date 2017-03-22
 *
 * @author Medxi
 * @version V1.0
 */
public class UtilTools {

    /**
     * 获取类
     * @param className 根据类名获取类
     * @return
     */
    public static Class<? extends Job> getJobClass(String className){
        try {
           return (Class<? extends Job>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
