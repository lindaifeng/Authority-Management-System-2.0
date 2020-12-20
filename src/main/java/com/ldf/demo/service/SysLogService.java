package com.ldf.demo.service;

import com.ldf.demo.pojo.Syslog;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/15 11:29
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface SysLogService {
    List<Syslog> findAll(String fuzzyName);

    void deleteSysLog();

    void addSysLog(Syslog sysLog);
}
