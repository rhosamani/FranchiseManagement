package com.franchise.management.controller;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FranchiseUserController {

    @Autowired
    @Qualifier("importUserJob")
    private Job importUserJob;

    @RequestMapping("/addUser")
    public String addUser(){
        return "user added";
    }

}
