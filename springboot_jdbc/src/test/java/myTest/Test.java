package myTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 时间测试类
 */
class TimeTest {
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

 class DateUtils {
    //自定义日期格式
    public static final String SIMPLE_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String SIMPLE_DATEFORMAT_YMD = "yyyy-MM-dd";
    //日期比较方法
    public static int compareDate(Date first, Date second){
        return first.compareTo(second);
    }
    //日期转string
    public static String formatDateYMDHMS(Date date){
        if(date==null){
            return null;
        }
        return format(date,SIMPLE_DATEFORMAT);
    }
    //通用格式化
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}

public class Test {
    public static void main(String[] args) {
        int i = 0;
        List<TimeTest> timeList = new ArrayList<>();
//        while (++i <= 5) {
//            TimeTest time = new TimeTest();
//            String s = new Double(1535610732881);
//            time.setTime(new Date(s.toString()));
//            timeList.add(time);
//        }
        TimeTest time2 = new TimeTest();
        time2.setTime(new Date(Date.parse("6/13/2019")));
        timeList.add(time2);

        TimeTest time3 = new TimeTest();
        time3.setTime(new Date(Date.parse("6/14/2018")));
        timeList.add(time3);

        TimeTest time4 = new TimeTest();
        time4.setTime(new Date(Date.parse("6/15/2018")));
        timeList.add(time4);

        TimeTest time5 = new TimeTest();
        time5.setTime(new Date(Date.parse("6/16/2018")));
        timeList.add(time5);

        TimeTest time6 = new TimeTest();
        time6.setTime(new Date(Date.parse("6/17/2018")));
        timeList.add(time6);

        TimeTest time7 = new TimeTest();
        time7.setTime(new Date(Date.parse("6/19/2018")));
        timeList.add(time7);
        System.out.println(timeList);
        //插入一个空值
//        TimeTest time = new TimeTest();
//        timeList.add(time);
//        System.out.println("---------------初始值------------------");
//        timeList.forEach(o -> {
//            System.out.println(DateUtils.formatDateYMDHMS(o.getTime()));
//        });
//        System.out.println("---------------------------------------");
        //下面有两种写法 推荐使用方法一
        //代码解释: 将timeList流首先过滤时间不为空的,将需要比较的值转出map然后去重,最后取出最大值/最小值
        //方法一 min(DateUtils::compareDate)  这种写法需要自己写工具类,且compareDate必须是static修饰
//        Date min = timeList.stream().filter(o -> o.getTime() != null).map(TimeTest::getTime).distinct().min(DateUtils::compareDate).get();
        //方法二 max((e1, e2) -> e1.compareTo(e2))这种写法需要类里面含有比较方法
        List<TimeTest> max = timeList.stream().filter(o -> o.getTime() != null).
                sorted((e1, e2) -> {
                    return e2.getTime().compareTo(e1.getTime());
                }).collect(Collectors.toList());
//        System.out.println("min=" + DateUtils.formatDateYMDHMS(min));
        System.out.println("max=" + DateUtils.formatDateYMDHMS(max.get(0).getTime()));
    }
}

