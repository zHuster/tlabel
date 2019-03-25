package com.csdc.controller;

import com.csdc.entity.Project;
import com.csdc.mapper.ProjectMapper;
import com.csdc.runner.LabelRunner;
import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    @Autowired
    ProjectMapper projectMapper;



}
