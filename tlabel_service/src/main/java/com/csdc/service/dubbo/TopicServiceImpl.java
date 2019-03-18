package com.csdc.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.csdc.runner.LabelRunner;
import csdc.info.lda_common.model.enums.DisciplineType;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 *
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class TopicServiceImpl implements TopicService{

    @Override
    public Map<Integer, List<String>> getTopicsByDiscipline(DisciplineType disciplineType) {
        return LabelRunner.data.get(disciplineType).getSummary();
    }
}
