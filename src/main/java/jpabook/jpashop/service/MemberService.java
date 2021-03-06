package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//조회, 읽기를 위한 readOnly
@Transactional(readOnly = true)
//final 필드 가지고 생성자 만듬
@RequiredArgsConstructor
public class MemberService {

    //변경할일 없으면 final 권장
    private final MemberRepository memberRepository;

    //회원 가입
    //읽기전용 제외 default: false
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    //조회: 읽기전용
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 1명 조회
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);

    }
}
