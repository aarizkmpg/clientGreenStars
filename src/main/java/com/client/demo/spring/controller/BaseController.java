package com.client.demo.spring.controller;

import com.client.demo.spring.model.demo.BaseModel;
import com.client.demo.spring.mongo.MongoService;
import com.client.demo.spring.service.CommonService;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BaseController {

    @Autowired
    protected CommonService commonService;

    @Autowired
    protected MongoService mongoService;

    @InitBinder
    public void initBinder(WebDataBinder binder, HttpServletRequest servletRequest) {
        binder.setAutoGrowCollectionLimit(500);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        if (!"POST".equalsIgnoreCase(servletRequest.getMethod())){
            return;
        }
        Object nonCastedTarget = binder.getTarget();

        if (nonCastedTarget == null || !(nonCastedTarget instanceof BaseModel)) {
            return;
        }

        BaseModel target = (BaseModel) nonCastedTarget;
        if(target.getCreateBy() == null){
            target.setCreateBy("admin");
            target.setCreateDt(new Date());
        }
        if(target.getLastupdBy() == null){
            target.setLastupdBy("admin");
            target.setLastupdDt(new Date());
        }

    }

}
