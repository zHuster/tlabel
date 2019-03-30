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
     */
    Map<Integer, Map<Double,List<String>>> getTopicsByDiscipline(DisciplineType disciplineType);

    /**
     * 根据主题相似度获取与所选主题相关的10个主题
     *
     * @param topicId
     * @param disciplineType
     */
    Map<Integer, Map<Double,List<String>>> getSimilarTopics(Integer topicId, DisciplineType disciplineType);

    /**
     * 根据主题id获取主题详情
     * @param ids
     */
    Map<Integer,List<String>> findTopicsByIds(List<Integer> ids,String disciplineType);
}
