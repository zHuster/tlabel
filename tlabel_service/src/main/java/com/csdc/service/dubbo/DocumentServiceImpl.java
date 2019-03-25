package com.csdc.service.dubbo;

import com.csdc.entity.Project;
import com.csdc.entity.json.JProject;
import com.csdc.exception.RequestError;
import com.csdc.exception.RequestException;
import com.csdc.mapper.ProjectMapper;
import com.csdc.runner.LabelRunner;
import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import csdc.nlp.lda.model.Documents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangzhi
 * @since <pre>2019/3/25</pre>
 */
@RestController
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    ProjectMapper projectMapper;


    @GetMapping("ff")
    @Override
    public List<JProject> getProjectsByTopicId(Integer topicId, DisciplineType disciplineType) {
        topicId=1;
        NormalizeTopicSummary topicSummary=LabelRunner.data.get(DisciplineType.新闻学与传播学);
        double[][] theta=topicSummary.getTheta();
        if (topicId >= theta[0].length) throw new RequestException(RequestError.WRONG_TOPIC_ID);
        List<Double> specifiedPro = new ArrayList<>();
        for (int i = 0; i < theta.length; i++) {
            specifiedPro.add(theta[i][topicId]);
        }
        List<Double> topTen = specifiedPro.stream().sorted(Comparator.reverseOrder()).limit(10).collect(Collectors.toList());
        List<String> ids = specifiedPro.stream().filter(topTen::contains)
                .map(specifiedPro::indexOf).map(topicSummary.getDocuments()::get).map(Documents.Document::getProjectId)
                .map(String::valueOf).collect(Collectors.toList());
        List<Project> projects = projectMapper.findProjectByIds(ids);
        return projects.stream().map(Project::makeNewJProject).collect(Collectors.toList());
    }
}
