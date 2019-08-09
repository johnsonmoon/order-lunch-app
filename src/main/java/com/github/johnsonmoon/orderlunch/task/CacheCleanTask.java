package com.github.johnsonmoon.orderlunch.task;

import com.github.johnsonmoon.orderlunch.common.cache.OrderCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Create by xuyh at 2019/8/9 13:54.
 */
@Component
public class CacheCleanTask {
    private static Logger logger = LoggerFactory.getLogger(CacheCleanTask.class);

    @Value("${order.cache.clean.switch-on}")
    private Boolean orderCacheCleanTaskSwitchOn = true;

    @Scheduled(cron = "${order.cache.clean.cron}")
    public void orderCacheCleanTask() {
        if (orderCacheCleanTaskSwitchOn) {
            logger.info("Order cache clean task begin, time: {}", System.currentTimeMillis());
            OrderCache.clear();
            logger.info("Order cache clean task finished, time: {}", System.currentTimeMillis());
        }
    }
}
