package com.ldf.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldf.demo.pojo.Orders;
import com.ldf.demo.service.OrderService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 19:19
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //设置成OR，表示所列出的条件只要满足其中一个就可以，
// 如果不写或者设置成logical = Logical.AND，表示所有列出的都必须满足才能进入方法。
    @RequiresRoles(value = {"King","USER","ADMIN"},logical = Logical.OR)
    @RequestMapping("/findAll.do")
    public String findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
                          @RequestParam(name = "fuzzyName",defaultValue = "",required = false) String fuzzyName,
                          HttpServletRequest request) {

        PageHelper.startPage(page, pageSize);
        List<Orders> orders = orderService.findAll(fuzzyName);
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        request.getSession().setAttribute("pageInfo", pageInfo);
        return "orders-page-list";
    }



    @GetMapping("/findById.do")
    public String findById(@RequestParam("id") Integer id,
                           HttpServletRequest request) {
        Orders orders = orderService.findById(id);
        System.out.println(orders);
        request.getSession().setAttribute("orders",orders);
        return "orders-show";
    }


    @GetMapping("/deleteById.do")
    public String deleteById(@RequestParam("id") Integer id) {
        orderService.deleteById(id);
        return "redirect:/orders/findAll.do";
    }

    @GetMapping("/deleteByIdStr.do")
    public String deleteByIdStr(@RequestParam("idStr") String idStr) {
        if (idStr != null) {
            for (int i = 0; i < idStr.length(); i++) {
                String[] split = idStr.split(",");
                orderService.deleteById(Integer.parseInt(split[i]));
            }
        }
        return "redirect:/orders/findAll.do";
    }


}
