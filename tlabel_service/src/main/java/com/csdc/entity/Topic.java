package com.csdc.entity;

import com.csdc.util.HashGenerator;
import csdc.info.lda_common.model.enums.DisciplineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private String id;
    private Integer index;
    private DisciplineType disciplineType;
    private String label;

    public Topic(Map.Entry<Integer, List<String>> entry) {
        this.id = HashGenerator.generateHexHash(8);
        this.index = entry.getKey();
        this.label = entry.getValue().stream().reduce((x, y) -> x + ";" + y).get();
    }
}
