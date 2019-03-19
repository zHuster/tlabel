package com.csdc.service.dubbo;

import csdc.info.lda_common.model.enums.DisciplineType;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 *
 * 根据学科查询主题模块接口
 */

public interface TopicService {
    /**
     * 根据学科查询主题
     *
     * @param disciplineType
     * @return
     */
    Map<Integer, List<String>> getTopicsByDiscipline(DisciplineType disciplineType);
}