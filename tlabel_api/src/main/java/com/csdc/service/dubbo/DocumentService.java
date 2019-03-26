package com.csdc.service.dubbo;

import com.csdc.entity.json.JProject;
import com.csdc.entity.json.JProjectInfo;
import csdc.info.lda_common.model.enums.DisciplineType;

import java.util.List;

/**
 * @author zhangzhi
 * @since <pre>2019/3/25</pre>
 */
public interface DocumentService {

    List<JProject> findProjectsByTopic(Integer topicId, DisciplineType disciplineType);

    JProjectInfo findProjectInfoById(String projectId);
}
