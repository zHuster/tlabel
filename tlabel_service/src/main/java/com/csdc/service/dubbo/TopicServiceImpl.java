package com.csdc.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.csdc.exception.RequestError;
import com.csdc.exception.RequestException;
import com.csdc.runner.LabelRunner;
import com.csdc.util.Similarity;
import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 */
@Service(version = "1.0.0")
//@org.springframework.stereotype.Service
@RestController
public class TopicServiceImpl implements TopicService {

    @Override
    public Map<Integer, List<String>> getTopicsByDiscipline(DisciplineType disciplineType) {
        return LabelRunner.data.get(disciplineType).getSummary();
    }


    @GetMapping("res/{topicId}")
    @Override
    public Map<Integer, List<String>> getSimilarTopics(@PathVariable("topicId") Integer topicId, DisciplineType disciplineType) {
        NormalizeTopicSummary topicSummary = LabelRunner.data.get(DisciplineType.新闻学与传播学);
        double[][] phi = topicSummary.getPhi();
        if (topicId >= phi.length)
            throw new RequestException(RequestError.NO_SUCH_TOPIC);
        double[] specifiedTopic = phi[topicId];
        Map<Integer, double[]> map = new HashMap<>(phi.length);
        for (int i = 0; i < phi.length; i++) {
            map.put(i, phi[i]);
        }
        List<Double> similarities = new ArrayList<>();
        map.values().stream().mapToDouble(e -> Similarity
                .cosineSimilarty(e, specifiedTopic))
                .forEach(similarities::add);
        Map<Integer, Double> indexPro = new HashMap<>();
        similarities.stream().forEach(e -> indexPro.put(similarities.indexOf(e), e));
        similarities.sort(Comparator.reverseOrder());
        List<Double> topTen = similarities.stream().filter(e -> e != 1)
                .limit(10).collect(Collectors.toList());
        Map<Integer, List<String>> result = indexPro.entrySet().stream()
                .filter(e -> topTen
                        .contains(e.getValue()) && e.getValue() != 1)
                .collect(Collectors
                        .toMap(e -> e.getKey(), e -> topicSummary.getSummary().get(e.getKey())));
        return result;
    }
}
