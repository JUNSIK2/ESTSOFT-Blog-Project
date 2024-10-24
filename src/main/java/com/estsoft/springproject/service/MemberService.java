package com.estsoft.springproject.service;

import com.estsoft.springproject.domain.entity.Member;
import com.estsoft.springproject.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        // select * from member
        return memberRepository.findAll();
    }

    public Member saveMember(Member member) {
        // insert into member values()
        return memberRepository.save(member);
    }
}
