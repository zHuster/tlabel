package com.csdc.controller;

import com.csdc.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ProjectMapper projectMapper;

}
