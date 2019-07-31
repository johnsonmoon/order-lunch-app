package com.github.johnsonmoon.orderlunch.service;


import com.github.johnsonmoon.orderlunch.entity.domain.Member;

import java.util.List;

/**
 * java类作用描述
 *
 * @author: langlang
 * @date: 2019-07-31 15:47
 */
public interface MemberService {

    List<Member> findAll(Member member);

    Member findByName(String name);
}
