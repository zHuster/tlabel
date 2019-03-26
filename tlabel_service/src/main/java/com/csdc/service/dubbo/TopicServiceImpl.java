package com.csdc.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.csdc.exception.RequestError;
import com.csdc.exception.RequestException;
import com.csdc.runner.LabelRunner;
import com.csdc.service.CalculateService;
import com.csdc.util.Similarity;
import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 * <p>
 * 服务端主题标签接口的实现者
 */
@Service(version = "1.0.1")
@org.springframework.stereotype.Service
@RestController
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Override
    @Nullable
    @GetMapping("hh")
    public Map<Integer, Map<Double,List<String>>> getTopicsByDiscipline(DisciplineType disciplineType) {
        Map<Integer, Map<Double,List<String>>> result=new HashMap<>();
        double[][] theta=LabelRunner.data.get(DisciplineType.新闻学与传播学).getTheta();
        Map<Integer,Double> possibilities=CalculateService.getPossibilities(theta);
        Map<Integer, List<String>> temp = LabelRunner.data.get(DisciplineType.新闻学与传播学).getSummary();
        temp.keySet().stream().forEach(e->{
            Map<Double,List<String>> subMap=new HashMap<>();
            subMap.put(possibilities.get(e),temp.get(e));
            result.put(e,subMap);
        });
        log.info("获取{}中的所有主题",disciplineType);
        return result;
    }

    @GetMapping("res/{topicId}")
    @Override
    public Map<Integer, Map<Double, List<String>>> getSimilarTopics(
            @PathVariable("topicId") Integer topicId,
            DisciplineType disciplineType) {
        NormalizeTopicSummary topicSummary = LabelRunner.data.get(DisciplineType.新闻学与传播学);
        if (topicSummary == null) throw new RequestException(RequestError.NO_SUCH_DISCIPLINE);
        double[][] phi = topicSummary.getPhi();
        if (topicId >= phi.length)
            throw new RequestException(RequestError.NO_SUCH_TOPIC);
        double[] specifiedTopic = phi[topicId];
        List<Double> similarities = new ArrayList<>();
        Arrays.stream(phi).mapToDouble(e -> Similarity
                .cosineSimilarty(e, specifiedTopic))
                .forEach(similarities::add);
        List<Double> topTen = similarities.parallelStream().filter(e -> e != 1)
                .sorted(Comparator.reverseOrder())
                .limit(10).collect(Collectors.toList());
        Map<Integer, Map<Double, List<String>>> result = new LinkedHashMap<>();
        topTen.forEach(e -> {
            HashMap toolMap = new HashMap();
            toolMap.put(e, topicSummary.getSummary().get(similarities.indexOf(e)));
            result.put(similarities.indexOf(e), toolMap);
        });
        log.info("获取{}中主题{}的相似主题",disciplineType,topicId);
        return result;
    }
}
