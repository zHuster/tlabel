package com.csdc.runner;

import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 * <p>
 * 启动时自动提取标签
 */
@Slf4j
@Component
@Order(1)
public class LabelRunner implements ApplicationRunner {

    public static ConcurrentHashMap<DisciplineType, NormalizeTopicSummary> data = new ConcurrentHashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String modlePath = "tlabel_service/data/model/新闻学与传播学.model";
        ObjectInputStream ois = null;
        NormalizeTopicSummary topicSummary;
        try {
            ois = new ObjectInputStream(new FileInputStream(modlePath));
            topicSummary = (NormalizeTopicSummary) ois.readObject();
            data.put(DisciplineType.新闻学与传播学, topicSummary);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("模型不存在");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null)
                ois.close();
        }
    }

}
