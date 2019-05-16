package com.lwx.multifunctiondemo.utils;

import android.text.TextUtils;

/**
 * 1.判断传入的String的字段是否为空
 */
public class StringUtils {

    /**
     * 去掉金额中的逗号
     */
    public static String removeDouHao(String str) {
        StringBuffer buffer = new StringBuffer();
        if (!TextUtils.isEmpty(str)) {

            if (str.contains(",")) {

                String result[] = str.split(",");
                for (int i = 0; i < result.length; i++) {
                    buffer.append(result[i]);
                }

                return buffer.toString();

            } else {
                if (Double.parseDouble(str) > 0) {
                    return str;
                } else {
                    return "0.00";
                }

            }

        } else {
            return "0.00";
        }
    }


    /**
     * 如果是0,则返回0.00
     */
    public static String isDoubleZero(String str) {


        if (!TextUtils.isEmpty(str)) {

            if (str.contains(",")) {

                return str;

            } else {
                if (Double.parseDouble(str) > 0) {
                    return str;
                } else {
                    return "0.00";
                }

            }

        } else {
            return "0.00";
        }


    }

    /**
     * 判断是否为空,
     * true,字符串为空，
     * false，字符串不为空
     */
    public static boolean isStringNull(String str) {
        return !(TextUtils.isEmpty(str) || TextUtils.equals(str, "null") || TextUtils.equals(str, "NULL"));
    }

    /**
     * <pre>
     * 将字符串从右至左每三位加一逗号
     * </pre>
     *
     * @param str 需要加逗号的字符串
     * @return 以从右至左每隔3位加一逗号显示
     */
    public static String displayWithComma(String str) {
        String[] sourceStrArray = str.split("\\.");
        String newStr = "";
        if (sourceStrArray.length == 1) {//没有小数点
            newStr = displayWithNumber(str);
        } else if (sourceStrArray.length == 2) {//有小数点
            newStr = displayWithNumber(sourceStrArray[0]) + "." + sourceStrArray[1];
        } else {
            newStr = str;
        }
        return newStr;
    }

    /**
     * 整数字符串转化成逗号隔开的
     *
     * @param str
     * @return
     */
    public static String displayWithNumber(String str) {

        str = new StringBuffer(str).reverse().toString(); // 先将字符串颠倒顺序
        String str2 = "";

        int size = (str.length() % 3 == 0) ? (str.length() / 3) : (str.length() / 3 + 1); // 每三位取一长度


        for (int i = 0; i < size - 1; i++) { // 前n-1段
            str2 += str.substring(i * 3, i * 3 + 3) + ",";
        }

        for (int i = size - 1; i < size; i++) { // 第n段
            str2 += str.substring(i * 3, str.length());
        }

        str2 = new StringBuffer(str2).reverse().toString();

        return str2;
    }



    /**
     * 去除含有的字符
     * */
    public static String replaceWord(String str, String word){
        if(!isStringNull(word)){
            return str;
        }

        if(!isStringNull(str)){
            return "";
        }

        if(str.contains(word)){
            return str.replace(word,"");
        }else{
            return str;
        }
    }

}
