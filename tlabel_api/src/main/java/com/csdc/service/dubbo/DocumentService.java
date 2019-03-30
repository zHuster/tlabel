package com.csdc.service.dubbo;

import com.csdc.entity.json.JProject;
import com.csdc.entity.json.JProjectInfo;
import csdc.info.lda_common.model.enums.DisciplineType;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzhi
 * @since <pre>2019/3/25</pre>
 */
public interface DocumentService {

    /**
     * 查询主题相关的所有项目
     *
     * @param topicId
     * @param disciplineType
     * @return
     */
    List<JProject> findProjectsByTopic(Integer topicId, DisciplineType disciplineType);

    /**
     * 查询某一项目详情
     *
     * @param projectId
     * @return
     */
    JProjectInfo findProjectInfoById(String projectId);
}
