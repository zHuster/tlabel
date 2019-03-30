package com.csdc.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.csdc.entity.Project;
import com.csdc.entity.ProjectInfo;
import com.csdc.entity.json.JProject;
import com.csdc.entity.json.JProjectInfo;
import com.csdc.exception.RequestError;
import com.csdc.exception.RequestException;
import com.csdc.mapper.ProjectMapper;
import com.csdc.runner.LabelRunner;
import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import csdc.nlp.lda.model.Documents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangzhi
 * @since <pre>2019/3/25</pre>
 */
@RestController
@Slf4j
@Service(version = "1.0.0")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    ProjectMapper projectMapper;

    /**
     * 获取主题相关文档
     *
     * @param topicId
     * @param disciplineType
     * @return
     */
    @GetMapping("ff")
    @Override
    public List<JProject> findProjectsByTopic(Integer topicId, DisciplineType disciplineType) {
        NormalizeTopicSummary topicSummary = LabelRunner.data.get(DisciplineType.新闻学与传播学);
        double[][] theta = topicSummary.getTheta();
        topicId=100;
        if (topicId >= theta[0].length) throw new RequestException(RequestError.WRONG_TOPIC_ID);
        List<Double> specifiedPro = new ArrayList<>();
        for (int i = 0; i < theta.length; i++) {
            specifiedPro.add(theta[i][topicId]);
        }
        List<Double> topFifty = specifiedPro.stream().sorted(Comparator.reverseOrder())
                .limit(50)
                .collect(Collectors.toList());
        List<String> ids = specifiedPro.stream().filter(topFifty::contains)
                .map(specifiedPro::indexOf).map(topicSummary.getDocuments()::get)
                .map(Documents.Document::getProjectId)
                .map(String::valueOf).collect(Collectors.toList());
        List<Project> projects = projectMapper.findProjectByIds(ids);
        log.info("获取{}中主题{}的50个相关文档", disciplineType, topicId);
        return projects.stream().map(Project::makeNewJProject).collect(Collectors.toList());
    }

    /**
     * 获取主题项目详情
     *
     * @param projectId
     * @return
     */
    @GetMapping("l/{projectId}")
    @Override
    public JProjectInfo findProjectInfoById(@PathVariable("projectId") String projectId) {
        validateStringArgs(projectId);
        ProjectInfo projectInfo = projectMapper.findProjectInfoById(projectId);
        Optional<JProjectInfo> jProjectInfo = Stream.of(projectInfo).
                map(ProjectInfo::makeNewJProjectInfo).findFirst();
        log.info("获取项目{}详情", projectId);
        return jProjectInfo.get();
    }

    /**
     * 字符串验证
     *
     * @param projectId
     */
    private void validateStringArgs(String projectId) {
        if (StringUtils.isEmpty(projectId))
            throw new RequestException(RequestError.PROJECT_ID_IS_ILLEGAL);
    }
}
