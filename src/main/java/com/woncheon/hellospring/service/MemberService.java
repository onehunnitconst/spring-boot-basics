package com.woncheon.hellospring.service;

import java.util.List;
import java.util.Optional;

import com.woncheon.hellospring.domain.Member;
import com.woncheon.hellospring.repository.MemberRepository;

public class MemberService {

  MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member) {
    validateDuplicateMember(member);

    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
      .ifPresent(m -> {
        throw new IllegalStateException("이미 존재하는 회원입니다.");
      });
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findMemberOne(Long id) {
    return memberRepository.findById(id);
  }

}