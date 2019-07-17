package com.client.demo.spring.controller;


import com.client.demo.spring.dao.AuditingObj;
import com.client.demo.spring.model.demo.Attribute;
import com.client.demo.spring.model.demo.Company;
import com.client.demo.spring.model.demo.Product;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController extends BaseController{

    @GetMapping("list/{page}")
    public String list(HttpServletRequest req, @PathVariable("page")Integer pageIndex, Model model) {
        model.addAttribute("objects", commonService.listObjects(Product.class));
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("companies",commonService.listObjects(Company.class));
        return "listObjects";
    }

    @GetMapping("list")
    public String list(HttpServletRequest req) {
        return "redirect:list/0";
    }

    @PostMapping("add")
    public String add(HttpServletRequest req, @ModelAttribute("product") @Valid Product product,@RequestParam("modelType") String modelType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:list";
        }
        long id = commonService.saveObject(reverseObject(product));
        mongoService.insertObject(commonService.getObjectById(id,Product.class),"product",product.getId().toString());
        return "redirect:list";
    }

    @PostMapping("update")
    public String update(HttpServletRequest req, @ModelAttribute("product") @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:list";
        }
        commonService.updateObject(reverseObject(product));
        mongoService.insertObject(product,"product",product.getId().toString());
        return "redirect:getById?id="+ product.getId();
    }

    private Product reverseObject(Product product){
        List<Attribute> list = product.getAttributes();
        List<Attribute> reversedList = new ArrayList<>();
        if(list != null && list.size() >0){
            list.stream().forEach(attr -> {
                attr.setProduct(product);
                if(attr.getId() != null){
                    if(attr.getDeleting() == 1){
                        attr.setProduct(null);
                        commonService.deleteObject(attr);
                    }else {
                        reversedList.add(attr);
                    }
                }else{
                    if(attr.getDeleting() != 1 && attr.getName() != null && !"".equals(attr.getName().trim())){
                        reversedList.add(attr);
                    }
                }
            });
        }
        System.out.println(reversedList.size()+"!!!!!!");
        product.setAttributes(reversedList);
        return product;
    }

    @GetMapping("getById")
    public String getById(HttpServletRequest req,@ModelAttribute("id") @Valid Long id, BindingResult result, Model model) {
        Product product = commonService.getObjectById(id, Product.class);
        model.addAttribute("product",product);
        model.addAttribute("companies",commonService.listObjects(Company.class));
        model.addAttribute("auditLogs",commonService.listObjectAuditLogs(Product.class,id,null));
        model.addAttribute("mongoLogs",mongoService.listObject("product",product.getId().toString()));
        return "updateObject";
    }

    @GetMapping(path = "getReversion", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getReversion(HttpServletRequest req,@ModelAttribute("vid") @Valid Integer vid, BindingResult result, Model model)
    {
        //Get data from service layer into entityList.
        List<AuditingObj> list = commonService.listObjectAuditLogs(Product.class,null,vid);
        if(list != null && list.size()> 0){
            AuditingObj aObj = list.get(0);  //for Company and product, each version should only has 1 record.
            ObjectMapper Obj = new ObjectMapper();
            Obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            Obj.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

            try {
                String jsonStr = Obj.writeValueAsString(aObj);
                System.out.println(jsonStr);
                return jsonStr;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{}";
    }

    @ModelAttribute("product")
    public Product formBackingObject() {
        return new Product();
    }

    @ModelAttribute("pageTitle")
    public String fromIdToSetTitle() {
        return "Product";
    }

}
