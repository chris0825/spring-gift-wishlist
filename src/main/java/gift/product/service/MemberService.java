package gift.product.service;

import gift.product.dao.MemberDao;
import gift.product.model.Member;
import gift.product.util.CertifyUtil;
import gift.product.validation.MemberValidation;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;
    private final MemberValidation memberValidation;
    private final CertifyUtil certifyUtil;

    @Autowired
    public MemberService(MemberDao memberDao, MemberValidation memberValidation, CertifyUtil certifyUtil) {
        this.memberDao = memberDao;
        this.memberValidation = memberValidation;
        this.certifyUtil = certifyUtil;
        memberDao.createMemberTable();
    }

    public Member signUp(Map<String, String> request) {
        memberValidation.emailDuplicateCheck(request.get("email"));
        Member member = new Member(
            request.get("email"),
            certifyUtil.encodingPassword(request.get("password")),
            0
        );
        memberDao.signUp(member);
        return member;
    }
    public void login(Map<String, String> request) {
        Member member = new Member(
            request.get("email"),
            certifyUtil.encodingPassword(request.get("password")),
            0
        );

        memberDao.validateMember(member);
    }

    public boolean isExistsMember(String email) {
        return memberDao.isExistsMember(email);
    }
}
