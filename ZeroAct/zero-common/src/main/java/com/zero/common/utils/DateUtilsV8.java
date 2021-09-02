package com.zero.common.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtilsV8 {

    public static final Integer YEAR = 4;
    public static final Integer MONTH = 7;
    public static final Integer DAY = 10;

    /**
     * Description: 获取12个月份的列表
     *
     * @return
     * @author wangzhengxiang
     * @date 2018年12月20日 下午2:41:35
     */
    public static List<String> getMonthOfYear(int year) {
        List<String> ymList = new ArrayList<String>();
        YearMonth ym = YearMonth.of(year, 1);
        for (int i = 0; i < 12; i++) {
            ymList.add(ym.toString());
            ym = ym.plusMonths(1);
        }
        return ymList;
    }

    /**
     * Description: 获取最近一年的12个月份(含当月)
     *
     * @return
     * @author wangzhengxiang
     * @date 2018年12月20日 下午2:41:35
     */
    public static List<String> getMonthForNearlyYear() {
        List<String> ymList = new ArrayList<String>();
        YearMonth ym = YearMonth.now().minusYears(1l);
        for (int i = 0; i < 12; i++) {
            ym = ym.plusMonths(1);
            ymList.add(ym.toString());
        }
        return ymList;
    }

    /**
     * Description: 获取最近几个月(含当月)
     */
    public static List<String> getNearlyMonth(int num) {
        //建一个容器
        List<String> months = new ArrayList<>();
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        //调整到12个月以前
        calendar.add(Calendar.MONTH, -(num - 1));
        //循环12次获取12个月份
        for (int i = 0; i < num; i++) {
            //日历对象转为Date对象
            Date date = calendar.getTime();
            //将date转为字符串
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String dateStr = sdf.format(date);
            //向list集合中添加
            months.add(dateStr);
            //每次月份+1
            calendar.add(Calendar.MONTH, 1);
        }
        return months;
    }

    /**
     * Description: 返回当年月份的所有日期
     *
     * @return
     * @author wangzhengxiang
     * @date 2018年12月21日 下午4:03:02
     */
    public static List<String> getDayOfThisMonth() {
        List<String> dayList = getDayOfMonth(Year.now().getValue(), YearMonth.now().getMonth().getValue());
        return dayList;
    }

    /**
     * Description: 获取某个月份的天数列表
     *
     * @param year
     * @param month
     * @return
     * @author wangzhengxiang
     * @date 2018年12月27日 下午2:22:17
     */
    public static List<String> getDayOfMonth(int year, int month) {
        List<String> dayList = new ArrayList<String>();
        LocalDate baseDay = LocalDate.of(year, month, 1);
        int days = baseDay.lengthOfMonth();
        for (int i = 0; i < days; i++) {
            dayList.add(baseDay.toString());
            baseDay = baseDay.plusDays(1);
        }
        return dayList;
    }

    /**
     * Description: 获取指定日期间的月份
     *
     * @param begin
     * @param end
     * @return
     * @author wangzhengxiang
     * @date 2018年12月27日 下午2:22:17
     */
    public static List<String> getMonthsBetweenDate(String begin, String end) {
        List<String> ymList = new ArrayList<String>();
        try {
            YearMonth beginDate = YearMonth.parse(begin, DateTimeFormatter.ofPattern("yyyy-MM"));
            YearMonth endDate = YearMonth.parse(end, DateTimeFormatter.ofPattern("yyyy-MM"));
            Long months = Period.between(beginDate.atDay(1), endDate.atDay(1)).toTotalMonths();

            for (Long i = 0l; i <= months; i++) {
                ymList.add(beginDate.toString());
                beginDate = beginDate.plusMonths(1);
            }
            return ymList;

        } catch (Exception e) {
            System.out.println("日期格式错误" + e);
            return ymList;
        }

    }

    /**
     * 获取指定日期间的日期列表
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static List<LocalDate> getDaysList(LocalDate beginDate, LocalDate endDate) {
        List<LocalDate> mdList = new ArrayList<LocalDate>();
        try {
            Long days = endDate.toEpochDay() - beginDate.toEpochDay();
            for (int i = 0; i <= days; i++) {
                mdList.add(beginDate);
                beginDate = beginDate.plusDays(1);
            }
            return mdList;
        } catch (Exception e) {
            System.out.println("日期格式错误" + e);
            return mdList;
        }
    }

    /**
     * 获取指定日期间的日期列表
     *
     * @param dayOfWeek 周几
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static List<LocalDate> getDaysList(int dayOfWeek, LocalDate beginDate, LocalDate endDate) {

        List<LocalDate> mdList = new ArrayList<LocalDate>();
        try {
            int dw = beginDate.getDayOfWeek().getValue();
            int plusDays = 7 + dayOfWeek - dw;
            LocalDate firstDay = beginDate.plusDays(plusDays);
            do {
                mdList.add(firstDay);
                firstDay = firstDay.plusDays(7);
            } while (firstDay.isBefore(endDate.plusDays(1)));
            return mdList;
        } catch (Exception e) {
            System.out.println("日期格式错误" + e);
            return mdList;
        }

    }

    /**
     * Description: 查询两年间差几年
     *
     * @param begin
     * @param end
     * @return
     * @author wangzhengxiang
     * @date 2019年1月3日 下午4:07:32
     */
    public static List<String> getYearsBetweenYear(String begin, String end) {
        List<String> yList = new ArrayList<String>();
        try {
            Year beginDate = Year.parse(begin, DateTimeFormatter.ofPattern("yyyy"));
            Year endDate = Year.parse(end, DateTimeFormatter.ofPattern("yyyy"));
            int years = endDate.compareTo(beginDate);

            for (int i = 0; i <= years; i++) {
                yList.add(beginDate.toString());
                beginDate = beginDate.plusYears(1);
            }

            System.out.printf("开始年份%s与结束年份%s之间的相差%s年\n 年份列表为：%s\n", begin, end, years, yList);
            return yList;

        } catch (Exception e) {
            System.out.println("日期格式错误" + e);
            return getYearsBetweenYear("2018", Year.now().toString());
        }

    }

    /**
     * Description: 根据类别和开始结束时间，返回对应的日期列表
     *
     * @param dateType 日期类型：date,week,month，year
     * @param begin    开始时间
     * @param end      结束时间
     * @return
     * @author wangzhengxiang
     * @date 2019年1月3日 下午2:59:12
     */
/*
    public static List<String> getDateList(String dateType, String begin, String end) {
        List<String> dateList = new ArrayList<String>();
        switch (dateType) {
            case "date":
                dateList = (StringUtils.isEmpty((String) begin) || StringUtils.isEmpty((String) end)) ? getDayOfThisMonth().toString()
                        : getDaysBetweenDate(begin, end).toString();
                break;
            case "week":
                //TODO
                dateList = null;
                break;
            case "month":
                dateList = (StringUtils.isEmpty((String) begin) || StringUtils.isEmpty((String) end))
                        ? getMonthForNearlyYear()
                        : getMonthsBetweenDate(begin, end);
                break;
            case "year":
                dateList = (StringUtils.isEmpty((String) begin) || StringUtils.isEmpty((String) end))
                        ? getYearsBetweenYear("2018", Year.now().toString())
                        : getYearsBetweenYear(begin, end);

                break;
            default:
                dateList = null;
                break;
        }

        return dateList;
    }
*/


    /**
     * Description: 判断今天是否在某时间区间内
     *
     * @param begin
     * @param end
     * @return
     * @author wangzhengxiang
     * @date 2019年1月10日 上午11:40:20
     */
    public static Boolean isBelong(String begin, String end) {
        Boolean flag = false;
        LocalDate today = LocalDate.now();
        LocalDate beginDate = LocalDate.parse(begin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        flag = today.isAfter(beginDate.minusDays(1)) && today.isBefore(endDate.plusDays(1));

        System.out.printf("开始日期%s与结束日期%s是都包含今天%s ====== %s \n ", begin, end, today, flag);
        return flag;
    }


    /**
     * Description: 判断两个时间相差的秒数
     *
     * @param begin
     * @param end
     * @return
     * @author wangzhengxiang
     * @date 2019年1月18日 上午11:55:35
     */
    public static long compareTimes(String begin, String end) {
        LocalDateTime beginDate = LocalDateTime.parse(begin, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Duration duration = Duration.between(beginDate, endDate);
        System.out.println(duration.toMillis() / 1000);
        return duration.toMillis() / 1000;
    }


    /**
     * Description: 判断是否超出有效时间
     *
     * @param begin         开始时间
     * @param effectiveTime 有效时间（秒）
     * @return
     * @author wangzhengxiang
     * @date 2019年1月18日 下午1:19:27
     */
    public static Boolean isTimeOut(String begin, long effectiveTime) {
        Boolean flag = true;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long times = compareTimes(begin, df.format(LocalDateTime.now()));
        if (times < effectiveTime) {
            flag = false;
        }
        return flag;
    }


    public static void getToDayOfWeek() {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println(dayOfWeek);
    }


    /**
     * Description: 根据枚举和开始结束时间，返回对应的周期性日期列表
     *
     * @param frequency 周期类型类型：ActFrequencyType
     * @param begin     开始时间
     * @param end       结束时间
     * @return
     * @author wangzhengxiang
     * @date 2019年1月3日 下午2:59:12
     */
    public static List<LocalDate> getFrequencyDateList(int frequency, LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<LocalDate>();
        LocalDate startDate = begin;
        if (9 == frequency) {
            while (!end.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).equals(startDate)) {
                dateList.add(startDate);
                startDate = startDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
            }
        } else if (10 == frequency) {
            while (!LocalDate.of(startDate.getYear(), startDate.plusMonths(3).getMonth().firstMonthOfQuarter(), 1).equals(startDate)) {
                dateList.add(startDate);
                startDate = LocalDate.of(startDate.getYear(), startDate.plusMonths(3).getMonth().firstMonthOfQuarter(), 1);
            }
        } else if (11 == frequency) {
            while (!end.plusYears(1).with(TemporalAdjusters.firstDayOfYear()).equals(startDate.with(TemporalAdjusters.firstDayOfMonth()))) {
                dateList.add(startDate);
                startDate = startDate.plusYears(1).with(TemporalAdjusters.firstDayOfYear());
            }
        } else if (0 == frequency) {
            dateList.addAll(getDaysList(begin, end));
        } else if (8 == frequency) {
            dateList.add(startDate);
            dateList.addAll(getDaysList(DayOfWeek.MONDAY.getValue(), begin, end));
        } else {
            dateList.addAll(getDaysList(frequency, begin, end));
        }
        return dateList;
    }


    public static void main(String[] args) {
        LocalDate begin = LocalDate.parse("2021-08-01");
        LocalDate end = LocalDate.parse("2021-10-31");
        String[] dateTypes = new String[]{"1", "2"};
        //    List<LocalDate> list1 = getFrequencyDateList("1", begin, end);
        System.out.println(begin.getMonth().firstMonthOfQuarter());

        //List<LocalDate> list2 = getDaysList(1, begin, end);
        //System.out.println(list2);

    }

}
