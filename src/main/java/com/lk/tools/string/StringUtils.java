package com.lk.tools.string;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luokun
 * @date 2019/4/22 15:38
 */
public class StringUtils {
    /**
     * 判断字符串是否为null
     *
     * @param temp
     *            需要验证的字符串
     * @return boolean true：字符串是null，false：字符串不是null
     */
    public static boolean isNull(String temp) {
        if (temp == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串不是null或者空字符串
     *
     * @param temp
     * @return boolean true：字符串既不是null也不是空字符串，false：字符串是null或者空字符串
     */
    public static boolean isValue(String temp) {
        if (temp != null && !"".equals(temp)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否由全数字组成
     * @param str ： 数字字符串
     * @return boolean true：全数字组成，false：不是全数字组成
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static boolean isValueNum(int temp) {

        String strTemp = Integer.toString(temp);
        if (strTemp != null && !"".equals(strTemp)) {
            return true;
        }
        return false;
    }

    /**
     * 将null字符串转换为空字符串
     *
     * @param temp
     *            需要转换的字符串
     * @return String 如果字符串为null则返回空字符串，如果为其他返回字符串本身
     */
    public static String nullConvertEmpty(String temp) {
        if (temp != null) {
            return temp;
        }
        return "";
    }

    /**
     * 获得当前字符串的index位置的char
     * @param temp
     *            需要获得char的字符串
     * @param index
     *            字符串的位置
     * @return char 字符串index位置的char
     * @throws StringIndexOutOfBoundsException
     *             temp为null或空对象，或index大于temp的长度
     */
    public static char charAt(String temp, int index)
            throws StringIndexOutOfBoundsException {
        if (temp == null || "".equals(temp)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        if (temp.length() < index) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return temp.charAt(index);
    }

    public static int stringToInt(String temp) throws NumberFormatException {

        return Integer.parseInt(temp);
    }


    /**
     * 通过比较temp与anotherTemp字符串的字典，得知两个字符串是否相等，如不等，返回这两个字符串的字典差。
     *
     * @param temp
     *            需要比较的字符串
     * @param anotherTemp
     *            需要比较的字符串
     * @return int 0：相等 <0：temp小于anotherTemp的字典差 >0：temp大于anotherTemp的字典差
     *         任何字符串与null或空对象的字典差均为1
     */
    public static int compareTo(String temp, String anotherTemp) {
        if (temp == null || anotherTemp == null) {
            return -1;
        }
        return temp.compareTo(anotherTemp);
    }

    /**
     * 通过比较temp与anotherTemp字符串的字典，忽略大小写， 得知两个字符串是否相等，如不等，返回这两个字符串的字典差。
     *
     * @param temp
     *            需要比较的字符串
     * @param anotherTemp
     *            需要比较的字符串
     * @return int 0：相等 <0：temp小于anotherTemp的字典差 >0：temp大于anotherTemp的字典差
     *         任何字符串与null或空对象的字典差均为1
     */
    public static int compareToIgnoreCase(String temp, String anotherTemp) {
        if (temp == null || anotherTemp == null) {
            return -1;
        }
        return temp.compareToIgnoreCase(anotherTemp);
    }

    /**
     * 将两个字符串拼接到一起
     *
     * @param temp
     *            需要拼接的字符串
     * @param anotherTemp
     *            需要拼接的字符串
     * @return String 拼接后的字符串，如果字符串均为null，返回空字符串，如果其中一个为null，返回另一个
     */
    public static String concat(String temp, String anotherTemp) {
        if (temp == null && anotherTemp == null) {
            return "";
        }
        if (temp == null) {
            return anotherTemp;
        }
        if (anotherTemp == null) {
            return temp;
        }
        return temp.concat(anotherTemp);
    }

    /**
     * 字符串包含指定的char值序列
     *
     * @param temp
     *            字符串
     * @param anotherTemp
     *            包含char值的序列
     * @return boolean true：temp包含anotherTemp， false：temp不包含anotherTemp
     *         如果temp或anotherTemp为null返回false， 如temp为空值，返回false，
     *         如anotherTemp为空值，返回true。
     */
    public static boolean contains(String temp, CharSequence anotherTemp) {
        if (temp == null || anotherTemp == null) {
            return false;
        }
        return temp.contains(anotherTemp);
    }

    /**
     * 字符串与指定的char值序列相等
     * @param temp
     *            字符串
     * @param anotherTemp
     *            指定的char值序列
     * @return boolean true：temp包含anotherTemp， false：temp不包含anotherTemp
     *         如果temp或anotherTemp为null返回false， 如temp为空值，返回false，
     *         如anotherTemp为空值，返回true。
     */
    public static boolean contentEquals(String temp, CharSequence anotherTemp) {
        if (temp == null || anotherTemp == null) {
            return false;
        }
        return temp.contains(anotherTemp);
    }

    /**
     * 方法返回的测试该字符串是否以指定后缀sffix结束
     *
     * @param temp
     *            字符串
     * @param sffix
     *            字符串
     * @return boolean true：temp以sffix结尾， false：temp不以sffix结尾
     *         如果temp或sffix为null返回false， 如temp为空值，返回false， 如sffix为空值，返回true。
     */
    public static boolean endsWith(String temp, String sffix) {
        if (temp == null || sffix == null) {
            return false;
        }
        return temp.endsWith(sffix);
    }

    /**
     * 判断两个字符串是否相同
     * @param temp
     *            字符串1
     * @param temp
     *            字符串2
     * @return boolean true：temp1与temp2相同
     *         false：temp1与temp2不同，如temp1或temp2为null，也会返回false。
     */
    public static boolean equals(String temp, String anotherTemp) {
        try {
            if (temp.equals(anotherTemp)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断两个字符串是否相同，忽略大小写
     *
     * @param temp
     *            字符串1
     * @param temp
     *            字符串2
     * @return boolean true：temp1与temp2相同
     *         false：temp1与temp2不同，如temp1或temp2为null，也会返回false。
     */
    public static boolean equalsIgnoreCase(String temp, String anotherTemp) {
        try {
            if (temp.equalsIgnoreCase(anotherTemp)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断字符串temp是否包含antherTemp
     *
     * @param temp
     *            字符串
     * @param anotherTemp
     *            包含的字符串
     * @return >=0：antherTemp在temp中的第一个antherTemp起始位置， -1：antherTemp不包含与temp内。
     */
    public static int indexOf(String temp, String anotherTemp) {
        return indexOf(temp, anotherTemp, 0);
    }

    /**
     * 判断字符串temp是否包含antherTemp
     *

     * @param temp
     *            字符串
     * @param anotherTemp
     *            包含的字符串
     * @param index
     *            从temp字符串的哪个位置开始查找
     * @return int >=0：antherTemp在temp中的第一个antherTemp起始位置，
     *         -1：antherTemp不包含与temp内。
     */
    public static int indexOf(String temp, String anotherTemp, int index) {
        if (temp == null || anotherTemp == null) {
            return -1;
        }
        return temp.indexOf(anotherTemp, index);
    }

    /**
     * 判断字符串是否为空字符串，此判断如果字符串为null则返回true
     *
     * @param temp
     *            需要验证的字符串
     * @return boolean true：字符串是空字符串，false：字符串不是空字符串
     */
    public static boolean isEmpty(String temp) {
        if (temp == null) {
            return true;
        }
        return temp.isEmpty();
    }

    /**
     * 判断字符串temp是否包含antherTemp
     * @param temp
     *            字符串
     * @param anotherTemp
     *            包含的字符串
     * @return
     */
    public static int lastIndexOf(String temp, String anotherTemp) {
        return lastIndexOf(temp, anotherTemp, temp.length());
    }

    /**
     * 判断字符串temp是否包含antherTemp
     *
     * @param temp
     *            字符串
     * @param anotherTemp
     *            包含的字符串
     * @param index
     *            在temp字符串的哪个位置结束查找
     * @return int >=0：antherTemp在temp中的第一个antherTemp起始位置，
     *         -1：antherTemp不包含与temp内。
     */
    public static int lastIndexOf(String temp, String anotherTemp, int index) {
        if (temp == null || anotherTemp == null) {
            return -1;
        }
        return temp.lastIndexOf(anotherTemp, index);
    }

    /**
     * 判断字符串temp从toffset位置开始计算与字符串other，从ooffset开始计算比较len个字节长度是否相同， 并判断大小写
     *
     * @param toffset
     *            temp字符串开始的位置
     * @param other
     *            比较的字符串
     * @param ooffset
     *            比较的字符串开始的位置
     * @param len
     *            比较的字符串长度
     * @return
     */
    public static boolean regionMatches(String temp, int toffset, String other,
                                        int ooffset, int len) {
        if (temp == null || other == null) {
            return false;
        }
        return temp.regionMatches(false, toffset, other, ooffset, len);
    }

    /**
     * 判断字符串temp从toffset位置开始计算与字符串other，从ooffset开始计算比较len个字节长度是否相同，
     * 根据ignoreCase的值判断是否忽略大小写
     *
     * @param ignoreCase
     *            是否忽略大小写 true：忽略大小写，false：判断大小写
     * @param toffset
     *            temp字符串开始的位置
     * @param other
     *            比较的字符串
     * @param ooffset
     *            比较的字符串开始的位置
     * @param len
     *            比较的字符串长度
     * @return
     */
    public static boolean regionMatches(String temp, boolean ignoreCase,
                                        int toffset, String other, int ooffset, int len) {
        if (temp == null || other == null) {
            return false;
        }
        return temp.regionMatches(ignoreCase, toffset, other, ooffset, len);
    }

    /**
     * 将原是字符串内包含的oldChar替换为newChar
     *
     * @param temp
     *            原始字符串
     * @param oldChar
     *            被替换的字符串
     * @param newChar
     *            替换成的字符串
     * @return String 如temp为null或为空字符串，oldChar或newChar为null则返回temp，
     *         其他情况返回replace后的结果
     */
    public static String replace(String temp, CharSequence oldChar,
                                 CharSequence newChar) {
        if (temp == null || "".equals(temp) || oldChar == null
                || newChar == null) {
            return temp;
        }
        return temp.replace(oldChar, newChar);
    }

    /**
     * 将原有字符串通过正则表达式regex替换为replacement
     *
     * @param temp
     *            原有字符串
     * @param regex
     *            正则表达式
     * @param replacement
     *            需要替换的内容
     * @return String 如temp为null或为空字符串，regex或replacement为null则返回temp，
     *         其他情况返回replace后的结果
     */
    public static String replaceAll(String temp, String regex,
                                    String replacement) {
        if (temp == null || "".equals(temp) || regex == null
                || replacement == null) {
            return temp;
        }
        return temp.replaceAll(regex, replacement);
    }

    /**
     * 将原有字符串通过正则表达式将第一个regex替换为replacement，
     *
     * @param temp
     *            原有字符串
     * @param regex
     *            正则表达式
     * @param replacement
     *            需要替换的内容
     * @return String 如temp为null或为空字符串，regex或replacement为null则返回temp，
     *         其他情况返回replace后的结果
     */
    public static String replaceFirst(String temp, String regex,
                                      String replacement) {
        if (temp == null || "".equals(temp) || regex == null
                || replacement == null) {
            return temp;
        }
        return temp.replaceAll(regex, replacement);
    }

    /**
     * 方法返回的测试该字符串是否以指定前缀prefix开始
     *
     * @param temp
     *            字符串
     * @param prefix
     *            字符串
     * @return boolean true：temp以prefix开始， false：temp不以prefix开始
     *         如果temp或prefix为null返回false， 如temp为空值，返回false， 如prefix为空值，返回true。
     */
    public static boolean startsWith(String temp, String prefix) {
        return startsWith(temp, prefix, 0);
    }

    /**
     * 方法返回的测试该字符串是否以指定前缀prefix开始
     *
     * @param temp
     *            字符串
     * @param prefix
     *            字符串
     * @param index
     *            从temp的第几个位置开始
     * @return boolean true：temp以prefix开始， false：temp不以prefix开始
     *         如果temp或prefix为null返回false， 如temp为空值，返回false，
     *         如index大于temp的长度，返回false 如prefix为空值且index小于temp的长度，返回true。
     */
    public static boolean startsWith(String temp, String prefix, int index) {
        if (temp == null || prefix == null) {
            return false;
        }
        if (index > temp.length()) {
            return false;
        }
        return temp.startsWith(prefix, index);
    }

    /**
     * 截取字符串temp，从beginIndex开始至endIndex结束
     *
     * @param temp
     *            字符串
     * @param beginIndex
     *            截取的开始位置，如小于0为0处理，如大于temp的长度为temp.length处理
     * @return String 如temp为null则返回temp，其他返回temp substring后的结果
     */
    public static String substring(String temp, int beginIndex) {
        return substring(temp, beginIndex, temp.length());
    }

    /**
     * 截取字符串temp，从beginIndex开始至endIndex结束
     *
     * @param temp
     *            字符串
     * @param beginIndex
     *            截取的开始位置，如小于0为0处理，如大于temp的长度为temp.length处理
     * @param endIndex
     *            截取的结束位置，如小于0为0处理，如大于temp的长度为temp.length处理
     * @return String 如temp为null则返回temp，其他返回temp substring后的结果
     */
    public static String substring(String temp, int beginIndex, int endIndex) {
        if (temp == null) {
            return temp;
        }
        if (beginIndex < 0) {
            beginIndex = 0;
        }
        if (beginIndex > temp.length()) {
            beginIndex = temp.length();
        }
        if (endIndex < 0) {
            endIndex = temp.length();
        }
        if (endIndex > temp.length()) {
            endIndex = temp.length();
        }
        return temp.substring(beginIndex, endIndex);
    }

    /**
     * 将object转成字符串
     *
     * @param obj
     *            ：任意的对象
     * @return
     */
    public static String valueOf(Object obj) {
        return String.valueOf(obj);
    }


    /**
     * 是判断对象是否存在
     * @Title isNullOrEmpty
     * @Description TODO
     * @param @param obj
     * @param @return
     * @return boolean
     * @throws
     */

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;
        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;
        if (obj instanceof Collection)
            return ((Collection<?>) obj).isEmpty();
        if (obj instanceof Map)
            return ((Map<?, ?>) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
