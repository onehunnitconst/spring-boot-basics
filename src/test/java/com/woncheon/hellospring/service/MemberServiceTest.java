package com.woncheon.hellospring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.woncheon.hellospring.domain.Member;
import com.woncheon.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {
  
  MemoryMemberRepository repository;
  MemberService service;

  // 테스트 시작 전 실행되는 Callback 메서드
  @BeforeEach
  void beforeEach() {
    repository = new MemoryMemberRepository();
    service = new MemberService(repository);
  }

  // 테스트가 끝나면 실행되는 Callback 메서드
  @AfterEach
  void afterEach() {
    repository.clearStore();
  }

  @Test
  void join() {
    Member member = new Member();
    member.setName("sangsu");

    Long saveId = service.join(member);

    Member result = service.findMemberOne(saveId).get();
    assertThat(member.getName()).isEqualTo(result.getName());
  }

  @Test
  void validateDuplicateMember() {
    Member member1 = new Member();
    member1.setName("sangsu");

    service.join(member1);

    Member member2 = new Member();
    member2.setName("sangsu");

    IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

  @Test
  void findMembers() {
    Member member1 = new Member();
    member1.setName("sangsu1");

    Member member2 = new Member();
    member2.setName("sangsu2");

    service.join(member1);
    service.join(member2);

    List<Member> results = service.findMembers();

    assertThat(results.size()).isEqualTo(2);
  }
}