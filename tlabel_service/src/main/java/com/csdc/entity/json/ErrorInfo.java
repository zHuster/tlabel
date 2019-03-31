package com.csdc.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangzhi
 * @since <pre>2019/3/28</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    private int status;
    private String info;
}
