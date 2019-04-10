package com.csdc.runner;

import com.csdc.exception.OperateError;
import com.csdc.exception.OperateException;
import com.csdc.util.Constants;
import csdc.info.lda_common.model.enums.DisciplineType;
import csdc.label.model.NormalizeTopicSummary;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 * <p>
 * 启动时自动提取标签
 */
@Slf4j
@Component
@Order(1)
@Ignore
public class LabelRunner implements ApplicationRunner {

    public static ConcurrentHashMap<DisciplineType, NormalizeTopicSummary> data = new ConcurrentHashMap<>();

    private ExecutorService executors = Executors.newFixedThreadPool(Constants.THREAD_COUNTS);


    @Override
    public void run(ApplicationArguments args) throws Exception {

        File file = new File(Constants.MODEL_LOCATION);
        if (!file.exists() && !file.mkdirs())
            throw new OperateException(OperateError.FAIL_TO_MAKEDIRS);
        File[] files = file.listFiles();
        Arrays.stream(files).forEach(e -> {
            if (!e.getName().endsWith("model"))
                throw new OperateException(OperateError.WRONG_FIEL_EXIST_IN_FOLDER);
            executors.submit(() -> loadModels(e.getAbsolutePath(), e.getName()));
            log.info("读取{}成功", e.getName().split("\\.")[0]);
        });
        executors.shutdown();
    }

    /**
     * 从模型文件中加载出对象放入内存中（模型数据结构较复杂）
     *
     * @param modelPath
     * @param fileName
     */
    private void loadModels(String modelPath, String fileName) {
        ObjectInputStream ois = null;
        NormalizeTopicSummary topicSummary;
        try {
            ois = new ObjectInputStream(new FileInputStream(modelPath));
            topicSummary = (NormalizeTopicSummary) ois.readObject();
            data.put(DisciplineType.新闻学与传播学, topicSummary);
        } catch (Exception e) {
            log.error("读取模型{}失败", fileName);
        } finally {
            if (ois != null)
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
