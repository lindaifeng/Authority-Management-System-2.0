package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.SysLogMapper;
import com.ldf.demo.pojo.Syslog;
import com.ldf.demo.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/15 11:29
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public List<Syslog> findAll(String fuzzyName) {
        return sysLogMapper.findAll(fuzzyName);
    }

    @Override
    public void deleteSysLog() {
        sysLogMapper.deleteSysLog();
    }

    @Override
    public void addSysLog(Syslog sysLog) {
        sysLogMapper.addSysLog(sysLog);
    }
}
