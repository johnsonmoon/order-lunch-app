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
    Boolean nameExist(String name);

    Long save(Member member);

    List<Member> findAll(Member member);

    List<Member> findAll();

    Member findByName(String name);

    Long getAllCount();

    void deleteById(Long id);
}
