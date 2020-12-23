package com.ldf.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.service.RoleService;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:35
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //设置成OR，表示所列出的条件只要满足其中一个就可以，
// 如果不写或者设置成logical = Logical.AND，表示所有列出的都必须满足才能进入方法。
    @RequiresRoles(value = {"King","ADMIN"},logical = Logical.OR)
    @GetMapping("/findAll.do")
    public String findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
                          HttpServletRequest request) {
        PageHelper.startPage(page, pageSize);
        List<Role> roles = roleService.findAll();
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        request.getSession().setAttribute("roleList", pageInfo);
        return "role-list";
    }

    @GetMapping("/findById.do")
    public String findById(@RequestParam("id") String id,
                           HttpServletRequest request) {
        List<Role> role = roleService.findById(id);
        PageInfo<Role> pageInfo = new PageInfo<>(role);
        request.getSession().setAttribute("role", pageInfo);
        return "role-show";
    }


    @GetMapping("/role-add.do")
    public String roleAdd() {
        return "role-add";
    }

    @PostMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }

    @GetMapping("/findRoleByIdAndNotPermission.do")
    public String findRoleByIdAndNotPermission(@RequestParam("id") String id,
                                               HttpServletRequest request) {
        List<Permission> permission = roleService.findRoleByIdAndNotPermission(id);
        request.getSession().setAttribute("permission", permission);
        //返回给前端当前角色id做添加关联使用，减少后端数据库查询压力
        Role role = new Role();
        role.setId(id);
        request.getSession().setAttribute("role", role);
        return "role-addpermission";
    }

    @GetMapping("/addpermission.do")
    public String addpermission(@RequestParam("permissionId") String permissionId,
                                @RequestParam("roleId") String roleId,
                                RedirectAttributes attributes) {
        roleService.addpermission(permissionId,roleId);
        //添加成功跳转到详情页面，重定向id丢失，附带该角色id过去以便查询数据。
        attributes.addAttribute("id",roleId);
        return "redirect:/role/findById.do";
    }

    @GetMapping("/deleteById.do")
    public String deleteById(@RequestParam("id") String id) {
        int r = roleService.deleteByIdAndRole_User(id);
        if (r + "" != null) {
            roleService.deleteById(id);
        }
        return "redirect:/role/findAll.do";
    }

    @GetMapping("/deletePermissionById.do")
    public String deletePermissionById(@RequestParam("roleId") String roleId,
                                       @RequestParam("permissionId") String permissionId,
                                       RedirectAttributes attributes) {

        roleService.deleteByIdAndRole_PermissionId(permissionId, roleId);
        //也可使用addAttribute方法直接带id重定向过去，可以直接获取到
        attributes.addAttribute("id", roleId);
        return "redirect:/role/findById.do";
    }


}
