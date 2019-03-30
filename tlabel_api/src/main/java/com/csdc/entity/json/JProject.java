package com.csdc.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangzhi
 * @since <pre>2019/3/25</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JProject implements Serializable {

    private static final long serialVersionUID = 3804190366025043520L;
    private String id;
    private String name;
    private Integer year;

}
