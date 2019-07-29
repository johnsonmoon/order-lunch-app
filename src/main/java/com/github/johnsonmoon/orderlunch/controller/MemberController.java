package com.github.johnsonmoon.orderlunch.controller;

import com.github.johnsonmoon.orderlunch.constant.MemberConstant;
import com.github.johnsonmoon.orderlunch.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by xuyh at 2019/7/29 21:08.
 */
@RestController
@RequestMapping("/members")
public class MemberController {
    private static Logger logger = LoggerFactory.getLogger(MemberController.class);

    @GetMapping(produces = "application/json")
    @ResponseBody
    public HttpResponse getMembers() {
        return new HttpResponse(200, "成功", MemberConstant.memberMap);
    }
}
