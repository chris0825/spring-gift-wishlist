package gift.product.validation;

import gift.product.exception.DuplicateEmailException;
import gift.product.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberValidation {
    private final MemberService memberService;

    @Autowired
    public MemberValidation(MemberService memberService) {
        this.memberService = memberService;
    }

    public void emailDuplicateCheck(String email) {
        System.out.println("[MemberValidation] emailDuplicateCheck()");
        if(memberService.isExistsMember(email))
            throw new DuplicateEmailException("이미 가입된 회원입니다.");
    }
}
