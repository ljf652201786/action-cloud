package com.action.system.controller;

import com.action.common.core.common.Result;
import com.action.common.core.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @Description: 系统服务公共接口
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/20
 */
@RestController
@RequestMapping("common")
@AllArgsConstructor
public class SysCommonController {
    private final MailService mailService;

    /**
     * @Description: 用户邮箱注册
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public Result sendEmail() {
//        mailService.sendSimpleEmail("652210786@qq.com", "主题", "内容");
//        mailService.sendTemplateEmail("2521313275@qq.com",new String[]{"652210786@qq.com"},new String[]{},"主题",);
        return Result.success();
    }

    @RequestMapping(value = "s", method = RequestMethod.GET)
    public String s() {
//        mailService.sendSimpleEmail("652210786@qq.com", "主题", "内容");
//        mailService.sendTemplateEmail("2521313275@qq.com",new String[]{"652210786@qq.com"},new String[]{},"主题",);
        return "mailService.sendSimpleEmail(\"652210786@qq.com\", \"主题\", \"内容\");";
    }


    /*
     * 计算两个日期之间的工作日天数（不包括周末）
     * */
    public static int calculateWorkdays(LocalDate startDate, LocalDate endDate) {
        // 假设工作日为周一到周五，且没有法定假日
        int workdays = 0;
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workdays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return workdays;
    }

    // 添加法定假日
    public static void addHolidays(Map<LocalDate, String> holidays, LocalDate... dates) {
        for (LocalDate date : dates) {
            holidays.put(date, "Holiday");
        }
    }

    // 修改calculateWorkdays方法以考虑假期
    public static int calculateWorkdays(LocalDate startDate, LocalDate endDate, Map<LocalDate, String> holidays) {
        int workdays = 0;
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY
                    && !holidays.containsKey(currentDate)) {
                workdays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return workdays;
    }

    // 主函数示例
    /*public static void main(String[] args) {
        Map<LocalDate, String> holidays = new HashMap<>();
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        // 假设2023年有假期
        addHolidays(holidays,
                LocalDate.of(2023, 1, 1), // 元旦
                LocalDate.of(2023, 2, 2), // 春节
                LocalDate.of(2023, 4, 3), // 清明节
                LocalDate.of(2023, 5, 1), // 劳动节
                LocalDate.of(2023, 6, 22), // 夏季节
                LocalDate.of(2023, 9, 20), // 中秋节
                LocalDate.of(2023, 10, 1), // 国庆节
                LocalDate.of(2023, 12, 25) // Christmas
        );

        // 计算请假时间内的工作日天数
        int workdays = calculateWorkdays(startDate, endDate, holidays);
        System.out.println("工作日天数: " + workdays);
    }*/

    public static long calculateVacationDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    /*public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024, 4, 1); // 请假开始日期
        LocalDate endDate = LocalDate.of(2024, 4, 8);   // 请假结束日期

        long days = calculateVacationDays(startDate, endDate);
        System.out.println("请假天数: " + days);
    }*/

    public static void main(String[] args) {
        //初始化定义每天考勤的四个时间点 9:00 12:00 14:00 18:00
        int _9 = 9;
        int _12 = 12;
        int _14 = 14;
        int _18 = 18;
        List<Integer> list = new ArrayList<Integer>() {{
            add(_9);
            add(_12);
            add(_14);
            add(_18);
        }};
        float t = 0;
        //请假开始日期 2024-04-26 9:00
        LocalDateTime beginTime = LocalDateTime.of(2024, 4, 26, 9, 0);
        //请假开始日期 2024-04-27 9:00
        LocalDateTime endTime = LocalDateTime.of(2024, 4, 27, 18, 0);

        int beginTimeHour = beginTime.getHour();
        int endTimeHour = endTime.getHour();
        long l = calculateTheTimeDifference(beginTime, endTime);//相差的天数

        if (endTime.getSecond() > beginTime.getSecond() && (!list.contains(beginTimeHour) || !list.contains(endTimeHour))) {
            System.out.println("规则不匹配");
            return;
        }
        switch (beginTimeHour) {
            case 9 -> {
                if (beginTimeHour == endTimeHour) {
                    t = l;
                } else if (beginTimeHour + 3 == endTimeHour || beginTimeHour + 5 == endTimeHour) {
                    t = l + 0.5f;
                } else {
                    t = l + 1;
                }
                break;
            }
            case 12, 14 -> {
                if (beginTimeHour - 3 == endTimeHour || beginTimeHour - 5 == endTimeHour) {
                    t = l - 0.5f;
                } else if (beginTimeHour == endTimeHour || beginTimeHour == endTimeHour + 2 || beginTimeHour - 2 == endTimeHour) {
                    t = l;
                } else {
                    t = l + 0.5f;
                }
                break;
            }
            default -> {
                if (beginTimeHour - 9 == endTimeHour) {
                    t = l - 1;
                } else if (beginTimeHour - 6 == endTimeHour || beginTimeHour - 4 == endTimeHour) {
                    t = l - 0.5f;
                } else {
                    t = l;
                }
            }
        }
        System.out.println("请假天数:" + t + "天");
    }

    public static long calculateTheTimeDifference(LocalDateTime startDate, LocalDateTime endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }


}
