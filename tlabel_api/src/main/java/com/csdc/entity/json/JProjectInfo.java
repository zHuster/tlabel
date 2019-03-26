package com.csdc.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangzhi
 * @since <pre>2019/3/26</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JProjectInfo {
    private String id;
    private String name;
    private Integer year;
    private String productType;
    private String discipline;
    private String applicant;
    private String agency;
    private String province;

}
