package com.csdc.util;

/**
 * 计算相似度相关方法类
 * Created by fengqian on 2017/11/15 0015
 */
public class Similarity {

    /**
     * 计算两个数组的余弦相似度
     *
     * @param a
     * @param b
     * @return
     */
    public static double cosineSimilarty(double[] a, double[] b) {
        if (a.length != b.length)
            return 0;
        else {
            double multiple = 0.0;
            double square_sum_a = 0.0;
            double square_sum_b = 0.0;
            for (int i = 0; i < a.length; i++) {
                multiple += a[i] * b[i];
                square_sum_a += a[i] * a[i];
                square_sum_b += b[i] * b[i];
            }
            if (square_sum_a == 0 || square_sum_b == 0)
                return 0;
            else
                return multiple / (Math.sqrt(square_sum_a) * Math.sqrt(square_sum_b));
        }
    }

}
