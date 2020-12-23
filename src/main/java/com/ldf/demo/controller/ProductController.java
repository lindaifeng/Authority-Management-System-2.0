package com.ldf.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldf.demo.pojo.Product;
import com.ldf.demo.service.ProductService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/12 21:37
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
@RequestMapping("/product")
public class ProductController {

  @Autowired
    private ProductService productService;
//设置成OR，表示所列出的条件只要满足其中一个就可以，
// 如果不写或者设置成logical = Logical.AND，表示所有列出的都必须满足才能进入方法。
    @RequiresRoles(value = {"King","USER","ADMIN"},logical = Logical.OR)
    @RequestMapping("/findAll.do")
    public String findAll(@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "8") Integer size,
                          @RequestParam(name = "fuzzyName",defaultValue = "",required = false) String fuzzyName,
                          HttpServletRequest request) {

        PageHelper.startPage(page,size);
        List<Product> products = productService.findAll(fuzzyName);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        request.getSession().setAttribute("pageInfo", pageInfo);
        return "product-list";
    }


    @GetMapping("/findById.do")
    public String findById(@RequestParam("id") String id,HttpServletRequest request){
        Product product = productService.findById(id);
        request.getSession().setAttribute("product",product);
        return "product-update";
    }

    @PostMapping("/productUpdate.do")
    public String productUpdate(Product product){
        productService.productUpdate(product);
        return "redirect:/product/findAll.do";
    }

    @GetMapping("/product-add.do")
    public String productAdd(){
        return "product-add";
    }


    @PostMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:/product/findAll.do";
    }


    @GetMapping("/deleteById.do")
    public String deleteById(@RequestParam("id") String id){
        productService.deleteById(id);
        return "redirect:/product/findAll.do"; //成功重定向
    }

    @GetMapping("/deleteByIdStr.do")
    public String deleteByIdStr(@RequestParam("idStr") String idStr) {
        System.out.println(idStr);
        if (idStr != null) {
            String[] split = idStr.split(",");
            for (int i = 0; i < split.length; i++) {
                productService.deleteById(split[i]);
            }
        }
        return "redirect:/product/findAll.do";
    }


}
