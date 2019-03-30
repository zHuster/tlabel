package com.csdc.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangzhi
 * @since <pre>2019/3/26</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JProjectInfo implements Serializable {

    private static final long serialVersionUID = -5150212184450519302L;
    private String id;
    private String name;
    private Integer year;
    private String productType;
    private String discipline;
    private String applicant;
    private String agency;
    private String province;

}
