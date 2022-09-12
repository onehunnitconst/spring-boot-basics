package com.woncheon.hellospring.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import com.woncheon.hellospring.domain.Member;

class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  // 테스트가 끝나면 실행되는 Callback 메서드
  @AfterEach
  void afterEach() {
    repository.clearStore();
  }

  @Test
  void save() {
    Member member = new Member();
    member.setName("sangsu");
    
    repository.save(member);

    Member savedMember = repository.findById(member.getId()).get();

    assertThat(member).isEqualTo(savedMember);
  }

  @Test
  void findByName() {
    Member member1 = new Member();
    member1.setName("sangsu1");

    Member member2 = new Member();
    member2.setName("sangsu2");

    repository.save(member1);
    repository.save(member2);

    Member result = repository.findByName(member1.getName()).get();

    assertThat(result).isEqualTo(member1);
  }

  @Test
  void findAll() {
    Member member1 = new Member();
    member1.setName("sangsu1");

    Member member2 = new Member();
    member2.setName("sangsu2");

    repository.save(member1);
    repository.save(member2);

    List<Member> results = repository.findAll();

    assertThat(results.size()).isEqualTo(2);
  }
}
