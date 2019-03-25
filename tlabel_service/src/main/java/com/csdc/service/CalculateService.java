package com.csdc.service;

import com.csdc.exception.OperateError;
import com.csdc.exception.OperateException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhi
 * @since <pre>2019/3/22</pre>
 * <p>
 * 计算主题在文档中的比率
 */
public class CalculateService {

    public static Map<Integer, Double> getPossibilities(double[][] theta) {
        if (theta == null || theta.length <= 0) throw new OperateException(OperateError.MODEL_NOT_CONTAINS_THETA);
        Map<Integer, Double> result = new HashMap<>();
        double sum;
        for (int i = 0; i < theta[0].length; i++) {
            sum = 0;
            for (int j = 0; j < theta.length; j++) {
                sum += theta[j][i];
            }
            result.put(i, sum / theta[0].length);
        }
        return result;
    }
}
