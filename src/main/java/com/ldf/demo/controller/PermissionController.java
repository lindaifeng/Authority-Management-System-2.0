package com.ldf.demo.controller;

import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.service.PermissionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/14 22:27
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    //设置成OR，表示所列出的条件只要满足其中一个就可以，
// 如果不写或者设置成logical = Logical.AND，表示所有列出的都必须满足才能进入方法。
    @RequiresRoles(value = {"King","ADMIN"},logical = Logical.OR)
    @GetMapping("/findAll.do")
    public String findAll(HttpServletRequest request) {
        List<Permission> permission = permissionService.findAll();
        request.getSession().setAttribute("permissionList", permission);
        return "permission-list";
    }

    //查询未添加的角色
    @GetMapping("/findById.do")
    public String findById(@RequestParam("id") String id, HttpServletRequest request) {
        List<Permission> permission = permissionService.findById(id);

        //查询未添加的角色
        List<Role> role = permissionService.findByIdAndNotRole_Permission(id);
        for (Role role1 : role) {
            System.out.println(role1);
        }
        request.getSession().setAttribute("permissionList", permission);
        request.getSession().setAttribute("permissionId", id);

        request.getSession().setAttribute("role", role);
        return "permission-show";
    }

    //跳转到添加页面
    @GetMapping("/permission-add.do")
    public String permissionadd(){
        return "permission-add";
    }
    //添加资源权限
    @PostMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }

    //添加资源权限与角色之间的关联
    @GetMapping("/addRoleByPermissionId.do")
    public String addRoleByPermissionId(@RequestParam("permissionId") String permissionId,
                                        @RequestParam("roleId") String roleId,
                                        RedirectAttributes attributes){
        permissionService.addRoleByPermissionId(permissionId,roleId);
        attributes.addAttribute("id",permissionId);
        return "redirect:/permission/findById.do";
    }

    //删除资源权限
    @GetMapping("/deletePermissionById.do")
    public String deletePermissionById(String permissionId) {
        //删除关联表
        int p = permissionService.deletePermissionByIdAndRole_Permission(permissionId);

        permissionService.deletePermissionById(permissionId);

        return "redirect:/permission/findAll.do";
    }
    //根据角色id和资源权限id删除关联表
    @GetMapping("/deleteRoleById.do")
    public String deleteRoleById(@RequestParam("permissionId") String permissionId,
                                 @RequestParam("roleId") String roleId,
                                 RedirectAttributes attributes){
        permissionService.deleteRoleById(permissionId,roleId);
        attributes.addAttribute("id",permissionId);
        return "redirect:/permission/findById.do";
    }
}
