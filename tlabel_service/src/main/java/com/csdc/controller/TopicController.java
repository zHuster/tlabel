package com.csdc.controller;

import com.csdc.runner.LabelRunner;
import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    @GetMapping("/hello/11")
    public NormalizeTopicSummary getResult() {
        return LabelRunner.data.get(DisciplineType.新闻学与传播学);
    }

}
