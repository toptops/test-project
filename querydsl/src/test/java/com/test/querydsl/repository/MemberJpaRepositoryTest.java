package com.test.querydsl.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.querydsl.dto.MemberSearchCondition;
import com.test.querydsl.dto.MemberTeamDto;
import com.test.querydsl.entity.Member;
import com.test.querydsl.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> result1 = memberJpaRepository.findAll_QueryDsl();
        assertThat(result1).contains(member);

        List<Member> result2 = memberJpaRepository.findByUsername_QueryDsl("member1");
        assertThat(result2).contains(member);
    }

    @Test
    public void serachTest() {
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
        memberSearchCondition.setAgeGoe(35);
        memberSearchCondition.setAgeLoe(40);
        memberSearchCondition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.search(memberSearchCondition);
        assertThat(result).extracting("username").containsExactly("member4");
    }
}