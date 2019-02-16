package com.dooonabe.drpc.controller;

import com.dooonabe.drpc.entity.TimeUsedEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangtt19184 on 2019/2/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public String home(){
        try {
            ////
            TimeUsedEntity.class.newInstance().toString();
            Class c = new TimeUsedEntity().getClass();
            ////
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String commonPrefix = "ABC";
        return commonPrefix;
    }
}
