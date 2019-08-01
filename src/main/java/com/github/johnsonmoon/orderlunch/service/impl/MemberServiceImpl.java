package com.github.johnsonmoon.orderlunch.service.impl;

import com.github.johnsonmoon.orderlunch.entity.domain.Member;
import com.github.johnsonmoon.orderlunch.repository.MemberRepository;
import com.github.johnsonmoon.orderlunch.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * java类作用描述
 *
 * @author: langlang
 * @date: 2019-07-31 15:47
 */
@Component
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Long save(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public List<Member> findAll(Member member) {
        List<Member> all = new ArrayList<>();
        if (member != null) {
            all.add(member);
        } else {
            all = memberRepository.findAll();
        }
        return all;
    }

    @Override
    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }

    @Override
    public Long getAllCount() {
        return memberRepository.count();
    }
}
