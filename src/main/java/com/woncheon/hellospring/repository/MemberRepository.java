package com.woncheon.hellospring.repository;

import java.util.*;

import com.woncheon.hellospring.domain.Member;

public interface MemberRepository {
  Member save(Member member);
  Optional<Member> findById(Long id);
  Optional<Member> findByName(String name);
  List<Member> findAll();
}
