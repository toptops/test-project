package com.test.querydsl.repository;

import com.test.querydsl.dto.MemberSearchCondition;
import com.test.querydsl.entity.Member;
import com.test.querydsl.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTestRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    MemberTestRepository memberTestRepository;


    @Test
    public void test() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Member> result = memberTestRepository.applyPagination(memberSearchCondition, pageRequest);

        for(Member p : result) {
            System.out.println(p);
        }
    }
}