package com.student.student_base_project.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The code contains Chinese because it contains Chinese string processing to get the Chinese API date
 */

public class StringUtils {

    public static boolean isEmpty(TextView textView) {
        return TextUtils.isEmpty(textView.getText().toString());
    }

    public static boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }



    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }


    /**
     * dp转换px
     **/
    public static int dip2px(Context context, double dpValue) {
        try {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        } catch (Exception ex) {

        }
        return 0;
    }

    /****
     * 将List集合转成String 并以","号分割
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List list, String separator) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /****
     * 将String 转成list
     * @param str
     * @return
     */
    public static List<String> stringsToList(String str) {
        String[] strings = str.split(",");
        return Arrays.asList(strings);
    }

    /***
     * string转成list
     */
    public static List<String> strToList(String str, String split) {
        String[] strings = str.split(split);
        return Arrays.asList(strings);
    }

    /***
     * 截取空格前的字符串
     * @param string
     * @return
     */
    public static String stringSpacingToString(String string) {
        if (!isEmpty(string)) {
            return string.substring(0, string.indexOf(" "));
        }
        return "";
    }

    /***
     * 截取空格后的字符串
     */
    public static String stringSpcingToStr(String string) {
        if (!isEmpty(string)) {
            return string.substring(string.indexOf(" "), string.length());
        }
        return "";
    }

    /***
     * 截取之前的字符
     * @param string
     * @param chart
     * @return
     */
    public static String stringSpacingToString(String string, String chart) {
        if (!isEmpty(string)) {
            return string.substring(0, string.indexOf(chart));
        }
        return "";
    }

    /****
     * 获取/1000 保留两位小数
     * @return
     */
    public static String getFloat(int num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float fl = (float) num / 1000;
        String dist = decimalFormat.format(fl);
        return dist + "km";
    }

    public static String getMultiplyDouble(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        BigDecimal bigDecimal = new BigDecimal(num);
        BigDecimal bigDecima2 = new BigDecimal(10000);
        BigDecimal bit = bigDecimal.multiply(bigDecima2);
        return decimalFormat.format(bit.doubleValue());
    }

    /****
     * 获取/1000 保留两位小数
     * @return
     */
    public static String getFloat(float num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        float fl = num / 1000;
        String dist = decimalFormat.format(fl);
        return dist + "km";
    }

    /****
     * 获取/1000 保留四位小数
     * @param num2 被除数
     * @return
     */
    public static String getFloat(int num, int num2) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        float fl = (float) num / num2;
        String dist = decimalFormat.format(fl);
        return dist + "";
    }

    /****
     *  保留几位小数
     * @param num 除数
     * @param num2 被除数
     * @param decimal 保留几位小数
     * @return
     */
    public static String getFloat(int num, int num2, String decimal) {
        DecimalFormat decimalFormat = new DecimalFormat(decimal);
        float fl = (float) num / num2;
        String numValue = decimalFormat.format(fl);
        return numValue + "";
    }

    /****
     *  保留几位小数
     * @param num 除数
     * @param num2 被除数
     * @param decimal 保留几位小数
     * @return
     */
    public static String getFloat(double num, int num2, String decimal) {
        DecimalFormat decimalFormat = new DecimalFormat(decimal);
        double fl = num / num2;
        String numValue = decimalFormat.format(fl);
        return numValue + "";
    }

    /******
     * 保留两位小数
     */
    public static String getDouble(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String strNum = decimalFormat.format(num);
        return strNum;
    }


    /******
     * 保留两位小数
     */
    public static double getAddDouble(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.add(b2).doubleValue();
    }

    /****
     * 获取有效期
     * @param endTime  结束时间
     * @return 22 这种格式
     */
    public static String getValidDate(String endTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date beginTime = simpleDateFormat.parse(getCurrentTime()); //当前时间
            Date enTime = simpleDateFormat.parse(endTime);
            long diff = (enTime.getTime() - beginTime.getTime()) / (86400 * 1000);  //返回有效期
            return Long.toString(diff);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /*****
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    /***
     * 获取当前月
     */
    public static String getCurrentMonthOfYear() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        String time = format.format(date);
        return time;
    }

    /***
     * 需要给的日期往后推多少天
     * @param d  需要添加的日期
     * @param day 添加的天数
     * @return 添加后的日期
     * @throws ParseException
     */
    public static Date addDate(Date d, long day) throws ParseException {
        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);
    }

    /****
     * 获取推迟的天数 转成string
     * @param time 用户给的起止时间
     * @param day 添加的天数
     * @return 日期string   格式：2017-07-06
     */
    public static String getDelayDate(String time, long day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(time);
            Date newDate = addDate(date, day);
            String newDa = sdf.format(newDate);
            return newDa;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /***
     * 验证密码是否是纯数字
     * @param txt
     */
    public static boolean verifyPwdIsTrue(String txt) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(txt);
        if (m.matches()) { //纯数字
            return true;
        }
        p = Pattern.compile("[a-zA-Z]");
        m = p.matcher(txt);
        Log.d("Dong", "");
        if (m.matches()) { //英文字母
            return false;
        }
        p = Pattern.compile("[\u4e00-\u9fa5]");
        m = p.matcher(txt);
        if (m.matches()) { //中文
            return false;
        }
        return false;
    }

    /***
     * 验证密码是否包含字母
     * @param txt
     */
    public static boolean isContainLetter(String txt) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        return txt.matches(regex);
    }

    /****
     * 去除字符串的最后一个字符
     * @param text
     * @return
     */
    public static String deleteLastCharacter(String text) {
        return text.substring(0, text.length() - 1);
    }

    /****
     * 计算两个整数的整数百分比
     * @param num1
     * @param num2
     * @return
     */
    public static int calcuPercent(int num1, int num2) {
        //创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        //保留0位小数
        numberFormat.setMaximumFractionDigits(0);
        String num = numberFormat.format((float) num1 / (float) num2 * 100);
        return Integer.parseInt(num);
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "," + hour + "," + min + "," + sec;
    }

    /****
     * 获取有效期时间 精准到毫秒
     * @param endDate 有效期结束日
     * @return
     */
    public static long getValidityTime(String endDate) {
        //获取当前时间的毫秒值
        long currentTime = System.currentTimeMillis();
        long endTime = strDateToLong(endDate);
        long validTime = endTime - currentTime;
        Log.d("Dong", "currentTime = " + currentTime + " ,, " + endTime + ",," + validTime);
        return validTime;
    }

    /***
     * 将字符串转成毫秒数
     * @param strTime
     * @return
     */
    public static long strDateToLong(String strTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(strTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将字符串转成毫秒数
     *
     * @param strTime
     * @param format  yyyy-MM-dd HH:mm:ss | yyyy-MM-dd |  HH:mm:ss
     * @return
     */
    public static long strDateToLong(String strTime, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(strTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /****
     * 两个double数相加
     * @param dou1
     * @param dou2
     * @return
     */
    public static Double doubleAdd(double dou1, double dou2) {
        DecimalFormat df = new DecimalFormat("0.0000");
        BigDecimal b1 = new BigDecimal(df.format(dou1));
        BigDecimal b2 = new BigDecimal(df.format(dou2));
        double doubleValue = b1.add(b2).doubleValue();
        return doubleValue;
    }


    /****
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /****
     * 获取错误的code码
     * @param e
     * @return
     */
    public static String getErrorCode(Throwable e) {
        if (e != null) {
            String message = e.getMessage();
            if (message.contains("--")) {
                if (!StringUtils.isEmpty(message)) {
                    String[] strCode = message.split("--");
                    if (strCode.length < 0) {
                        return "";
                    } else {
                        String str = strCode[2];
                        return str;
                    }
                }
            }
        }
        return "";
    }

    public static String getErrorMessage(Throwable e) {
        if (e != null) {
            String message = e.getMessage();
            if (message.contains("--")) {
                if (!StringUtils.isEmpty(message)) {
                    String[] strCode = message.split("--");
                    if (strCode.length < 0) {
                        return "";
                    } else {
                        String str = strCode[1];
                        return str;
                    }
                }
            }
        }
        return "";
    }

    /***
     * 判断文件是否存在
     * @param path
     * @return
     */
    public static boolean isFileExit(String path) {
        if (path == null) {
            return false;
        }
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isLastRow(int position, int spanCount, int total) {
        int lastRowFirstColumn =
                total - (total % spanCount == 0 ? spanCount : total % spanCount) + 1;
        return position >= lastRowFirstColumn;
    }

    public static boolean isLastColumn(int position, int spanCount, int total) {
        if (total < spanCount) {
            spanCount = total;
        }
        return position % spanCount == 0;
    }


    public static char getCharInString(String target, int index) {
        if (target == null || target == "") {
            return 0;
        }
        if (index < 0) {
            return 0;
        }
        String[] targets = target.split("_");
        String result = "";
        for (int i = 0; i < targets.length; ++i) {
            if (i % 2 == 0) {
                result += getStringByAcount(targets[i], targets[i + 1]);
            }
        }
        int length = result.length();
        if (index >= length) {
            return 0;
        }
        char resultChar = result.charAt(index);
        return resultChar;
    }

    public static String getStringByAcount(String s, String acount) {
        String result = "";
        int all = 0;
        try {
            all = Integer.parseInt(acount);
        } catch (NumberFormatException e) {
            System.out.println("numberFormat error");
        }
        for (int i = 0; i < all; i++) {
            result = result + s;
        }
        return result;
    }



    public static String getTimeStringE(String stringTime) {
        SimpleDateFormat format = new SimpleDateFormat("E");
        String time = format.format(new Date(getTimeLong("yyyy-MM-dd", stringTime)));
        return time;
    }

    public static Long getTimeLong(String filter, String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(filter);
            Date dateTime = format.parse(date);
            return dateTime.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0L;
    }


    public static final String getTimeString(long time, String filter) {
        SimpleDateFormat format = new SimpleDateFormat(filter);
        return format.format(new Date(time));
    }



    public static int getExactMinu(String remindContent) {
        if (remindContent.contains("不提醒")) {
            return 0;
        }
        if (remindContent.contains("分钟")) {
            int minuNum = setNum(remindContent);
            return minuNum;
        }
        if (remindContent.contains("小时")) {
            int num = setNum(remindContent);
            return num * 60;
        }
        if (remindContent.contains("天")) {
            int dayNum = setNum_1(remindContent);
            return dayNum * 24 * 60;
        }
        if (remindContent.contains("周")) {
            int weekNum = setNum_1(remindContent);
            return weekNum * 7 * 24 * 60;

        }
        return 0;
    }

    private static int setNum(String remindContent) {
        String substring = remindContent.substring(2, remindContent.length() - 2);
        int minuNum = Integer.parseInt(substring);
        return minuNum;
    }

    private static int setNum_1(String remindContent) {
        String substring = remindContent.substring(2, remindContent.length() - 1);
        int minuNum = Integer.parseInt(substring);
        return minuNum;
    }

    public static String matchWeek(String weekDay) {
        if (weekDay.contains("一")) {
            return "MONDAY";
        }
        if (weekDay.contains("二")) {
            return "TUESDAY";
        }
        if (weekDay.contains("三")) {
            return "WEDNESDAY";
        }
        if (weekDay.contains("四")) {
            return "THURSDAY";
        }
        if (weekDay.contains("五")) {
            return "FRIDAY";
        }
        if (weekDay.contains("六")) {
            return "SATURDAY";
        }
        if (weekDay.contains("日")) {
            return "SUNDAY";
        }
        return "";
    }

    public static String getRermingTimeString(int minusCount) {
        int i = 0;
        if (minusCount > 60 * 24 * 7) {
            i = minusCount / 60 * 24 * 7;
            return "提前" + i + "周";
        } else if (minusCount > 60 * 24) {
            i = minusCount / 60 * 24;
            return "提前" + i + "天";
        } else if (minusCount > 60) {
            i = minusCount / 60;
            return "提前" + i + "小时";
        } else {
            return "提前" + minusCount + "分钟";

        }
    }

    /**
     *
     *
     * @param phoneNums
     */
    public static boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11) && isMobileNO(phoneNums)) {
            return true;
        }
        return false;
    }

    /**
     *
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     *
     */
    public static boolean isMobileNO(String mobileNums) {

        String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    public static long dateTomillis(String date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            if (!TextUtils.isEmpty(date)) {

                long time = dateformat.parse(date).getTime();
                System.out.println(time);
                return time;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     *
     *
     * @param map
     * @return String
     */
    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }

    /***
     *
     */
    public static <T> String listToJson(List<T> list) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list);
        return jsonStr;
    }

    /***
     *
     * @param clz
     * @return
     */
    public static <T> String clzToJson(T clz) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(clz);
        return jsonStr;
    }


    /***
     *
     * @param str
     * @return
     */
    public static String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /***
     *Remove space \t, carriage return \n, newline \r, TAB character \t from string
     * @param src
     * @return
     */
    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }

    /***
     * Returns whether the string is longer than 4 characters
     */
    public static boolean checkCharacterLegal(String string) {
        String str = URLDecoderString(string);
        int length = str.getBytes().length;
        Log.d("Dong", "" + length);
        if (length > 4) {
            return true;
        }
        return false;
    }

    /**
     *
     */
    public static int getCurrentAge(Date birthday) {

        Calendar curr = Calendar.getInstance();

        Calendar born = Calendar.getInstance();
        born.setTime(birthday);

        int age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR);
        if (age <= 0) {
            return 0;
        }

        int currMonth = curr.get(Calendar.MONTH);
        int currDay = curr.get(Calendar.DAY_OF_MONTH);
        int bornMonth = born.get(Calendar.MONTH);
        int bornDay = born.get(Calendar.DAY_OF_MONTH);
        if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
            age--;
        }
        return age < 0 ? 0 : age;
    }

    /***
     *
     */
    public static int getCurrentAge2(Date birthday, Date policyApplyTime) {

        Calendar curr = Calendar.getInstance();
        curr.setTime(policyApplyTime);


        Calendar born = Calendar.getInstance();
        born.setTime(birthday);

        int age = born.get(Calendar.YEAR) - curr.get(Calendar.YEAR);
        if (age <= 0) {
            return 0;
        }

        int currMonth = curr.get(Calendar.MONTH);
        int currDay = curr.get(Calendar.DAY_OF_MONTH);
        int bornMonth = born.get(Calendar.MONTH);
        int bornDay = born.get(Calendar.DAY_OF_MONTH);
        if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
            age--;
        }
        return age < 0 ? 0 : age;
    }


    /***
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *
     */
    public static boolean isContainChar(String string) {
        String s = "0,1,2,3,4,5,6,7,8,9,一,二,三,四,五,六,七,八,九,十,零,壹,贰,叁,肆,伍,陆,柒,捌,玖,拾";
        List<String> list = strToList(s, ",");
        List<String> inputList = Arrays.asList(string.split(""));
        for (int i = 0; i < inputList.size(); i++) {
            String inpuS = inputList.get(i);
            boolean s1 = list.contains(inpuS);
            if (s1) {
                return s1;
            }
        }
        return false;
    }

    /***
     *
     */
    public static boolean isContainKeyword(String string) {
        String s = "组,队,号,室";
        List<String> list = strToList(s, ",");
        List<String> inputList = Arrays.asList(string.split(""));
        for (int i = 0; i < inputList.size(); i++) {
            String inpuS = inputList.get(i);
            boolean s1 = list.contains(inpuS);
            if (s1) {
                return s1;
            }
        }
        return false;
    }

    /***
     *
     * @param ss
     * @return
     */
    public static int chinaWordNum(String ss) {
        if (isEmpty(ss)) {
            return 0;
        }
        String regex = "[\u4e00-\u9fa5]";
        return ss.length() - ss.replaceAll(regex, "").length();
    }

    /****
     *
     */
    public static String getBirthInfo(String idcard) {
        String strBirth = idcard.substring(6, 14);
        return strBirth.substring(0, 4) + "-" + strBirth.substring(4, 6) + "-" + strBirth.substring(6);
    }

    /****
     *
     */
    public static String getAccountBookBirthInfo(String idcard) {
        return idcard.substring(0, 4) + "-" + idcard.substring(4, 6) + "-" + idcard.substring(6);
    }

    /***
     * 根据身份证号获取性别
     */
    public static String getSexByIdcard(String idcard) {
        if (idcard.length() != 18) {
            return "身份证格式不正确";
        }
        if (Integer.parseInt(idcard.substring(16).substring(0, 1)) % 2 == 0) {
            return "女";
        } else {
            return "男";
        }
    }

    /***
     *
     * @param time
     * @param  year +多少年
     * @return
     */
    public static String getYearFrontDay(String time, int year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        //
        calendar.add(Calendar.YEAR, year);
        int day = calendar.get(Calendar.DATE);
        //
        calendar.set(Calendar.DATE, day - 1);

        String lastDay = sdf.format(calendar.getTime());
        return lastDay;
    }

    /**
     *
     *
     * @param email
     * @return
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /***
     *
     */
    public static String getChinaCharacter(String str) {
        String reg = "[^\u4e00-\u9fa5]";
        str = str.replaceAll(reg, " ");
        return str;
    }

    /***
     *
     * @return
     */
    public static String getCutoutString(String str, String keyword, int end) {
        int begin = str.indexOf(keyword);
        return str.substring(begin, begin + end);
    }


    /***
     *  EditText
     * @param activity
     * @param editText
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /* if the time is less than 10, process */
    public static String getDateString(int num) {
        String str = null;
        if (num < 10) {
            str = "0" + num;

        } else {
            str = num + "";
        }
        return str;
    }

    /*
     *
     */
    public static String replaceChinaChar(int i) {
        if (i == 1) {
            return "一";
        } else if (i == 2) {
            return "二";
        } else if (i == 3) {
            return "三";
        }
        return "三";
    }

    /***
     *
     * @param list
     * @param sort
     * @return
     */
    public static List removeListElement(int[] list, int sort) {
        List newList = new ArrayList();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != sort) {
                newList.add(list[i]);
            }
        }
        return newList;
    }

    /***
     *
     */
    public static String getJointStr(String policyPeriod) {
        String newPolicyPeriod = "";
        if (StringUtils.isEmpty(policyPeriod)) {
            return "";
        }
        if (policyPeriod.contains("@")) {
            newPolicyPeriod = policyPeriod.replace("@", "");
        } else {
            newPolicyPeriod = policyPeriod;
        }
        if (newPolicyPeriod.contains("105")) {
            return "保至终身";
        }
        return "保至" + newPolicyPeriod + "周岁";
    }


    /***

     */
    public static int getFamilyRelationTypeId(String strRelationToMainInsured, int insuranceSex, int recognizeeSex) {
        if ("本人".equals(strRelationToMainInsured)) {
            return 0;
        } else if ("夫妻".equals(strRelationToMainInsured) || "配偶".equals(strRelationToMainInsured)) {
            return 7;
        } else if ("父母".equals(strRelationToMainInsured)) {
            if (recognizeeSex == 0) {
                return 1;
            } else {
                return 2;
            }
        } else if ("岳父".equals(strRelationToMainInsured)) {
            if (insuranceSex == 1) {
                return 0;
            } else {
                return 3;
            }
        } else if ("岳母".equals(strRelationToMainInsured)) {
            if (insuranceSex == 1) {
                return 0;
            } else {
                return 4;
            }
        } else if ("公公".equals(strRelationToMainInsured)) {
            if (insuranceSex == 0) {
                return 0;
            } else {
                return 5;
            }
        } else if ("婆婆".equals(strRelationToMainInsured)) {
            if (insuranceSex == 0) {
                return 0;
            } else {
                return 6;
            }
        } else if ("儿女".equals(strRelationToMainInsured)) {
            if (recognizeeSex == 0) {
                return 8;
            } else {
                return 9;
            }
        } else if ("兄弟姐妹".equals(strRelationToMainInsured)) {
            if (recognizeeSex == 0) {
                return 10;
            } else {
                return 11;
            }
        } else if ("表兄弟姐妹".equals(strRelationToMainInsured)) {
            if (recognizeeSex == 0) {
                return 12;
            } else {
                return 13;
            }
        }
        return 0;
    }

    /***
     *
     * @param context
     * @return
     */
    public static String getWifiBissid(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Service.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String bssid = info.getBSSID();
        return bssid;
    }

    /***
     *
     */
    public static String getNetWorkTime() {
        try {
            URL url = new URL("http://www.baidu.com/");
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }


    public static String changeBankCard(String cardNumber) {
        String regex = "(\\w{4})(.*)(\\w{4})";
        Matcher m = Pattern.compile(regex).matcher(cardNumber);
        if (m.find()) {
            String rep = m.group(2);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rep.length(); i++) {
                sb.append("*");
            }
            return cardNumber.replaceAll(rep, sb.toString());

        }
        return cardNumber;
    }

    /***
     *
     */
    public static String getWifiName(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Service.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String bssid = info.getSSID();
        return bssid;
    }

    /***
     *
     * @return
     */
    public static String getDateDetail(String currentNetworkDate, String date) {
        if (StringUtils.isEmpty(currentNetworkDate) || StringUtils.isEmpty(date)) {
            return "";
        }
        if (strDateToLong(StringUtils.stringSpacingToString(currentNetworkDate), "yyyy-MM-dd") > strDateToLong(StringUtils.stringSpacingToString(date), "yyyy-MM-dd")) {
            return "昨日";
        } else if (strDateToLong(StringUtils.stringSpacingToString(currentNetworkDate), "yyyy-MM-dd") < strDateToLong(StringUtils.stringSpacingToString(date), "yyyy-MM-dd")) {
            return "次日";
        } else {
            return "";
        }
    }

    /***
     *
     * @param ymthon
     * @return
     */
    public static String getFormatYMonth(String ymthon) {
        if (StringUtils.isEmpty(ymthon)) {
            return "";
        }
        if (ymthon.contains("年") && ymthon.contains("月")) {
            return ymthon.replace("年", "-").replace("月", "");
        }
        return "";
    }


    /*
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd HH:mm");
        return format.format(new Date());
    }


    /***
     *
     * @param value
     * @param insuranceType
     * @return
     */
    public static String getPayFeesPeriodOrSafeGuardDeadLine(String value, int insuranceType) {
        if (value.contains("@")) {
            List<String> list = StringUtils.strToList(value, "@");
            if (insuranceType == 0) {
                if (list.get(0).contains("105")) {
                    return "至终身";
                }
                return "至" + list.get(0) + "周岁";
            } else {
                if (list.size() > 1) {
                    if (list.get(1).contains("105")) {
                        return "至终身";
                    }
                    return "至" + list.get(1) + "周岁";
                } else {
                    if (list.get(0).contains("105")) {
                        return "至终身";
                    }
                    return "至" + list.get(0) + "周岁";
                }
            }
        }
        return "";
    }

    /***
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double add(double num1, double num2) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        BigDecimal bigDecimal = new BigDecimal(num1);
        BigDecimal bigDecima2 = new BigDecimal(num2);
        return Double.parseDouble(decimalFormat.format(bigDecimal.add(bigDecima2)));
    }

    /***
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double subtract(double num1, double num2) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        BigDecimal bigDecimal = new BigDecimal(num1);
        BigDecimal bigDecima2 = new BigDecimal(num2);
        return Double.parseDouble(decimalFormat.format(bigDecimal.subtract(bigDecima2)));
    }

    /***
     *
     * @param d
     * @return
     */
    public static String doubleFormat(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        return decimalFormat.format(d);
    }
}
