package com.algo.util;

import cn.hutool.core.date.TimeInterval;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class TimerUtil {

    private static final TimeInterval timer = new TimeInterval();

    public static void start(String groupId) {
        timer.start(groupId);
    }

    public static void stop(String groupId) {
        long l = timer.intervalMs(groupId);
        log.info("{}耗时：{} MS",groupId,l);
    }
}
