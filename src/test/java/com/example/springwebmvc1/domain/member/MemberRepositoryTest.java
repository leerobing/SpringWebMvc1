package com.example.springwebmvc1.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setUsername("lee");
        member.setAge(28);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member member1 = memberRepository.find(saveMember.getId());
        assertThat(member1.getId()).isEqualTo(member.getId());
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> findAllList = memberRepository.findAll();


        //then
        assertThat(findAllList.size()).isEqualTo(2);
        assertThat(findAllList).contains(member1,member2);

    }

}