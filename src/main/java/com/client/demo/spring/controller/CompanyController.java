package com.client.demo.spring.controller;


import com.client.demo.spring.dao.AuditingObj;
import com.client.demo.spring.model.demo.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("company")
public class CompanyController extends BaseController{


    @GetMapping("list/{page}")
    public String list(HttpServletRequest req, @PathVariable("page")Integer pageIndex, Model model) {
        model.addAttribute("objects", commonService.listObjects(Company.class));
        model.addAttribute("pageIndex",pageIndex);
        return "listObjects";
    }

    @GetMapping("list")
    public String list(HttpServletRequest req) {
        return "redirect:list/0";
    }

    @PostMapping("add")
    public String add(HttpServletRequest req, @ModelAttribute("company") @Valid Company company,@RequestParam("modelType") String modelType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:list";
        }
        company.setLastupdDt(new Date());
        company.setCreateDt(new Date());
        company.setCreateBy("admin1");
        company.setLastupdBy("admin2");
        long id = commonService.saveObject(company);
        mongoService.insertObject(commonService.getObjectById(id,Company.class),"company",company.getId().toString());
        return "redirect:list";
    }

    @PostMapping("update")
    public String update(HttpServletRequest req, @ModelAttribute("company") @Valid Company company, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:list";
        }
        company.setLastupdDt(new Date());
        company.setLastupdBy("admin2");
        commonService.updateObject(company);
        mongoService.insertObject(company,"company",company.getId().toString());
        return "redirect:getById?id="+ company.getId();
    }

    @GetMapping("getById")
    public String getById(HttpServletRequest req,@ModelAttribute("id") @Valid Long id, BindingResult result, Model model) {
        Company company = commonService.getObjectById(id, Company.class);
        model.addAttribute("company",company);
        model.addAttribute("auditLogs",commonService.listObjectAuditLogs(Company.class,id,null));
        model.addAttribute("mongoLogs",mongoService.listObject("company",company.getId().toString()));
        return "updateObject";
    }

    @GetMapping(path = "getReversion", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getReversion(HttpServletRequest req,@ModelAttribute("vid") @Valid Integer vid, BindingResult result, Model model)
    {
        //Get data from service layer into entityList.
        List<AuditingObj> list = commonService.listObjectAuditLogs(Company.class,null,vid);
        if(list != null && list.size()> 0){
            AuditingObj aObj = list.get(0);  //for Company and product, each version should only has 1 record.
            ObjectMapper Obj = new ObjectMapper();
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
    @ModelAttribute("company")
    public Company formBackingObject() {
        return new Company();
    }

    @ModelAttribute("pageTitle")
    public String fromIdToSetTitle() {
        return "Company";
    }

}
