package com.base.android.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by NguyenLinh on 15,January,2019
 */
public class CurrencyUtils {
    public static String convertFloatToCurrency(Float money) {
        try {
            NumberFormat formatter = new DecimalFormat("###,###");
            String resp = formatter.format(money);
            resp = resp.replaceAll(",", ".");
            return resp;
        } catch (Exception e) {
            return "";
        }
    }
}
