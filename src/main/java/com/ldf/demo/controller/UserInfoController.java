package com.ldf.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.pojo.UserInfo;
import com.ldf.demo.service.UserInfoService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:04
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    //设置成OR，表示所列出的条件只要满足其中一个就可以，
// 如果不写或者设置成logical = Logical.AND，表示所有列出的都必须满足才能进入方法。
    @RequiresRoles(value = {"King","ADMIN"},logical = Logical.OR)
    @RequestMapping("/findAll.do")
    public String findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
                          @RequestParam(name = "fuzzyName", defaultValue = "", required = false) String fuzzyName,
                          HttpServletRequest request) {
        PageHelper.startPage(page, pageSize);
        List<UserInfo> userInfos = userInfoService.findAll(fuzzyName);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        request.getSession().setAttribute("userList", pageInfo);
        return "user-list";
    }

    @GetMapping("/findById.do")
    public String findById(@RequestParam(name = "id", defaultValue = "") String id,
                           @ModelAttribute(name = "userId", value = "") String userId,
                           HttpServletRequest request) {
//        //@ModelAttribute RedirectAttributes重定向中获取绑定值
        UserInfo userInfo = null;
        List<Role> role = null;

        if (userId != null && userId.length() > 0) {
            //判断是否是从定向过来的(做了增删操作，重定向后id丢失，因此借助传过来的userId进行查询)
             userInfo = userInfoService.findById(userId);
             role = userInfoService.findByIdNotRole(userId);
        } else {
            //访问从id过来(没有做增删操作)
             userInfo = userInfoService.findById(id);
             role = userInfoService.findByIdNotRole(id);
        }

        request.getSession().setAttribute("user", userInfo);
        request.getSession().setAttribute("role", role);
        return "user-show";
    }

    //用户关联此角色
    @GetMapping("/relatedRole.do")
    public String relatedRole(@RequestParam("roleId") String roleId,
                              @RequestParam("userId") String userId,
                              RedirectAttributes attributes) {
        userInfoService.relatedRole(roleId, userId);
        //重定向到根据userId查询角色信息页面附带userId过去
        // addFlashAttribute隐藏参数 addAttribute不隐藏参数
        attributes.addFlashAttribute("userId", userId);
        return "redirect:/user/findById.do";
    }


    @GetMapping("/deleteRoleById.do")
    public String deleteRoleById(@RequestParam("roleId") String roleId,
                           @RequestParam("userId") String userId,
                           RedirectAttributes attributes) {
        userInfoService.deleteByIdRoleAndUser(roleId, userId);
        //重定向到根据userId查询角色信息页面附带userId过去
        // addFlashAttribute隐藏参数 addAttribute不隐藏参数
        attributes.addFlashAttribute("userId", userId);
        return "redirect:/user/findById.do";
    }

}
