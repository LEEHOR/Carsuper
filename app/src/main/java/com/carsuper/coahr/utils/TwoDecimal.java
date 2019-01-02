package com.carsuper.coahr.utils;

import java.text.DecimalFormat;

/**
 * Created by Leehor
 * on 2018/10/18
 * on 17:38
 */
public class TwoDecimal {
    /**
     * 将数据保留两位小数
     */
    public static double getTwoDecimal(double num) {
        DecimalFormat dFormat = new DecimalFormat("#.00");
        String yearString = dFormat.format(num);
        Double temp = Double.valueOf(yearString);
        return temp;
    }

}
