package com.ldf.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldf.demo.pojo.Syslog;
import com.ldf.demo.service.SysLogService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/15 11:28
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;
    //设置成OR，表示所列出的条件只要满足其中一个就可以，
// 如果不写或者设置成logical = Logical.AND，表示所有列出的都必须满足才能进入方法。
    @RequiresRoles(value = "King",logical = Logical.OR)
    @RequestMapping("/findAll.do")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "8") Integer size,
                          @RequestParam(name = "fuzzyName",defaultValue = "",required = false) String fuzzyName,
                          HttpServletRequest request){
        PageHelper.startPage(page,size);
        List<Syslog> sysLogs = sysLogService.findAll(fuzzyName);
        PageInfo<Syslog> pageInfo = new PageInfo<>(sysLogs);
        request.getSession().setAttribute("sysLogs",pageInfo);
        return "syslog-list";
    }
    @RequestMapping("/deleteSysLog.do")
    public String deleteSysLog(){
        sysLogService.deleteSysLog();
        return "redirect:/sysLog/findAll.do";
    }

}
