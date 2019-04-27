package com.graduate.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-03-27  10:25
 * @Description：汉字转化为拼音的工具类
 */
public class PinyinTool {
    HanyuPinyinOutputFormat format = null;

    public static enum Type {
        /**
         * 全部大写
         */
        UPPERCASE,
        /**
         * 全部小写
         */
        LOWERCASE,
        /**
         * 首字母大写
         */
        FIRSTUPPER
    }

    public PinyinTool() {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, "", Type.UPPERCASE);
    }

    public String toPinYin(String str, String spera) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, spera, Type.UPPERCASE);
    }

    /**
     * 将str转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换
     * 如： 明天 转换成 MINGTIAN
     *
     * @param str：要转化的汉字
     * @param spera：转化结果的分割符
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        if (type == Type.UPPERCASE) {

            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {

            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }

        String py = "";
        String temp = "";
        String[] t;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int) c <= 128) {

                py += c;
            } else {
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (t == null) {

                    py += c;
                } else {
                    temp = t[0];
                    if (type == Type.FIRSTUPPER) {

                        temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                    }
                    py += temp + (i == str.length() - 1 ? "" : spera);
                }
            }
        }
        return py.trim();
    }
}

