package com.lk.tools.utils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.lk.tools.date.DateUtils;
import com.lk.tools.string.StringUtils;

/**
 * @author luokun
 * @date 2019/4/23 10:21
 */
public class Utils {
    /**
     * 返回32位UUID，不包含中划线
     * @author eric
     * @date 2017年6月16日下午5:38:36
     * @return 去掉中划线的uuid
     */
    public static String getUuidFor32(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 返回36位UUID，包含中划线
     * @author eric
     * @date 2017年6月16日下午5:38:11
     * @return uuid
     */
    public static String getUuidFor36(){
        return UUID.randomUUID().toString();
    }

    /**
     * 返回自定义id
     * @author Aran
     * @date 2017年6月16日下午5:38:11
     * @return
     */
    public static String getCustomizedId(){

        String strCustom = "wms";
        String strTime = DateUtils.getDate("yyMMdd");
        int strRandom = getRandom(6);
        String strReq = strCustom + strTime + strRandom;

        return strReq;
    }
    /**
     * 返回订单号
     * @Descrption
     * @author yeJingYang
     * @date 2019年3月21日下午1:27:49
     * @param type  订单号类型：
     *                    1:收货单号，已"R"开头
     *                    2:订单号，已"O"开头
     * @return
     */
    public static String getOrderNum(int type){
        StringBuffer workNum = new StringBuffer();
        if (type == 1) {
            workNum.append("R");
        } else if (type == 2) {
            workNum.append("O");
        }
        workNum.append(DateUtils.getDate("yyMMdd"));
        workNum.append(getRandom(4));

        return workNum.toString();
    }
    /**
     * 返回托盘号
     * @Descrption
     * @author yeJingYang
     * @date 2019年4月9日下午8:14:10
     * @return
     */
    public static String getTrayCode(){
        StringBuffer trayCode = new StringBuffer();
        trayCode.append("P");
        trayCode.append(DateUtils.getDate("yyMMdd"));
        trayCode.append(getRandom(3));

        return trayCode.toString();
    }

    /**
     * @Descrption 返回指定位数的随机数
     * @author Aran
     * @date 2017年5月12日下午12:31:23
     * @param length
     * @return int
     */
    public static int getRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * @Descrption
     * @author eric
     * @date 2017年5月12日下午12:31:23
     * @param PublicHandlerUtils
     * @return String
     */
    public static String getLastUrl(String strUrl) {
        return strUrl.substring(strUrl.lastIndexOf("/")+1);
    }

    /**
     * 获得request内的客户端ip
     * @author eric
     * @date 2017年5月17日上午10:58:57
     * @param @param request
     * @return 用户ip
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(ip!=null && !"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(ip!=null && !"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 将字符串数字元转化为分
     * @author eric
     * @date 2017年5月26日上午11:21:49
     * @param @param string 数字元
     * @return long 分
     */
    public static long yuanToCents(String yuan) {
        return (long)(Double.parseDouble(yuan)*100);
    }

    /**
     * 将数字类型的元转化成分
     * @author eric
     * @date 2017年6月19日下午1:38:59
     * @param yuan 元
     * @return 分
     */
    public static long yuanToCents(double yuan) {
        return (long) (yuan*100);
    }

    /**
     * 将字符串分转换成元
     * @author eric
     * @date 2017年6月19日下午2:25:10
     * @param cents 字符串分
     * @return 字符串元
     */
    public static String centsToYuan(String cents) {
        double d = Double.parseDouble(cents)/100;
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }

    /**
     * 将数字分转换成元
     * @author eric
     * @date 2017年6月19日下午2:23:36
     * @param cents 数字分
     * @return 数字元
     */
    public static double centsToYuan(long cents) {
        double d = (double)cents/100;
        DecimalFormat df = new java.text.DecimalFormat("#.00");
        return Double.parseDouble(df.format(d));
    }

    /**
     * 返回一个在max与min之间的伪随机数
     * @author eric
     * @date 2017年6月19日下午1:48:31
     * @param max 随机数的最大区间（包含）
     * @param min 随机数的最小区间（包含）
     * @return 伪随机数
     */
    public static Long randomPseudo(long max, long min){
        return (long)(Math.random()*((max+1)-min))+min;
    }

    /**
     * 将数字补齐数量
     * @author eric
     * @date 2017年6月27日下午7:12:20
     * @param quantity 需要补齐数字的位数
     * @param number 需要补的数字
     * @return 补齐后的值
     */
    public static String numericBeforeRepairZero(short quantity, long number){
        StringBuffer format = new StringBuffer();
        format.append("%0");
        format.append(quantity);
        format.append("d");
        return String.format(format.toString(), number);
    }

    /**
     * 验证手机号码是否正确
     * @author eric
     * @date 2017年10月20日下午4:51:51
     * @param mobilephone 手机号码
     * @return  true:正确
     *         false:错误
     */
    public static boolean isMobilephone(String mobilephone){
        Pattern p = Pattern.compile(
                "^1[3|4|5|7|8][0-9]{9}$"
                //"^((13[0-9])|(15[0-9])|(14[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$"
        );
        Matcher m = p.matcher(mobilephone);
        return m.matches();
    }

    /**
     * 验证e-mail格式是否正确
     * @author eric
     * @date 2017年10月20日下午6:01:37
     * @param email email格式
     * @return  true：正确
     *         false：错误
     */
    public static boolean isEmail(String email){
        Pattern p = Pattern.compile("[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证电话号码是否正确
     * @author Aran
     * @date 2018年2月11日下午4:51:51
     * @param telephone 电话号码
     * @return  true:正确
     *         false:错误
     */
    public static boolean isTelephone(String telephone){
        Pattern p = Pattern.compile(
                "^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}$"
                //"^0[1-9](\\d{1,2}\\-?)\\d{7,8}"
        );
        Matcher m = p.matcher(telephone);
        return m.matches();
    }

    /**
     * 验证是否为汉字
     * @author Aran
     * @date 2018年2月11日下午4:51:51
     * @param chinese 汉字字符串
     * @return  true:正确
     *         false:错误
     */
    public static boolean isChinese(String chinese){
        Pattern p = Pattern.compile(
                "^[\\u4e00-\\u9fa5]+$"
        );
        Matcher m = p.matcher(chinese);
        return m.matches();
    }

    /**
     * 验证是否包含汉字
     * @author Aran
     * @date 2018年2月11日下午4:51:51
     * @param chinese 汉字字符串
     * @return  true:正确
     *         false:错误
     */
    public static boolean isIncludeChinese(String chinese){
        Pattern p = Pattern.compile(
                "^[^\\u4e00-\\u9fa5]+$"
        );
        Matcher m = p.matcher(chinese);
        return !m.matches();
    }

    /**
     * 验证是否为传真
     * @author Aran
     * @date 2018年2月24日下午4:51:51
     * @param fax 传真
     * @return  true:正确
     *         false:错误
     */
    public static boolean isFax(String fax){
        Pattern p = Pattern.compile(
                "^((0\\d{2,3}-)?\\d{7,8})$"
        );
        Matcher m = p.matcher(fax);
        return m.matches();
    }

    /**
     * 验证是否为邮编
     * @author Aran
     * @date 2018年2月24日下午4:51:51
     * @param postcode 邮编
     * @return  true:正确
     *         false:错误
     */
    public static boolean isPostcode(String postcode){
        Pattern p = Pattern.compile(
                "[1-9]\\d{5}"
        );
        Matcher m = p.matcher(postcode);
        return m.matches();
    }

    /**
     * 验证是否为英文
     * @author Aran
     * @date 2018年2月24日下午4:51:51
     * @param english 英文
     * @return  true:正确
     *         false:错误
     */
    public static boolean isEnglish(String english){
        Pattern p = Pattern.compile(
                "^[A-Za-z]+$"
        );
        Matcher m = p.matcher(english);
        return m.matches();
    }
    /**
     * 验证是否为英文和数字
     * @author Aran
     * @date 2018年2月24日下午4:51:51
     * @param english 英文
     * @return  true:正确
     *         false:错误
     */
    public static boolean isEnglishAndNumber(String english){
        Pattern p = Pattern.compile(
                "^[A-Za-z0-9]+$"
        );
        Matcher m = p.matcher(english);
        return m.matches();
    }
    /**
     * 验证是否为金额
     * @author Aran
     * @date 2018年2月24日下午4:51:51
     * @param money 金额
     * @return  true:正确
     *         false:错误
     */
    public static boolean isMoney(BigDecimal money){
        Pattern p = Pattern.compile(
                "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"
        );
        Matcher m = p.matcher(money+"");
        return m.matches();
    }

    /**
     * 验证是否首位为数字
     * @author Aran
     * @date 2018年2月24日下午4:51:51
     * @param firstNumber
     * @return  true:正确
     *         false:错误
     */
    public static boolean isFirstNumber(String firstNumber){
        Pattern p = Pattern.compile(
                "^([A-Za-z])\\w+$"
        );
        Matcher m = p.matcher(firstNumber);
        return m.matches();
    }

    /**
     * 验证是否为ip
     * @author Aran
     * @date 2018年2月24日下午4:51:51
     * @param ip
     * @return  true:正确
     *         false:错误
     */
    public static boolean isIp(String ip){
        Pattern pv4 = Pattern.compile(
                "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$"
        );
        Matcher m = pv4.matcher(ip);
        if(m.matches()){
            return m.matches();
        }

        Pattern pv6s = Pattern.compile(
                "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$"
        );
        Matcher mv6s = pv6s.matcher(ip);
        if(mv6s.matches()){
            return mv6s.matches();
        }

        Pattern pv6h = Pattern.compile(
                "^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$"
        );
        Matcher mv6h = pv6h.matcher(ip);
        return mv6h.matches();
    }

    /**
     * 从string数据获取前面数字形成的BigDecimal数字
     * <br>"12包/箱"-->>12
     * <br>"12.5g/包"-->>12.5
     * @Descrption
     * @author yeJingYang
     * @date 2019年4月2日上午9:33:09
     * @param strBigDecimal 要
     * @return
     */
    public static BigDecimal getBigDecimalFromString(String strBigDecimal){
        String[] strings = strBigDecimal.split("");
        StringBuffer strb = new StringBuffer();
        for (String string : strings) {
            if (StringUtils.isNumeric(string) || ".".equals(string)) {
                strb.append(string);
            } else {
                break;
            }
        }
        return new BigDecimal(strb.toString());
    }

    /**
     * 返回总拣编码
     * @return
     */
    public static String getTotalNum(){
        StringBuffer totalNum = new StringBuffer("WCS");
        totalNum.append(DateUtils.getDate("yyMMdd"));
        totalNum.append(getRandom(4));

        return totalNum.toString();
    }
}
