package com.gdsig.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * String 工具类
 *
 * @author huihu
 * @date 2021-08-25
 */
public class StringUtil {

    /**
     * 获取非 null 值
     *
     * @param val 任意对象
     * @return 对象字符串
     */
    public static String getValueNotNull(Object val) {
        if (val == null) {
            return "";
        }
        return val.toString();
    }

    /**
     * 获取int值，非Int默认返回0
     *
     * @param obj 对象
     * @return int
     */
    public static int getIntValue(Object obj) {
        if (obj == null || "".equals(obj.toString())) {
            return 0;
        }

        try {
            double dValue = Double.parseDouble(obj.toString());
            return (int) dValue;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取int值，非Int默认返回0
     *
     * @param obj 对象
     * @return int
     */
    public static Integer getInteger(Object obj) {
        if (obj == null || "".equals(obj.toString())) {
            return null;
        }

        try {
            double dValue = Double.parseDouble(obj.toString());
            return (int) dValue;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取int值
     *
     * @param obj 对象
     * @param def 对象为空时，默认值
     * @return int
     */
    public static int getIntValue(Object obj, int def) {
        if (obj == null || "".equals(obj.toString())) {
            return def;
        }

        try {
            double dValue = Double.parseDouble(obj.toString());
            return (int) dValue;
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 获取double值
     *
     * @param obj 对象
     * @return double | 0
     */
    public static Double getDouble(Object obj) {
        if (obj == null || "".equals(obj.toString())) {
            return null;
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取double值
     *
     * @param obj 对象
     * @return double | 0
     */
    public static double getDoubleValue(Object obj) {
        return getDoubleValue(obj, 0D);
    }

    /**
     * 获取double值
     *
     * @param obj 对象
     * @param def 对象为空时，默认值
     * @return double
     */
    public static double getDoubleValue(Object obj, double def) {
        if (obj == null || "".equals(obj.toString())) {
            return def;
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 获取double值
     *
     * @param obj 对象
     * @param def 对象为空时，默认值
     * @return double
     */
    public static double defaultDoubleIfBlank(Object obj, Object def) {
        if (obj == null || "".equals(obj.toString())) {
            return getDoubleValue(def);
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return getDoubleValue(def);
        }
    }

    /**
     * object 转 List JSONObject
     *
     * @param json Object(String)
     * @return {@code Result<List<JSONObject>>}
     */
    public static List<JSONObject> obj2ListJsonObject(Object json) {
        try {
            return JSONArray.parseArray(json.toString(), JSONObject.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * string装 JSONObject
     *
     * @param json string
     * @return {@code Result<JSONObject>}
     */
    public static JSONObject obj2JsonObject(String json) {
        try {
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    /**
     * object 转 List String
     *
     * @param json Object(String)
     * @return {@code Result<List<String>>}
     */
    public static List<String> obj2ListString(Object json) {
        List<String> list;
        try {
            list = JSONArray.parseArray(json.toString(), String.class);
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return list;
    }

    /**
     * object 转 List String
     *
     * @param json Object(String)
     * @return {@code Result<List<String>>}
     */
    public static List<Integer> obj2ListInteger(Object json) {
        List<Integer> list;
        try {
            list = JSONArray.parseArray(json.toString(), Integer.class);
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return list;
    }

    /**
     * 格式化Double2位小数，整数则取得小数点
     *
     * @param d Double值
     * @return 字符串
     */
    public static String toString2Dim(Double d) {
        if (d == null) {
            return "";
        }
        DecimalFormat df = new DecimalFormat("#.##");
        Double dbl = Double.parseDouble(String.format("%.2f", d));
        return df.format(dbl);
    }

    /**
     * 获取随机数
     *
     * @param prev  随机数前缀
     * @param count 随机数多少位
     * @return 随机数拼接后字符串
     */
    public static String getRandomNumeric(String prev, int count) {
        StringBuilder sbRandom = new StringBuilder();

        if (prev != null) {
            sbRandom.append(prev);
        }
        sbRandom.append(RandomStringUtils.randomNumeric(count));

        return sbRandom.toString();
    }

    public static String getRandomNum(int count) {
        int maxNum = 36;
        int i;
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder pwd = new StringBuilder();
        Random r = new Random();
        while (pwd.length() < count) {
            i = Math.abs(r.nextInt(maxNum));
            pwd.append(str[i]);
        }
        return pwd.toString();
    }

    public static <T> T defaultIfEmpty(T obj1, T obj2) {
        return obj1 != null ? obj1 : obj2;
    }

    public static Object defaultIfBlank(Object obj1, Object obj2) {
        return obj1 == null || StringUtils.isBlank(obj1.toString()) ? obj2 : obj1;
    }

    /**
     * 计算精度
     *
     * @param value        value
     * @param scale        scale
     * @param roundingMode ,默认是-1
     * @return double
     */
    public static double round(Double value, int scale, int roundingMode) {
        if (value == null) {
            return 0d;
        }
        if (roundingMode == -1) {
            roundingMode = BigDecimal.ROUND_HALF_UP;
        }
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        bigDecimal = bigDecimal.setScale(scale, roundingMode);
        double dResult = bigDecimal.doubleValue();
        bigDecimal = null;
        return dResult;
    }

    public static int double2IntValue(Double value) {
        if (value == null) {
            return 0;
        }
        return StringUtil.getIntValue(StringUtil.round(value, 2, -1));
    }

    public static double formatDouble1(Double d) {
        if (d == null) {
            return 0.0;
        }
        return round(d, 1, -1);
    }

    public static String getClientIp(HttpServletRequest req) {

        String ip = req.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 字符串是否相同，相同则返回，否则返回默认值
     *
     * @param origin   源字符串
     * @param valueEqu 与源字符串相等的字符串
     * @param def      默认值
     * @return 判定后的字符串
     */
    public static String equalsOr(String origin, String valueEqu, String def) {
        if (Objects.equals(origin, valueEqu)) {
            return origin;
        }
        return def;
    }

    /**
     * 如果字符串为null|空字符，返回默认值
     *
     * @param str 字符串
     * @param def 默认值
     * @return String
     */
    public static String defIfBlank(String str, String def) {
        return StringUtils.isBlank(StringUtils.trim(str)) ? def : str;
    }

    /**
     * 元转分
     *
     * @param yuan 元：double
     * @return 分：int
     */
    public static int toAmountFen(double yuan) {
        // 元转分，double
        double fenDbl = StringUtil.round(yuan * 100.0, 2, -1);
        return (int) fenDbl;
    }

    /**
     * 分转元
     *
     * @param fen 分：double
     * @return 分：int
     */
    public static double toAmountYuan(double fen) {
        return  StringUtil.round(fen / 100.0, 2, -1);
    }

    /**
     * 取字符串后几位，长度不足前面补0
     *
     * @param str  字符串
     * @param size 后几位
     * @return 固定长度字符串
     */
    public static String lastOrPreAppendZero(String str, int size) {
        return StringUtils.leftPad(StringUtils.substring(str, -size), size, "0");
    }

    /**
     * 平分数量：5/3 -> 2 2 1
     *
     * @param qty   总数量
     * @param size  平分个体数
     * @param index 索引
     * @return 平分后的数量
     */
    public static int distribute(int qty, int size, int index) {
        if (qty < 0 || size <= 0 || size <= index) {
            return 0;
        }
        // 只有一个，全部占有
        if (size == 1 && index == 0) {
            return qty;
        }
        // 获取平均数
        int perSize = (int) Math.ceil(1.0 * qty / size);
        // 第二个起，返回平均数
        if ((index + 1) < size) {
            return perSize;
        }
        // 最后一个，获得余数
        return perSize + qty - perSize * (index + 1);
    }

    /**
     * 前面的值不为字符串空，则拼接
     *
     * @param value 对象值
     * @param str   需拼接的字符串
     * @return 新的字符串
     */
    public static String appendIfNotBlank(Object value, String str) {
        if (value == null) {
            return null;
        }
        if (StringUtils.isNotBlank(value.toString())) {
            return value + str;
        }
        return "";
    }

    public static String getRequestBody(InputStream is){
        if (is == null){
            return null;
        }
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return new String(outSteam.toByteArray(), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {

    }
}
