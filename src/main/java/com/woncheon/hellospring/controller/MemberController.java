package com.woncheon.hellospring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.woncheon.hellospring.domain.Member;
import com.woncheon.hellospring.repository.MemoryMemberRepository;
import com.woncheon.hellospring.service.MemberService;

@RestController
@RequestMapping("/users")
public class MemberController {

  private MemberService memberService = new MemberService(new MemoryMemberRepository());

  @PostMapping()
  public void join(@RequestBody Member member) {
    memberService.join(member);
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<Member> getMemberById (@PathVariable("id") Long id) {
    Optional<Member> member = memberService.findMemberOne(id);
    if (member.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(member.get());
  }

  @GetMapping()
  public @ResponseBody ResponseEntity<List<Member>> getMembers () {
    List<Member> members = memberService.findMembers();
    return ResponseEntity.ok(members);
  }

}