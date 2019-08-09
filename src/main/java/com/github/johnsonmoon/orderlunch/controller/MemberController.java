package com.github.johnsonmoon.orderlunch.controller;

import com.github.johnsonmoon.orderlunch.common.RestContext;
import com.github.johnsonmoon.orderlunch.entity.domain.Member;
import com.github.johnsonmoon.orderlunch.entity.http.HttpResponse;
import com.github.johnsonmoon.orderlunch.entity.param.MemberParam;
import com.github.johnsonmoon.orderlunch.entity.vo.MemberVO;
import com.github.johnsonmoon.orderlunch.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by xuyh at 2019/7/29 21:08.
 */
@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping(produces = "application/json")
    public HttpResponse getMembers() {
        HttpServletRequest req = RestContext.getHttpServletRequest();
        Member member = (Member) req.getSession().getAttribute("member");
        List<Member> all = memberService.findAll(member);
        Map<String, String> collect = all.stream().collect(Collectors.toMap(Member::getName, Member::getPhone));
        return new HttpResponse(200, "成功", collect);
    }

    @GetMapping(path = "/list", produces = "application/json")
    public HttpResponse listAllMembers() {
        List<Member> members = memberService.findAll();
        List<MemberVO> memberVOS = new ArrayList<>();
        if (members != null && !members.isEmpty()) {
            for (Member member : members) {
                MemberVO memberVO = new MemberVO();
                BeanUtils.copyProperties(member, memberVO);
                memberVOS.add(memberVO);
            }
        }
        return new HttpResponse(200, "成功", memberVOS);
    }

    @PostMapping(path = "/member", produces = "application/json", consumes = "application/json")
    public HttpResponse saveMember(@RequestBody MemberParam memberParam) {
        Member member = new Member();
        BeanUtils.copyProperties(memberParam, member);
        Long id = memberService.save(member);
        return new HttpResponse(200, "成功", id);
    }

    @GetMapping(path = "/member/delete")
    public HttpResponse deleteMember(@RequestParam("id") Long id) {
        memberService.deleteById(id);
        return new HttpResponse(200, "成功");
    }
}
