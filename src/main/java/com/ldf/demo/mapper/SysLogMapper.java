package com.ldf.demo.mapper;

import com.ldf.demo.pojo.Syslog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/15 11:29
 * @code: 愿世间永无Bug!
 * @description:
 */
@Mapper
@Repository
public interface SysLogMapper {
    List<Syslog> findAll(String fuzzyName);

    void deleteSysLog();

    void addSysLog(Syslog sysLog);
}
