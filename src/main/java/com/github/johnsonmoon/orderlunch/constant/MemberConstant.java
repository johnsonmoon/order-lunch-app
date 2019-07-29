package com.github.johnsonmoon.orderlunch.constant;


import com.alibaba.fastjson.JSON;
import com.github.johnsonmoon.orderlunch.common.ThreadPools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: fanxx
 * @Date: 2019/7/26 下午5:44
 * @Description:
 */
@Component
public class MemberConstant {
    private static Logger logger = LoggerFactory.getLogger(MemberConstant.class);

    public static Map<String, String> memberMap = new HashMap<>();

    @Value("${order.lunch.web.member-define-file}")
    private String memberDefineFilePath = "members.json";

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        ThreadPools.applicationInitializeThreadPool.submit(() -> {
            try {
                File file = new File(memberDefineFilePath);
                if (file.exists() && file.isFile()) {
                    InputStream inputStream = new FileInputStream(file);
                    Map<String, Object> map = JSON.parseObject(inputStream, Map.class);
                    if (map != null && map.containsKey("members")) {
                        List<Map<String, Object>> maps = (List<Map<String, Object>>) map.get("members");
                        if (maps != null && !maps.isEmpty()) {
                            for (Map<String, Object> objectMap : maps) {
                                memberMap.put(String.valueOf(objectMap.get("name")), String.valueOf(objectMap.get("phone")));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        });
    }
}


