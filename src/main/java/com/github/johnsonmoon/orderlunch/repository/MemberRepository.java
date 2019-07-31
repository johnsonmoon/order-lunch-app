package com.github.johnsonmoon.orderlunch.repository;

import com.github.johnsonmoon.orderlunch.entity.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * java类作用描述
 *
 * @author: langlang
 * @date: 2019-07-31 15:57
 */
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Member findByName(String name);
}
