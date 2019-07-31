package com.github.johnsonmoon.orderlunch.controller;

import com.github.johnsonmoon.orderlunch.entity.domain.Member;
import com.github.johnsonmoon.orderlunch.entity.http.HttpResponse;
import com.github.johnsonmoon.orderlunch.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by xuyh at 2019/7/29 21:08.
 */
@RestController
@RequestMapping("/members")
public class MemberController {
    private static Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @GetMapping(produces = "application/json")
    @ResponseBody
    public HttpResponse getMembers(HttpServletRequest req) {
        Member member = (Member) req.getSession().getAttribute("member");
        List<Member> all = memberService.findAll(member);
        Map<String, String> collect = all.stream().collect(Collectors.toMap(Member::getName, Member::getPhone));
        return new HttpResponse(200, "成功", collect);
    }
}
